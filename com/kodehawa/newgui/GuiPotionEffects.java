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
import net.minecraft.src.Potion;
import net.minecraft.src.PotionEffect;
import net.minecraft.src.StringTranslate;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.kodehawa.CheatPack;

public class GuiPotionEffects extends GuiScreen
{
    private GuiScreen parentScreen;
    private GuiListControl effect;
    private GuiValueField level;
    private GuiValueField minutes;
    private GuiValueField seconds;
    private static final int xSize = 218;
    private static final int ySize = 148;

    public GuiPotionEffects(GuiScreen guiscreen)
    {
        parentScreen = guiscreen;
        effect = new GuiListControl(Potion.potionTypes);
        effect.prev = CheatPack.translate("ListControl.Previous");
        effect.next = CheatPack.translate("ListControl.Next");
        effect.focused = true;
    }

    private int[] loadValues(int i)
    {
        PotionEffect potioneffect = CheatPack.coreBase.getPlayer().getActivePotionEffect(Potion.potionTypes[i]);
        int j = potioneffect == null ? 0 : potioneffect.getAmplifier();
        int ai[] = potioneffect == null ? (new int[]
                {
                    0, 0
                }) : CheatPack.ticksToTime(potioneffect.getDuration());
        return (new int[]
                {
                    j, ai[0], ai[1]
                });
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        Keyboard.enableRepeatEvents(true);
        int i = (int)((float)(width - 218) * 0.5F);
        int j = (int)((float)(height - 148) * 0.5F);
        buttonList.addAll(effect.initGui(0, 1, 2, StringTranslate.getInstance().translateKey(Potion.potionTypes[effect.index].getName()), i + 9, j + 9, 200));
        int ai[] = loadValues(effect.index);
        level = new GuiValueField(new IntBuilder(ai[0]), fontRenderer, i + 9, j + 35, 200, 20);
        minutes = new GuiValueField(new IntBuilder(ai[1], 0, 0, 0x1b4e46), fontRenderer, i + 9, j + 61, 200, 20);
        seconds = new GuiValueField(new IntBuilder(ai[2], 0, 0, 59), fontRenderer, i + 9, j + 87, 200, 20);
        buttonList.add(new GuiButton(200, i + 9, j + 120, 97, 20, CheatPack.translate("GuiPotionEffects.ButtonSet")));
        buttonList.add(new GuiButton(201, i + 112, j + 120, 97, 20, CheatPack.translate("GuiPotionEffects.ButtonUnset")));
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int i, int j, float f)
    {
        drawDefaultBackground();
        this.mc.renderEngine.bindTexture("/CP2/resources/guipotioneffects.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        
        int l = (width - 218) / 2;
        int i1 = (height - 148) / 2;
        drawTexturedModalRect(l, i1, 0, 0, 218, 148);
        level.drawScreen(i, j, f);
        minutes.drawScreen(i, j, f);
        seconds.drawScreen(i, j, f);
        effect.setInfo(StringTranslate.getInstance().translateKey(Potion.potionTypes[effect.index].getName()));
        drawCenteredString(fontRenderer, CheatPack.translate("GuiPotionEffects.LabelTitle"), width / 2, 20, 0xffffff);
        drawCenteredString(fontRenderer, CheatPack.translate("GuiPotionEffects.LabelLevel"), width / 2, i1 + 35 + 5, 0xffffff);
        drawCenteredString(fontRenderer, CheatPack.translate("GuiPotionEffects.LabelMinutes"), width / 2, i1 + 61 + 5, 0xffffff);
        drawCenteredString(fontRenderer, CheatPack.translate("GuiPotionEffects.LabelSeconds"), width / 2, i1 + 87 + 5, 0xffffff);
        super.drawScreen(i, j, f);
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    protected void actionPerformed(GuiButton guibutton)
    {
        effect.actionPerformed(guibutton, Potion.potionTypes);

        if (guibutton.id == 0 || guibutton.id == 2)
        {
            int ai[] = loadValues(effect.index);
            level.getIntBuilder().setValue(Integer.valueOf(ai[0]));
            minutes.getIntBuilder().setValue(Integer.valueOf(ai[1]));
            seconds.getIntBuilder().setValue(Integer.valueOf(ai[2]));
        }

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
        if (flag && level.toInt() >= level.getIntBuilder().min)
        {
            if (minutes.toInt() <= minutes.getIntBuilder().min && seconds.toInt() <= seconds.getIntBuilder().min)
            {
                minutes.getIntBuilder().setInt(minutes.getIntBuilder().max);
                seconds.getIntBuilder().setInt(seconds.getIntBuilder().max);
            }

            CheatPack.getBase(mc).setPotionEffect(mc.thePlayer, new PotionEffect(effect.index, CheatPack.timeToTicks(minutes.toInt(), seconds.toInt()), level.toInt()));
        }
        else
        {
            CheatPack.getBase(mc).unsetPotionEffect(mc.thePlayer, effect.index);
            level.getIntBuilder().setInt(0);
            minutes.getIntBuilder().setInt(0);
            seconds.getIntBuilder().setInt(0);
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
            level.keyTyped(c, i);
            minutes.keyTyped(c, i);
            seconds.keyTyped(c, i);
            effect.keyTyped(c, i, Potion.potionTypes, CheatPack.prevKey, CheatPack.nextKey, mc.sndManager);

            if (i == CheatPack.setKey || i == CheatPack.unsetKey)
            {
                mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
                applyChanges(i == CheatPack.setKey);
            }
        }
    }

    /**
     * Called when the mouse is clicked.
     */
    public void mouseClicked(int i, int j, int k)
    {
        super.mouseClicked(i, j, k);
        level.mouseClicked(i, j, k);
        minutes.mouseClicked(i, j, k);
        seconds.mouseClicked(i, j, k);
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        level.updateScreen();
        minutes.updateScreen();
        seconds.updateScreen();
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
