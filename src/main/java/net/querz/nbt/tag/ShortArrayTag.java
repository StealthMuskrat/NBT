package net.querz.nbt.tag;

import java.util.Arrays;

public class ShortArrayTag extends ArrayTag<short[]> implements Comparable<ShortArrayTag> {

    public static final byte ID = 16;
    public static final short[] ZERO_VALUE = new short[0];

    public ShortArrayTag() {
        super(ZERO_VALUE);
    }

    public ShortArrayTag(short[] value) {
        super(value);
    }

    @Override
    public int compareTo(ShortArrayTag o) {
        return Integer.compare(length(),o.length());
    }

    @Override
    public byte getID() {
        return ID;
    }

    @Override
    public Tag<short[]> clone() {
        return new ShortArrayTag(Arrays.copyOf(getValue(),length()));
    }

    @Override
    public boolean equals(Object other) {
        return super.equals(other) && Arrays.equals(getValue(),((ShortArrayTag) other).getValue());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(getValue());
    }
}
