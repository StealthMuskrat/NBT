package net.querz.nbt.tag;

import java.util.Arrays;

public class FloatArrayTag extends ArrayTag<float[]> implements Comparable<FloatArrayTag> {

    public static final byte ID = 17;
    public static final float[] ZERO_VALUE = new float[0];

    public FloatArrayTag() {
        super(ZERO_VALUE);
    }

    public FloatArrayTag(float[] value) {
        super(value);
    }

    @Override
    public int compareTo(FloatArrayTag o) {
        return Integer.compare(length(),o.length());
    }

    @Override
    public byte getID() {
        return ID;
    }

    @Override
    public Tag<float[]> clone() {
        return new FloatArrayTag(Arrays.copyOf(getValue(),length()));
    }

    @Override
    public boolean equals(Object other) {
        return super.equals(other) && Arrays.equals(getValue(),((FloatArrayTag) other).getValue());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(getValue());
    }
}
