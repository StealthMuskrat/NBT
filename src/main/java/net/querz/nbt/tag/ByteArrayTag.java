package net.querz.nbt.tag;

import java.util.Arrays;

public class ByteArrayTag extends ArrayTag<byte[]> implements Comparable<ByteArrayTag> {

	public static final byte ID = 7;
	public static final byte[] ZERO_VALUE = new byte[0];

	public ByteArrayTag() {
		super(ZERO_VALUE);
	}

	public ByteArrayTag(byte[] value) {
		super(value);
	}

	public ByteArrayTag(boolean[] value) {
		super(ZERO_VALUE);
		byte[] data = new byte[value.length];
		for(int i = 0; i < value.length; i++) {
			data[i] = (byte) (value[i] ? 1 : 0);
		}
		setValue(data);
	}

	@Override
	public byte getID() {
		return ID;
	}

	public boolean[] asBoolean() {
		boolean[] data = new boolean[length()];
		for(int i = 0; i < length(); i++) {
			data[i] = getValue()[i] > 0;
		}
		return data;
	}

	@Override
	public boolean equals(Object other) {
		return super.equals(other) && Arrays.equals(getValue(), ((ByteArrayTag) other).getValue());
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(getValue());
	}

	@Override
	public int compareTo(ByteArrayTag other) {
		return Integer.compare(length(), other.length());
	}

	@Override
	public ByteArrayTag clone() {
		return new ByteArrayTag(Arrays.copyOf(getValue(), length()));
	}
}
