package com.kodehawa.newgui;


public class FloatBuilder extends Builder
{
    public final float min;
    public final float max;
    public float start;
    public float standard;
    private boolean minus;
    private boolean comma;
    private boolean infinity;
    private boolean nan;

    public FloatBuilder(float f)
    {
        this(f, f);
    }

    public FloatBuilder(float f, float f1)
    {
        this(f, f1, -3.402823E+038F, 3.402823E+038F);
    }

    public FloatBuilder(float f, float f1, float f2)
    {
        this(f, f, f1, f2);
    }

    public FloatBuilder(float f, float f1, float f2, float f3)
    {
        start = f;
        standard = f1;
        min = f2;
        max = f3;
        setValue(Float.valueOf(f));
    }

    public void append(char c)
    {
        c = Character.toLowerCase(c);
        String s = toString();
        int i = builder.length();

        if (c == '\b' && i > 0)
        {
            char c1 = builder.charAt(i - 1);

            if (c1 == '-')
            {
                minus = false;
            }

            if (c1 == '.' || c1 == ',')
            {
                comma = false;
            }

            byte byte0 = 1;

            if (infinity)
            {
                byte0 = 8;
            }
            else if (nan)
            {
                byte0 = 3;
            }

            builder.delete(i - byte0, i);

            if (infinity)
            {
                infinity = false;
            }

            if (nan)
            {
                nan = false;
            }
        }
        else
        {
            if (comma && s.length() - s.indexOf(".") > 5 || infinity || nan)
            {
                return;
            }

            if (c >= '0' && c <= '9')
            {
                double d = Double.valueOf((new StringBuilder()).append(s).append(c).toString()).doubleValue();

                if (d < (double)min)
                {
                    setFloat(min);
                }
                else if (d > (double)max)
                {
                    setFloat(max);
                }
                else
                {
                    builder.append(c);
                }
            }
            else if (c == '-' && min < 0.0F && i == 0)
            {
                minus = true;
                builder.append(c);
            }
            else if (!comma && (c == '.' || c == ',') && (i > 0 && !minus || i > 1 && minus))
            {
                comma = true;
                builder.append('.');
            }
            else if (!comma && c == 'i' && (i == 0 || i == 1 && minus))
            {
                infinity = true;
                builder.append("Infinity");
            }
            else if (!comma && c == 'n' && i == 0)
            {
                nan = true;
                builder.append("NaN");
            }
        }
    }

    public Number getStart()
    {
        return new Float(start);
    }

    public Number getMin()
    {
        return new Float(min);
    }

    public Number getMax()
    {
        return new Float(max);
    }

    public Number getStandard()
    {
        return new Float(standard);
    }

    public Number toValue()
    {
        return new Float(toFloat());
    }

    public void setValue(Number number)
    {
        setFloat(number.floatValue());
    }

    public void setFloat(float f)
    {
        String s = String.valueOf(f);
        minus = s.indexOf("-") != -1;
        int i = s.indexOf(".");
        comma = i != -1;

        if (comma && s.length() - i > 5)
        {
            int j = s.indexOf("E");
            s = (new StringBuilder()).append(s.substring(0, i + 6)).append(j == -1 ? "" : s.substring(j)).toString();
        }

        infinity = Math.abs(f) == (1.0F / 0.0F);
        nan = Float.isNaN(f);
        builder = new StringBuilder(s);
    }

    public float toFloat()
    {
        try
        {
            return Float.parseFloat(toString());
        }
        catch (Exception exception)
        {
            return start;
        }
    }
}
