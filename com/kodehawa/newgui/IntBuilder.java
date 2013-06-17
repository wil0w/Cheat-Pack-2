package com.kodehawa.newgui;

public class IntBuilder extends Builder
{
    public final int start;
    public final int min;
    public final int max;
    public int standard;

    public IntBuilder(int i)
    {
        this(i, i);
    }

    public IntBuilder(int i, int j)
    {
        this(i, j, 0x80000000, 0x7fffffff);
    }

    public IntBuilder(int i, int j, int k)
    {
        this(i, i, j, k);
    }

    public IntBuilder(int i, int j, int k, int l)
    {
        start = i;
        standard = j;
        min = k;
        max = l;
        setValue(Integer.valueOf(i));
    }

    public void append(char c)
    {
        int i = builder.length();

        if (c == '\b' && i > 0)
        {
            builder.delete(i - 1, i);
        }
        else if (c >= '0' && c <= '9')
        {
            long l = Long.valueOf((new StringBuilder()).append(toString()).append(c).toString()).longValue();

            if (l < (long)min)
            {
                setInt(min);
            }
            else if (l > (long)max)
            {
                setInt(max);
            }
            else
            {
                builder.append(c);
            }
        }
        else if (c == '-' && min < 0 && i == 0)
        {
            builder.append(c);
        }
    }

    public Number getStart()
    {
        return new Integer(start);
    }

    public Number getMin()
    {
        return new Integer(min);
    }

    public Number getMax()
    {
        return new Integer(max);
    }

    public Number getStandard()
    {
        return new Integer(standard);
    }

    public Number toValue()
    {
        return new Integer(toInt());
    }

    public void setValue(Number number)
    {
        setInt(number.intValue());
    }

    public void setInt(int i)
    {
        builder = new StringBuilder(String.valueOf(i));
    }

    public int toInt()
    {
        try
        {
            return Integer.parseInt(toString());
        }
        catch (Exception exception)
        {
            return standard;
        }
    }
}
