package net.querz.nbt.tag;

import java.util.Arrays;

public class CharArrayTag extends ArrayTag<char[]> implements Comparable<CharArrayTag>{

    public static final byte ID = 14;
    public static final char[] ZERO_VALUE = new char[0];

    public CharArrayTag() {
        super(ZERO_VALUE);
    }

    public CharArrayTag(char[] value) {
        super(value);
    }

    @Override
    public int compareTo(CharArrayTag o) {
        return Integer.compare(length(),o.length());
    }

    @Override
    public byte getID() {
        return ID;
    }

    @Override
    public CharArrayTag clone() {
        return new CharArrayTag(Arrays.copyOf(getValue(),length()));
    }

    @Override
    public boolean equals(Object other) {
        return super.equals(other) && Arrays.equals(getValue(),((CharArrayTag) other).getValue());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(getValue());
    }

    @Override
    protected String arrayToString(String prefix, String suffix) {
        StringBuilder sb = new StringBuilder("[\"");
        for(char c: getValue()) {
            sb.append(escapeString(c+"",false));
        }
        sb.append("\"]");
        return sb.toString();
    }
}
