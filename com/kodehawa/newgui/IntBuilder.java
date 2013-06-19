/*
* Copyright (c) 2013 Andrew Crocker
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in
* all copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
* THE SOFTWARE.
*/

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
