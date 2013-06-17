package com.kodehawa.newgui;

import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;

import org.lwjgl.opengl.GL11;

import com.kodehawa.CheatPack;

public class GuiMoreOptions extends GuiScreen
{
    private GuiScreen parentScreen;
    private static final int xSize = 256;
    private static final int ySize = 116;
    private GuiCheckBox disablefov;
    private GuiCheckBox instantdrop;
    private GuiCheckBox instantxp;
    private GuiCheckBox pausegame;
    private GuiCheckBox flying;
    private GuiCheckBox invulnerable;
    private GuiCheckBox killersight;
    private GuiCheckBox creative;

    public GuiMoreOptions(GuiScreen guiscreen)
    {
        parentScreen = guiscreen;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        int i = (width - 256) / 2;
        int j = (height - 116) / 2;
        creative = new GuiCheckBox(0, i + 9, j + 9 + 0, 116, 20, CheatPack.translate("GuiMoreOptions.ButtonCreative"), CheatPack.creative);
        flying = new GuiCheckBox(1, i + 131, j + 9 + 0, 116, 20, CheatPack.translate("GuiMoreOptions.ButtonFlying"), CheatPack.flying);
        invulnerable = new GuiCheckBox(2, i + 9, j + 9 + 26, 116, 20, CheatPack.translate("GuiMoreOptions.ButtonInvulnerable"), CheatPack.invulnerable);
        disablefov = new GuiCheckBox(3, i + 131, j + 9 + 26, 116, 20, CheatPack.translate("GuiMoreOptions.ButtonDisableFOV"), CheatPack.disablefov);
        instantdrop = new GuiCheckBox(4, i + 9, j + 9 + 52, 116, 20, CheatPack.translate("GuiMoreOptions.ButtonInstantDrops"), CheatPack.instantdrop);
        instantxp = new GuiCheckBox(5, i + 131, j + 9 + 52, 116, 20, CheatPack.translate("GuiMoreOptions.ButtonInstantExperience"), CheatPack.instantxp);
        killersight = new GuiCheckBox(6, i + 9, j + 9 + 78, 116, 20, CheatPack.translate("GuiMoreOptions.ButtonKillersight"), CheatPack.killersight);
        pausegame = new GuiCheckBox(7, i + 131, j + 9 + 78, 116, 20, CheatPack.translate("GuiMoreOptions.ButtonGuiPausingGame"), CheatPack.pausegame);
        disablefov.onText = instantdrop.onText = instantxp.onText = pausegame.onText = flying.onText = invulnerable.onText = killersight.onText = creative.onText = CheatPack.translate("CheckBox.On");
        disablefov.offText = instantdrop.offText = instantxp.offText = pausegame.offText = flying.offText = invulnerable.offText = killersight.offText = creative.offText = CheatPack.translate("CheckBox.Off");

        if (mc.theWorld.isRemote)
        {
            instantdrop.enabled = instantxp.enabled = pausegame.enabled = killersight.enabled = flying.enabled = invulnerable.enabled = false;
        }

        buttonList.add(disablefov);
        buttonList.add(instantdrop);
        buttonList.add(instantxp);
        buttonList.add(pausegame);
        buttonList.add(flying);
        buttonList.add(invulnerable);
        buttonList.add(killersight);
        buttonList.add(creative);
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int i, int j, float f)
    {
        drawDefaultBackground();
        int k = mc.renderEngine.getTexture("/CheatPackRessources/guimoreoptions.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(k);
        drawTexturedModalRect((width - 256) / 2, (height - 116) / 2, 0, 0, 256, 116);
        drawCenteredString(fontRenderer, CheatPack.translate("GuiMoreOptions.LabelTitle"), width / 2, 20, 0xffffff);
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
        }
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
    }

    /**
     * Returns true if this GUI should pause the game when it is displayed in single-player
     */
    public boolean doesGuiPauseGame()
    {
        return CheatPack.pausegame;
    }

    private void applyChanges()
    {
        CheatPack.disablefov = disablefov.isChecked();
        CheatPack.instantdrop = instantdrop.isChecked();
        CheatPack.instantxp = instantxp.isChecked();
        CheatPack.pausegame = pausegame.isChecked();
        CheatPack.flying = flying.isChecked();
        CheatPack.invulnerable = invulnerable.isChecked();
        CheatPack.killersight = killersight.isChecked();
        CheatPack.creative = creative.isChecked();
        CheatPack.loopCheats(mc);
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    public void onGuiClosed()
    {
        CheatPack.saveSettings();
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    protected void actionPerformed(GuiButton guibutton)
    {
        applyChanges();
    }
}
