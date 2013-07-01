

package com.kodehawa.newgui;

import net.minecraft.src.Enchantment;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ResourceLocation;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.kodehawa.CheatPack;

public class GuiEnchanting extends GuiContainer
{
    private GuiScreen parentScreen;
    private GuiButton pressedbutton;
    private InventoryEnchanting invEnch;
    private static GuiListControl enchantment;
    private static GuiValueField level;
    private boolean enchpossible;
    public static final ResourceLocation field_CP2_oi = new ResourceLocation("/CP2/resources/guienchanting.png");

    public GuiEnchanting(GuiScreen guiscreen, ContainerEnchanting containerenchanting)
    {
        super(containerenchanting);
        enchpossible = true;
        xSize = 218;
        ySize = 172;
        parentScreen = guiscreen;
        invEnch = containerenchanting.invEnch;
        enchantment = new GuiListControl(Enchantment.enchantmentsList);
        enchantment.prev = CheatPack.translate("ListControl.Previous");
        enchantment.next = CheatPack.translate("ListControl.Next");
        enchantment.focused = true;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        super.initGui();
        int i = (width - xSize) / 2;
        int j = (height - ySize) / 2;
        level = new GuiValueField(new IntBuilder(127, -128, 127), fontRenderer, i + 9, j + 91 + 26, 200, 20);
        //buttonList.addAll(enchantment.initGui(0, 1, 2, StringTranslate.getInstance().translateKey(Enchantment.enchantmentsList[enchantment.index].getName()), i + 9, j + 91 + 0, 200));
        buttonList.add(new GuiButton(200, i + 9, j + 91 + 52, 97, 20, CheatPack.translate("GuiPotionEffects.ButtonSet")));
        buttonList.add(new GuiButton(201, i + 112, j + 91 + 52, 97, 20, CheatPack.translate("GuiPotionEffects.ButtonUnset")));
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int i, int j, float f)
    {
        //enchantment.setInfo(StringTranslate.getInstance().translateKey(Enchantment.enchantmentsList[enchantment.index].getName()));
        super.drawScreen(i, j, f);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    public void drawGuiContainerBackgroundLayer(float f, int i, int j)
    {
        
    	mc.func_110434_K().func_110577_a(field_CP2_oi);
        //mc.renderEngine.bindTexture("/CP2/resources/guienchanting.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        
        drawTexturedModalRect((width - xSize) / 2, (height - ySize) / 2, 0, 0, xSize, ySize);
        level.drawScreen(i, j, f);
    }

    /**
     * Draw the foreground layer for the GuiContainer (everythin in front of the items)
     */
    protected void drawGuiContainerForegroundLayer()
    {
        drawCenteredString(fontRenderer, CheatPack.translate("GuiEnchanting.LabelTitle"), xSize / 2, -((height - ySize) / 2) + 20, 0xffffff);
        drawCenteredString(fontRenderer, CheatPack.translate("GuiEnchanting.LabelLevel"), xSize / 2, 122, 0xffffff);
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
            enchantment.keyTyped(c, i, Enchantment.enchantmentsList, CheatPack.prevKey, CheatPack.nextKey, mc.sndManager);

            if (i == CheatPack.setKey || i == CheatPack.unsetKey)
            {
                mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
                applyChanges(i == CheatPack.setKey);
            }
        }
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    public void onGuiClosed()
    {
        super.onGuiClosed();

        for (int i = 0; i < 4; i++)
        {
            ItemStack itemstack = invEnch.getStackInSlot(i);

            if (itemstack != null)
            {
                CheatPack.coreBase.getPlayer().dropItem(itemstack.itemID, 1);
                CheatPack.coreBase.getPlayer().inventory.setInventorySlotContents(i, null);
            }
        }

        Keyboard.enableRepeatEvents(false);
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    protected void actionPerformed(GuiButton guibutton)
    {
        enchantment.actionPerformed(guibutton, Enchantment.enchantmentsList);

        if (guibutton.id == 200 || guibutton.id == 201)
        {
            enchpossible = !enchpossible;

            if (enchpossible)
            {
                applyChanges(guibutton.id == 200);
            }
        }
    }

    private void applyChanges(boolean flag)
    {
        for (int i = 0; i < 4; i++)
        {
            if (invEnch.getStackInSlot(i) == null)
            {
                continue;
            }

            if (flag)
            {
                CheatPack.getBase(mc).setEnchantment(invEnch.getStackInSlot(i), Enchantment.enchantmentsList[enchantment.index], level.toInt());
            }
            else
            {
                CheatPack.getBase(mc).unsetEnchantment(invEnch.getStackInSlot(i), enchantment.index);
            }
        }
    }

    /**
     * Called when the mouse is clicked.
     */
    protected void mouseClicked(int i, int j, int k)
    {
        if (k == 0)
        {
            for (int l = 0; l < buttonList.size(); l++)
            {
                GuiButton guibutton = (GuiButton)buttonList.get(l);

                if (guibutton.mousePressed(mc, i, j))
                {
                    pressedbutton = guibutton;
                    mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
                    actionPerformed(guibutton);
                }
            }
        }

        level.mouseClicked(i, j, k);
        super.mouseClicked(i, j, k);
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        level.updateScreen();
    }

    /**
     * Called when the mouse is moved or a mouse button is released.  Signature: (mouseX, mouseY, which) which==-1 is
     * mouseMove, which==0 or which==1 is mouseUp
     */
    protected void mouseMovedOrUp(int i, int j, int k)
    {
        if (pressedbutton != null && k == 0)
        {
            pressedbutton.mouseReleased(i, j);
            pressedbutton = null;
        }

        super.mouseMovedOrUp(i, j, k);
    }

    /**
     * Returns true if this GUI should pause the game when it is displayed in single-player
     */
    public boolean doesGuiPauseGame()
    {
        return CheatPack.pausegame;
    }
}
