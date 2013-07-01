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

import net.minecraft.src.AchievementList;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.GuiAchievements;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.GuiStats;
import net.minecraft.src.InventoryBasic;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ResourceLocation;
import net.minecraft.src.Slot;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.kodehawa.CheatPack;

public class GuiItemSelection extends GuiContainer
{
    private GuiScreen parentScreen;
    private float i;
    private static boolean j;
    private static boolean k;
    private static InventoryBasic inventory = new InventoryBasic("tmp", k, 72);
    protected static final ResourceLocation field_CP2_oi = new ResourceLocation("/gui/allitems.png");
    

    public GuiItemSelection(GuiScreen guiscreen, EntityPlayer entityplayer)
    {
        super(new ContainerItemSelection(entityplayer));
        parentScreen = guiscreen;
        i = 0.0F;
        j = false;
        entityplayer.inventoryContainer = inventorySlots;
        allowUserInput = true;
        entityplayer.addStat(AchievementList.openInventory, 1);
        ySize = 208;
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
    }

    protected void handleMouseClick(Slot slot, int l, int i1, boolean flag)
    {
        if (slot != null)
        {
            if (slot.inventory == inventory)
            {
                InventoryPlayer inventoryplayer = mc.thePlayer.inventory;
                ItemStack itemstack1 = inventoryplayer.getItemStack();
                ItemStack itemstack3 = slot.getStack();

                if (itemstack1 != null && itemstack3 != null && itemstack1.itemID == itemstack3.itemID)
                {
                    if (i1 == 0)
                    {
                        if (flag)
                        {
                            itemstack1.stackSize = itemstack1.getMaxStackSize();
                        }
                        else if (itemstack1.stackSize < itemstack1.getMaxStackSize())
                        {
                            itemstack1.stackSize++;
                        }
                    }
                    else if (itemstack1.stackSize <= 1)
                    {
                        inventoryplayer.setItemStack(null);
                    }
                    else
                    {
                        itemstack1.stackSize--;
                    }
                }
                else if (itemstack1 != null)
                {
                    inventoryplayer.setItemStack(null);
                }
                else if (itemstack3 == null)
                {
                    inventoryplayer.setItemStack(null);
                }
                else if (itemstack1 == null || itemstack1.itemID != itemstack3.itemID)
                {
                    inventoryplayer.setItemStack(ItemStack.copyItemStack(itemstack3));
                    ItemStack itemstack4 = inventoryplayer.getItemStack();

                    if (flag)
                    {
                        itemstack4.stackSize = itemstack4.getMaxStackSize();
                    }
                }
            }
            else
            {
                inventorySlots.slotClick(slot.slotNumber, i1, i1, mc.thePlayer);
                ItemStack itemstack = inventorySlots.getSlot(slot.slotNumber).getStack();
                CheatPack.getBase(mc).setItemSelectionSlot(itemstack, (slot.slotNumber - inventorySlots.inventorySlots.size()) + 9 + 36);
            }
        }
        else
        {
            InventoryPlayer inventoryplayer1 = mc.thePlayer.inventory;

            if (inventoryplayer1.getItemStack() != null)
            {
                if (i1 == 0)
                {
                    mc.thePlayer.dropPlayerItem(inventoryplayer1.getItemStack());
                    CheatPack.getBase(mc).setItemSelectionSlot(inventoryplayer1.getItemStack(), -1);
                    inventoryplayer1.setItemStack(null);
                }

                if (i1 == 1)
                {
                    ItemStack itemstack2 = inventoryplayer1.getItemStack().splitStack(1);
                    mc.thePlayer.dropPlayerItem(itemstack2);
                    CheatPack.getBase(mc).setItemSelectionSlot(itemstack2, -1);

                    if (inventoryplayer1.getItemStack().stackSize == 0)
                    {
                        inventoryplayer1.setItemStack(null);
                    }
                }
            }
        }
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        super.initGui();
        buttonList.clear();
    }

    /**
     * Draw the foreground layer for the GuiContainer (everythin in front of the items)
     */
    protected void drawGuiContainerForegroundLayer()
    {
        fontRenderer.drawString(CheatPack.translate("GuiItemSelection.LabelItemSelection"), 8, 6, 0x404040);
    }

    /**
     * Returns true if this GUI should pause the game when it is displayed in single-player
     */
    public boolean doesGuiPauseGame()
    {
        return CheatPack.pausegame;
    }

    /**
     * Handles mouse input.
     */
    public void handleMouseInput()
    {
        super.handleMouseInput();
        int l = Mouse.getEventDWheel();

        if (l != 0)
        {
            int i1 = (((ContainerItemSelection)inventorySlots).a.size() / 8 - 8) + 1;

            if (l > 0)
            {
                l = 1;
            }

            if (l < 0)
            {
                l = -1;
            }

            i -= (double)l / (double)i1;

            if (i < 0.0F)
            {
                i = 0.0F;
            }

            if (i > 1.0F)
            {
                i = 1.0F;
            }

            ((ContainerItemSelection)inventorySlots).a(i);
        }
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int l, int i1, float f)
    {
        boolean flag = Mouse.isButtonDown(0);
        int j1 = guiLeft;
        int k1 = guiTop;
        int l1 = j1 + 155;
        int i2 = k1 + 17;
        int j2 = l1 + 14;
        int k2 = i2 + 160 + 2;

        if (!k && flag && l >= l1 && i1 >= i2 && l < j2 && i1 < k2)
        {
            j = true;
        }

        if (!flag)
        {
            j = false;
        }

        k = flag;

        if (j)
        {
            i = (float)(i1 - (i2 + 8)) / ((float)(k2 - i2) - 16F);

            if (i < 0.0F)
            {
                i = 0.0F;
            }

            if (i > 1.0F)
            {
                i = 1.0F;
            }

            ((ContainerItemSelection)inventorySlots).a(i);
        }

        super.drawScreen(l, i1, f);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_LIGHTING);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float f, int l, int i1)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        //mc.renderEngine.bindTexture("/gui/allitems.png");
    	mc.func_110434_K().func_110577_a(field_CP2_oi);

        int k1 = guiLeft;
        int l1 = guiTop;
        drawTexturedModalRect(k1, l1, 0, 0, xSize, ySize);
        int i2 = k1 + 155;
        int j2 = l1 + 17;
        int k2 = j2 + 160 + 2;
        drawTexturedModalRect(k1 + 154, l1 + 17 + (int)((float)(k2 - j2 - 17) * i), 0, 208, 16, 16);
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    protected void actionPerformed(GuiButton guibutton)
    {
        if (guibutton.id == 0)
        {
            mc.displayGuiScreen(new GuiAchievements(mc.statFileWriter));
        }

        if (guibutton.id == 1)
        {
            mc.displayGuiScreen(new GuiStats(this, mc.statFileWriter));
        }
    }

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    public void keyTyped(char c, int l)
    {
        if (!CheatPack.escapeGui(mc, l, parentScreen))
        {
            super.keyTyped(c, l);
        }
    }

    static InventoryBasic g()
    {
        return inventory;
    }
}
