package net.querz.nbt.io;

import net.querz.io.MaxDepthIO;
import net.querz.nbt.tag.*;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * SNBTWriter creates an SNBT String.
 *
 * */
public final class SNBTWriter implements MaxDepthIO {

	private static final Pattern NON_QUOTE_PATTERN = Pattern.compile("[a-zA-Z_.+\\-]+");

	private Writer writer;

	private SNBTWriter(Writer writer) {
		this.writer = writer;
	}

	public static void write(Tag<?> tag, Writer writer, int maxDepth) throws IOException {
		new SNBTWriter(writer).writeAnything(tag, maxDepth);
	}

	public static void write(Tag<?> tag, Writer writer) throws IOException {
		write(tag, writer, Tag.DEFAULT_MAX_DEPTH);
	}

	private void writeAnything(Tag<?> tag, int maxDepth) throws IOException {
		switch (tag.getID()) {
		case EndTag.ID:
			//do nothing
			break;
		case ByteTag.ID:
			writer.append(Byte.toString(((ByteTag) tag).asByte())).write('b');
			break;
		case ShortTag.ID:
			writer.append(Short.toString(((ShortTag) tag).asShort())).write('s');
			break;
		case IntTag.ID:
			writer.write(Integer.toString(((IntTag) tag).asInt()));
			break;
		case LongTag.ID:
			writer.append(Long.toString(((LongTag) tag).asLong())).write('l');
			break;
		case FloatTag.ID:
			writer.append(Float.toString(((FloatTag) tag).asFloat())).write('f');
			break;
		case DoubleTag.ID:
			writer.append(Double.toString(((DoubleTag) tag).asDouble())).write('d');
			break;
		case ByteArrayTag.ID:
			writeArray(((ByteArrayTag) tag).getValue(), ((ByteArrayTag) tag).length(), "B");
			break;
		case StringTag.ID:
			writer.write(escapeString(((StringTag) tag).getValue()));
			break;
		case ListTag.ID:
			writer.write('[');
			for (int i = 0; i < ((ListTag<?>) tag).size(); i++) {
				writer.write(i == 0 ? "" : ",");
				writeAnything(((ListTag<?>) tag).get(i), decrementMaxDepth(maxDepth));
			}
			writer.write(']');
			break;
		case CompoundTag.ID:
			writer.write('{');
			boolean first = true;
			for (Map.Entry<String, Tag<?>> entry : (CompoundTag) tag) {
				writer.write(first ? "" : ",");
				writer.append(escapeString(entry.getKey())).write(':');
				writeAnything(entry.getValue(), decrementMaxDepth(maxDepth));
				first = false;
			}
			writer.write('}');
			break;
		case IntArrayTag.ID:
			writeArray(((IntArrayTag) tag).getValue(), ((IntArrayTag) tag).length(), "I");
			break;
		case LongArrayTag.ID:
			writeArray(((LongArrayTag) tag).getValue(), ((LongArrayTag) tag).length(), "L");
			break;
		case CharTag.ID:
			writer.append(escapeChar(((CharTag) tag).getValue()));
		case CharArrayTag.ID:
			writeArray(((CharArrayTag) tag).getValue(),0,"C");
		case StringArrayTag.ID:
			writeArray(((StringArrayTag) tag).getValue(), ((StringArrayTag) tag).length(), "W");
		default:
			throw new IOException("unknown tag with id \"" + tag.getID() + "\"");
		}
	}

	private void writeArray(Object array, int length, String prefix) throws IOException {
		writer.append('[').append(prefix).write(';');
		if(array instanceof char[]) {
			writer.write(escapeString(new String((char[]) array)));
		}else if(array instanceof String[]) {
			for(int i = 0; i < length; i++) {
				writer.append(i == 0 ? "" : ",").write(escapeString((String) Array.get(array, i)));
			}
		}else {
			for (int i = 0; i < length; i++) {
				writer.append(i == 0 ? "" : ",").write(Array.get(array, i).toString());
			}
		}
		writer.write(']');
	}

	public static String escapeString(String s) {
		if (!NON_QUOTE_PATTERN.matcher(s).matches()) {
			StringBuilder sb = new StringBuilder();
			sb.append('"');
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if (c == '\\' || c == '"') {
					sb.append('\\');
				}
				sb.append(c);
			}
			sb.append('"');
			return sb.toString();
		}
		return s;
	}

	public static String escapeChar(char c) {
		StringBuilder sb = new StringBuilder();
		sb.append('\'');
		if(c == '\\' || c == '\'') {
			sb.append('\\');
		}
		sb.append(c).append('\'');
		return sb.toString();
	}
}
