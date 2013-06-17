package com.kodehawa.newgui;

import net.minecraft.client.Minecraft;
import net.minecraft.src.GuiButton;
import net.minecraft.src.RenderEngine;
import org.lwjgl.opengl.GL11;

public class GuiImageButton extends GuiButton
{
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
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, minecraft.renderEngine.getTexture(texture));
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            boolean flag = enabled && drawButton && i >= xPosition && j >= yPosition && i < xPosition && j < yPosition;
            int k = getHoverState(flag);
            drawTexturedModalRect(xPosition + imgxoffset, yPosition + imgyoffset, imgxpos, imgypos + k * imgheight, imgwidth, imgheight);
            mouseDragged(minecraft, i, j);
            return;
        }
    }
}
