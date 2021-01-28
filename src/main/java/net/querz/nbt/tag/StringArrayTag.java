package net.querz.nbt.tag;

import java.lang.reflect.Array;
import java.util.Arrays;

public class StringArrayTag extends ArrayTag<String[]> implements Comparable<StringArrayTag> {

    public static final byte ID = 15;
    public static final String[] ZERO_VALUE = new String[0];

    public StringArrayTag() {
        super(ZERO_VALUE);
    }

    public StringArrayTag(String[] value) {
        super(value);
    }

    @Override
    public int compareTo(StringArrayTag o) {
        return Integer.compare(length(),o.length());
    }

    @Override
    public byte getID() {
        return ID;
    }

    @Override
    public Tag<String[]> clone() {
        return new StringArrayTag(Arrays.copyOf(getValue(),length()));
    }

    @Override
    public boolean equals(Object other) {
        return super.equals(other) && Arrays.equals(getValue(),((StringArrayTag) other).getValue());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(getValue());
    }

    @Override
    protected String arrayToString(String prefix, String suffix) {
        StringBuilder sb = new StringBuilder("[");
        for(int i = 0; i < length(); i++) {
            sb.append(i == 0 ? "" : ",").append(escapeString((String)Array.get(getValue(),i),false));
        }
        sb.append("]");
        return sb.toString();
    }

}