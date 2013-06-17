package com.kodehawa.newgui;

import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;

import org.lwjgl.opengl.GL11;

import com.kodehawa.CheatPack;

public class GuiExperience extends GuiScreen
{
    private GuiScreen parentScreen;
    private static GuiValueField level;
    private static GuiValueField percentage;
    //private static GuiValueField score;
    private static final int xSize = 218;
    private static final int ySize = 124;

    public GuiExperience(GuiScreen guiscreen)
    {
        parentScreen = guiscreen;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        int i = (width - 218) / 2;
        int j = (height - 124) / 2;
        buttonList.add(new GuiButton(200, i + 9, j + 94, CheatPack.translate("GuiExperience.ButtonSet")));
        level = new GuiValueField(new IntBuilder(CheatPack.coreBase.getPlayer().experienceLevel), fontRenderer, i + 9, j + 9, 200, 20);
        percentage = new GuiValueField(new FloatBuilder(CheatPack.coreBase.getPlayer().experience), fontRenderer, i + 9, j + 35, 200, 20);
        //score = new GuiValueField(new IntBuilder((int) CheatPack.coreBase.getPlayer().experience), fontRenderer, i + 9, j + 61, 200, 20);
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int i, int j, float f)
    {
        drawDefaultBackground();
        int k = mc.renderEngine.getTexture("/CheatPackRessources/guiexperience.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(k);
        int l = (width - 218) / 2;
        int i1 = (height - 124) / 2;
        drawTexturedModalRect(l, i1, 0, 0, 218, 124);
        level.drawScreen(i, j, f);
        percentage.drawScreen(i, j, f);
        //score.drawScreen(i, j, f);
        drawCenteredString(fontRenderer, CheatPack.translate("GuiExperience.LabelTitle"), width / 2, 20, 0xffffff);
        drawCenteredString(fontRenderer, CheatPack.translate("GuiExperience.LabelLevel"), width / 2, i1 + 9 + 5, 0xffffff);
        drawCenteredString(fontRenderer, CheatPack.translate("GuiExperience.LabelPercentage"), width / 2, i1 + 35 + 5, 0xffffff);
        //drawCenteredString(fontRenderer, CheatPack.translate("GuiExperience.LabelScore"), width / 2, i1 + 61 + 5, 0xffffff);
        super.drawScreen(i, j, f);
    }

    /**
     * Returns true if this GUI should pause the game when it is displayed in single-player
     */
    public boolean doesGuiPauseGame()
    {
        return CheatPack.pausegame;
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
            percentage.keyTyped(c, i);
            //score.keyTyped(c, i);

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
        level.mouseClicked(i, j, k);
        percentage.mouseClicked(i, j, k);
        //score.mouseClicked(i, j, k);
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        level.updateScreen();
        percentage.updateScreen();
        //score.updateScreen();
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
        CheatPack.getBase(mc).setExperience(mc.thePlayer, level.toInt(), percentage.toFloat(), eventButton);
    }
}
