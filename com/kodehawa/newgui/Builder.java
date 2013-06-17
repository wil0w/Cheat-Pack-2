package com.kodehawa.newgui;

public abstract class Builder
{
    protected StringBuilder builder;

    public Builder()
    {
    }

    public abstract void append(char c);

    public abstract Number getStart();

    public abstract Number getMin();

    public abstract Number getMax();

    public abstract Number getStandard();

    public abstract Number toValue();

    public abstract void setValue(Number number);

    public final String toString()
    {
        return builder.toString();
    }
}
