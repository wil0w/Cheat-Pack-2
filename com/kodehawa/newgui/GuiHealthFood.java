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

import net.minecraft.src.FoodStats;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;

import org.lwjgl.opengl.GL11;

import com.kodehawa.CheatPack;

public class GuiHealthFood extends GuiScreen
{
    private GuiScreen parentScreen;
    private GuiValueField healthlevel;
    private GuiValueField foodlevel;
    private GuiValueField saturation;
    private GuiValueField exhaustion;
    private static final int xSize = 218;
    private static final int ySize = 150;

    public GuiHealthFood(GuiScreen guiscreen)
    {
        parentScreen = guiscreen;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        int i = (width - 218) / 2;
        int j = (height - 150) / 2;
        healthlevel = new GuiValueField(new IntBuilder(CheatPack.coreBase.getPlayer().getHealth(), 20), fontRenderer, i + 9, j + 9 + 0, 200, 20);

        try
        {
            int k = CheatPack.hasZMod ? 1 : 0;
            FoodStats foodstats = (FoodStats)CheatPack.getPrivateValue(net.minecraft.src.EntityPlayer.class, CheatPack.coreBase.getPlayer(), 3 + k);
            foodlevel = new GuiValueField(new IntBuilder(foodstats.getFoodLevel(), 20, -32768, 32767), fontRenderer, i + 9, j + 9 + 26, 200, 20);
            saturation = new GuiValueField(new FloatBuilder(foodstats.getSaturationLevel(), 0.0F), fontRenderer, i + 9, j + 9 + 52, 200, 20);
            exhaustion = new GuiValueField(new FloatBuilder(((Float)CheatPack.getPrivateValue(net.minecraft.src.FoodStats.class, foodstats, 2)).floatValue(), 0.0F), fontRenderer, i + 9, j + 9 + 78, 200, 20);
        }
        catch (Exception exception)
        {
            CheatPack.coreBase.throwException("Error with getting food stats!", exception);
        }

        buttonList.add(new GuiButton(200, i + 9, j + 9 + 104 + 7, CheatPack.translate("GuiHealthFood.ButtonSet")));
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int i, int j, float f)
    {
        drawDefaultBackground();
        this.mc.renderEngine.bindTexture("/CP2/resources/guihealthfood.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        int l = (width - 218) / 2;
        int i1 = (height - 150) / 2;
        drawTexturedModalRect(l, i1, 0, 0, 218, 150);
        healthlevel.drawScreen(i, j, f);
        foodlevel.drawScreen(i, j, f);
        saturation.drawScreen(i, j, f);
        exhaustion.drawScreen(i, j, f);
        drawCenteredString(fontRenderer, CheatPack.translate("GuiHealthFood.LabelTitle"), width / 2, 20, 0xffffff);
        drawCenteredString(fontRenderer, CheatPack.translate("GuiHealthFood.LabelHealthLevel"), width / 2, i1 + 9 + 0 + 5, 0xffffff);
        drawCenteredString(fontRenderer, CheatPack.translate("GuiHealthFood.LabelFoodLevel"), width / 2, i1 + 9 + 26 + 5, 0xffffff);
        drawCenteredString(fontRenderer, CheatPack.translate("GuiHealthFood.LabelSaturation"), width / 2, i1 + 9 + 52 + 5, 0xffffff);
        drawCenteredString(fontRenderer, CheatPack.translate("GuiHealthFood.LabelExhaustion"), width / 2, i1 + 9 + 78 + 5, 0xffffff);
        super.drawScreen(i, j, f);
    }

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    public void keyTyped(char c, int i)
    {
        if (!CheatPack.escapeGui(mc, i, parentScreen))
        {
            super.keyTyped(c, i);
            healthlevel.keyTyped(c, i);
            foodlevel.keyTyped(c, i);
            saturation.keyTyped(c, i);
            exhaustion.keyTyped(c, i);

            if (i == CheatPack.setKey)
            {
                mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
                applyChanges();
            }
        }
    }

    /**
     * Called when the mouse is clicked.
     */
    public void mouseClicked(int i, int j, int k)
    {
        super.mouseClicked(i, j, k);
        healthlevel.mouseClicked(i, j, k);
        foodlevel.mouseClicked(i, j, k);
        saturation.mouseClicked(i, j, k);
        exhaustion.mouseClicked(i, j, k);
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        healthlevel.updateScreen();
        foodlevel.updateScreen();
        saturation.updateScreen();
        exhaustion.updateScreen();
    }

    /**
     * Returns true if this GUI should pause the game when it is displayed in single-player
     */
    public boolean doesGuiPauseGame()
    {
        return CheatPack.pausegame;
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    protected void actionPerformed(GuiButton guibutton)
    {
        if (guibutton.id == 200)
        {
            applyChanges();
        }
    }

    private void applyChanges()
    {
        CheatPack.getBase(mc).setHealth(mc.thePlayer, healthlevel.toInt());
        CheatPack.getBase(mc).setFoodStatValues(mc.thePlayer, foodlevel.toInt(), saturation.toFloat(), exhaustion.toFloat());
    }
}
