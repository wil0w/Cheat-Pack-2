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

import net.minecraft.src.FontRenderer;
import net.minecraft.src.Gui;

public class GuiValueField extends Gui
{
    public final int xPos;
    public final int yPos;
    public final int width;
    public final int height;
    public final Builder builder;
    protected final FontRenderer fontRenderer;
    private int cursorCounter;
    public boolean isFocused;
    public boolean isEnabled;

    public GuiValueField(Builder builder1, FontRenderer fontrenderer, int i, int j, int k, int l)
    {
        cursorCounter = 0;
        isFocused = false;
        isEnabled = true;
        builder = builder1;
        fontRenderer = fontrenderer;
        xPos = i;
        yPos = j;
        width = k;
        height = l;
    }

    public int toInt()
    {
        return getIntBuilder().toInt();
    }

    public float toFloat()
    {
        return getFloatBuilder().toFloat();
    }

    public IntBuilder getIntBuilder()
    {
        if (!(builder instanceof IntBuilder))
        {
            throw new RuntimeException("IntBuilder not available");
        }
        else
        {
            return (IntBuilder)builder;
        }
    }

    public FloatBuilder getFloatBuilder()
    {
        if (!(builder instanceof FloatBuilder))
        {
            throw new RuntimeException("FloatBuilder not available");
        }
        else
        {
            return (FloatBuilder)builder;
        }
    }

    public void keyTyped(char c, int i)
    {
        if (!isEnabled || !isFocused)
        {
            return;
        }
        else
        {
            builder.append(c);
            return;
        }
    }

    public void drawScreen(int i, int j, float f)
    {
        drawRect(xPos - 1, yPos - 1, xPos + width + 1, yPos + height + 1, 0xffa0a0a0);
        drawRect(xPos, yPos, xPos + width, yPos + height, 0xff000000);

        if (isEnabled)
        {
            boolean flag = isFocused && (cursorCounter / 6) % 2 == 0;
            drawString(fontRenderer, (new StringBuilder()).append(builder.toString()).append(flag ? "_" : "").toString(), xPos + 4, yPos + (height - 8) / 2, 0xe0e0e0);
        }
        else
        {
            drawString(fontRenderer, builder.toString(), xPos + 4, yPos + (height - 8) / 2, 0x707070);
        }
    }

    public void mouseClicked(int i, int j, int k)
    {
        boolean flag = isEnabled && i >= xPos && i < xPos + width && j >= yPos && j < yPos + height;

        if (flag && !isFocused)
        {
            cursorCounter = 0;
        }

        isFocused = flag;
    }

    public void updateScreen()
    {
        cursorCounter++;
    }
}
