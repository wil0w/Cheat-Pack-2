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
import net.minecraft.src.GuiScreen;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.kodehawa.CheatPack;

public class GuiTeleport extends GuiScreen
{
    private GuiScreen parentScreen;
    private GuiListControl dimension;
    private GuiValueField posX;
    private GuiValueField posY;
    private GuiValueField posZ;

    public GuiTeleport(GuiScreen guiscreen)
    {
        parentScreen = guiscreen;
        dimension = new GuiListControl(0);
        dimension.focused = true;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        Keyboard.enableRepeatEvents(true);
        int i = (int)((float)(width - 218) * 0.5F);
        int j = (int)((float)(height - 148) * 0.5F);
        int k = 0;
        int l = 0;

        do
        {
            if (l >= CheatPack.dimensions.length)
            {
                break;
            }

            if (CheatPack.dimensions[l].intValue() == mc.thePlayer.dimension)
            {
                k = l;
                break;
            }

            l++;
        }
        while (true);

        dimension.index = k;
        buttonList.addAll(dimension.initGui(0, 1, 2, CheatPack.translate((new StringBuilder()).append("Dimension.").append(CheatPack.dimensions[dimension.index].intValue()).toString()), i + 9, j + 9, 200));
        posX = new GuiValueField(new FloatBuilder((float)mc.thePlayer.posX), fontRenderer, i + 9, j + 35, 200, 20);
        posY = new GuiValueField(new FloatBuilder((float)mc.thePlayer.posY), fontRenderer, i + 9, j + 61, 200, 20);
        posZ = new GuiValueField(new FloatBuilder((float)mc.thePlayer.posZ), fontRenderer, i + 9, j + 87, 200, 20);
        buttonList.add(new GuiButton(200, i + 9, j + 120, 97, 20, CheatPack.translate("GuiTeleport.ButtonTeleport")));
        buttonList.add(new GuiButton(201, i + 112, j + 120, 97, 20, CheatPack.translate("GuiTeleport.ButtonWarp")));
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int i, int j, float f)
    {
        drawDefaultBackground();
        mc.renderEngine.bindTexture("/CheatPackRessources/guiteleport.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        
        int l = (width - 218) / 2;
        int i1 = (height - 148) / 2;
        drawTexturedModalRect(l, i1, 0, 0, 218, 148);
        posX.drawScreen(i, j, f);
        posY.drawScreen(i, j, f);
        posZ.drawScreen(i, j, f);
        dimension.setInfo(CheatPack.translate((new StringBuilder()).append("Dimension.").append(CheatPack.dimensions[dimension.index].intValue()).toString()));
        drawCenteredString(fontRenderer, CheatPack.translate("GuiTeleport.LabelTitle"), width / 2, 20, 0xffffff);
        drawCenteredString(fontRenderer, CheatPack.translate("GuiTeleport.LabelPosX"), width / 2, i1 + 35 + 5, 0xffffff);
        drawCenteredString(fontRenderer, CheatPack.translate("GuiTeleport.LabelPosY"), width / 2, i1 + 61 + 5, 0xffffff);
        drawCenteredString(fontRenderer, CheatPack.translate("GuiTeleport.LabelPosZ"), width / 2, i1 + 87 + 5, 0xffffff);
        super.drawScreen(i, j, f);
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    protected void actionPerformed(GuiButton guibutton)
    {
        dimension.actionPerformed(guibutton, CheatPack.dimensions);

        if (guibutton.id == 200)
        {
            applyChanges(true);
        }

        if (guibutton.id == 201)
        {
            applyChanges(false);
        }
    }

    private void applyChanges(boolean flag)
    {
        if (flag)
        {
            CheatPack.getBase(mc).teleport(mc.thePlayer, posX.getFloatBuilder().toFloat(), posY.getFloatBuilder().toFloat(), posZ.getFloatBuilder().toFloat());
        }
        else
        {
            //CheatPack.getBase(mc).warp(mc.thePlayer, CheatPack.dimensions[dimension.index].intValue());
        }
    }

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    public void keyTyped(char c, int i)
    {
        if (!CheatPack.escapeGui(mc, i, parentScreen))
        {
            super.keyTyped(c, i);
            posX.keyTyped(c, i);
            posY.keyTyped(c, i);
            posZ.keyTyped(c, i);
            dimension.keyTyped(c, i, CheatPack.dimensions, CheatPack.prevKey, CheatPack.nextKey, mc.sndManager);
        }
    }

    /**
     * Called when the mouse is clicked.
     */
    public void mouseClicked(int i, int j, int k)
    {
        super.mouseClicked(i, j, k);
        posX.mouseClicked(i, j, k);
        posY.mouseClicked(i, j, k);
        posZ.mouseClicked(i, j, k);
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        posX.updateScreen();
        posY.updateScreen();
        posZ.updateScreen();
    }

    /**
     * Returns true if this GUI should pause the game when it is displayed in single-player
     */
    public boolean doesGuiPauseGame()
    {
        return CheatPack.pausegame;
    }

    public void selectNextField()
    {
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    public void onGuiClosed()
    {
        super.onGuiClosed();
        Keyboard.enableRepeatEvents(false);
    }
}
