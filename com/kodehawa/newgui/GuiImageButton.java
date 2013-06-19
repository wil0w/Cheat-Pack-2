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

import net.minecraft.client.Minecraft;
import net.minecraft.src.GuiButton;
import net.minecraft.src.RenderEngine;
import org.lwjgl.opengl.GL11;

public class GuiImageButton extends GuiButton
{
	
	protected Minecraft mc;
    public String texture;
    public int imgxpos;
    public int imgypos;
    public int imgwidth;
    public int imgheight;
    public int imgxoffset;
    public int imgyoffset;

    public GuiImageButton(int i, int j, int k, int l, int i1)
    {
        super(i, j, k, l, i1, "");
        texture = "";
        imgxpos = 0;
        imgypos = 0;
        imgwidth = l;
        imgheight = i1;
    }

    public GuiImageButton setImageData(String s, int i, int j, int k, int l)
    {
        texture = s;
        imgxpos = i;
        imgypos = j;
        imgwidth = k;
        imgheight = l;
        return this;
    }

    public GuiImageButton setImageOffset(int i, int j)
    {
        imgxoffset = i;
        imgyoffset = j;
        return this;
    }

    /**
     * Draws this button to the screen.
     */
    public void drawButton(Minecraft minecraft, int i, int j)
    {
        if (!drawButton)
        {
            return;
        }
        else
        {
            this.mc.renderEngine.bindTexture(texture);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            boolean flag = enabled && drawButton && i >= xPosition && j >= yPosition && i < xPosition && j < yPosition;
            int k = getHoverState(flag);
            drawTexturedModalRect(xPosition + imgxoffset, yPosition + imgyoffset, imgxpos, imgypos + k * imgheight, imgwidth, imgheight);
            mouseDragged(minecraft, i, j);
            return;
        }
    }
}
