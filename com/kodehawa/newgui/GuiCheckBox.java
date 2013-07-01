/*
* Copyright (c) 2013 David Rubio
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


import net.minecraft.src.GuiButton;
import net.minecraft.src.Minecraft;

public class GuiCheckBox extends GuiButton
{
    public String onText;
    public String offText;
    private boolean checked;
    private String text;

    public GuiCheckBox(int i, int j, int k, int l, int i1, String s, boolean flag)
    {
        super(i, j, k, l, i1, null);
        onText = "On";
        offText = "Off";
        checked = flag;
        setText(s);
    }

    /**
     * Returns true if the mouse has been pressed on this control. Equivalent of MouseListener.mousePressed(MouseEvent
     * e).
     */
    public boolean mousePressed(Minecraft minecraft, int i, int j)
    {
        boolean flag = super.mousePressed(minecraft, i, j);

        if (flag)
        {
            checked = !checked;
            update();
        }

        return flag;
    }

    public boolean isChecked()
    {
        return checked;
    }

    public void setText(String s)
    {
        text = s;
        update();
    }

    public String getText()
    {
        return text;
    }

    private void update()
    {
        displayString = (new StringBuilder()).append(text).append(": ").append(checked ? onText : offText).toString();
    }
}
