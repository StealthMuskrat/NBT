package net.querz.nbt.tag;

public class CharTag extends Tag<Character> implements Comparable<CharTag> {

    public static final byte ID = 13;
    public static final char ZERO_VALUE = '\u0000';

    public CharTag() {
        super(ZERO_VALUE);
    }

    public CharTag(char value) {
        super(value);
    }


    @Override
    public int compareTo(CharTag o) {
        return getValue().compareTo(o.getValue());
    }

    @Override
    public byte getID() {
        return ID;
    }

    @Override
    public String valueToString(int maxDepth) {
        return escapeString(getValue().toString(),false);
    }

    @Override
    public Tag<Character> clone() {
        return new CharTag(getValue());
    }

    @Override
    public Character getValue() {
        return super.getValue();
    }

    @Override
    public void setValue(Character value) {
        super.setValue(value);
    }

    @Override
    public boolean equals(Object other) {
        return super.equals(other) && getValue() == ((CharTag) other).getValue();
    }
}
