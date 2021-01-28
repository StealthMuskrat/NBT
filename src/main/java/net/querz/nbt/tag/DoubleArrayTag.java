package net.querz.nbt.tag;

import java.util.Arrays;

public class DoubleArrayTag extends ArrayTag<double[]> implements Comparable<DoubleArrayTag> {

    public static final byte ID = 18;
    public static final double[] ZERO_VALUE = new double[0];

    public DoubleArrayTag() {
        super(ZERO_VALUE);
    }

    public DoubleArrayTag(double[] value) {
        super(value);
    }

    @Override
    public int compareTo(DoubleArrayTag o) {
        return Integer.compare(length(),o.length());
    }

    @Override
    public byte getID() {
        return ID;
    }

    @Override
    public Tag<double[]> clone() {
        return new DoubleArrayTag(Arrays.copyOf(getValue(),length()));
    }

    @Override
    public boolean equals(Object other) {
        return super.equals(other) && Arrays.equals(getValue(),((DoubleArrayTag) other).getValue());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(getValue());
    }
}
