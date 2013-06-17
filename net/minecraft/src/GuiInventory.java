package net.minecraft.src;

import java.util.Collection;
import java.util.Iterator;

import net.minecraft.client.Minecraft;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.kodehawa.CheatPack;
import com.kodehawa.newgui.ContainerBrewing;
import com.kodehawa.newgui.ContainerEnchanting;
import com.kodehawa.newgui.GuiBrewing;
import com.kodehawa.newgui.GuiEnchanting;
import com.kodehawa.newgui.GuiExperience;
import com.kodehawa.newgui.GuiHealthFood;
import com.kodehawa.newgui.GuiImageButton;
import com.kodehawa.newgui.GuiItemSelection;
import com.kodehawa.newgui.GuiMoreOptions;
import com.kodehawa.newgui.GuiPotionEffects;
import com.kodehawa.newgui.GuiTeleport;

/*public class GuiInventory extends InventoryEffectRenderer
{
    /**
     * x size of the inventory window in pixels. Defined as float, passed as int
     */
   /* private float xSize_lo;
    private boolean enabled;

    /**
     * y size of the inventory window in pixels. Defined as float, passed as int.
     */
   /* private float ySize_lo;

    public GuiInventory(EntityPlayer par1EntityPlayer)
    {
        super(par1EntityPlayer.inventoryContainer);
        this.allowUserInput = true;
        par1EntityPlayer.addStat(AchievementList.openInventory, 1);
    }

    /**
     * Called from the main game loop to update the screen.
     */
  /*  public void updateScreen()
    {
        if (this.mc.playerController.isInCreativeMode())
        {
            this.mc.displayGuiScreen(new GuiContainerCreative(this.mc.thePlayer));
        }
        this.buttonList.clear();
        if (enabled)
        {
            setupMenuBar(CheatPack.showgui);
            System.out.println("Cheat Pack 2 GUI Init System");
        }
        if (CheatPack.showgui && enabled)
        {
            drawCenteredString(fontRenderer, "Cheat Pack 2.3 #4 by Kodehawa", width / 2, 2, 0x80a0a0);
        }
        int i = (int)((float)(width - xSize) * 0.5F);
        for (int j = 0; j < 9; j++)
        {
        GuiImageButton guiimagebutton = (new GuiImageButton(j, i + 18 * j + 9, 12, 18, 18)).setImageData("/CP2/resources/menuitems.png", 16 * j, 0, 16, 16).setImageOffset(1, 1);
        }
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    /*public void initGui()
    {
    	enabled = CheatPack.isEnabled(mc.theWorld.isRemote);
        this.buttonList.clear();

        if (enabled)
        {
            setupMenuBar(CheatPack.showgui);
            System.out.println("Cheat Pack 2 GUI Init System");
        }
        
        if (!mc.thePlayer.getActivePotionEffects().isEmpty())
        {
            guiLeft = 160 + (width - xSize - 200) / 2;
        }
        
        
        
        if (this.mc.playerController.isInCreativeMode())
        {
            this.mc.displayGuiScreen(new GuiContainerCreative(this.mc.thePlayer));
        }
        else
        {
            super.initGui();
        }
        
        
    }
    
    private void setupMenuBar(boolean flag)
    {
        if (flag)
        {
            int i = (int)((float)(width - xSize) * 0.5F);

            for (int j = 0; j < 9; j++)
            {
                GuiImageButton guiimagebutton = (new GuiImageButton(j, i + 18 * j + 9, 12, 18, 18)).setImageData("/CP2/resources/menuitems.png", 16 * j, 0, 16, 16).setImageOffset(1, 1);

                if (mc.theWorld.isRemote && (guiimagebutton.id == 0 || guiimagebutton.id == 2 || guiimagebutton.id == 5))
                {
                    guiimagebutton.enabled = true;
                }

                if (guiimagebutton.id == 7)
                {
                    guiimagebutton.enabled = true;
                }

                buttonList.add(j, guiimagebutton);
            }
        }
        else
        {
            buttonList.clear();
        }
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    /*protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
    	fontRenderer.drawString(CheatPack.translate("GuiInventory.LabelCrafting"), 86, 16, 0x404040);
    }

    /**
     * Draws the screen and all the components in it.
     */
   /* public void drawScreen(int par1, int par2, float par3)
    {
    	
    	if (CheatPack.showgui && enabled)
        {
            drawCenteredString(fontRenderer, "Cheat Pack 2.3 #4 by Kodehawa", width / 2, 2, 0x80a0a0);
        }
    	
        super.drawScreen(par1, par2, par3);
        this.xSize_lo = (float)par1;
        this.ySize_lo = (float)par2;
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
   /* protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture("/gui/inventory.png");
        int var4 = this.guiLeft;
        int var5 = this.guiTop;
        this.drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.ySize);
        drawPlayerOnGui(this.mc, var4 + 51, var5 + 75, 30, (float)(var4 + 51) - this.xSize_lo, (float)(var5 + 75 - 50) - this.ySize_lo);
    }

    public static void drawPlayerOnGui(Minecraft par0Minecraft, int par1, int par2, int par3, float par4, float par5)
    {
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)par1, (float)par2, 50.0F);
        GL11.glScalef((float)(-par3), (float)par3, (float)par3);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        float var6 = par0Minecraft.thePlayer.renderYawOffset;
        float var7 = par0Minecraft.thePlayer.rotationYaw;
        float var8 = par0Minecraft.thePlayer.rotationPitch;
        GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-((float)Math.atan((double)(par5 / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
        par0Minecraft.thePlayer.renderYawOffset = (float)Math.atan((double)(par4 / 40.0F)) * 20.0F;
        par0Minecraft.thePlayer.rotationYaw = (float)Math.atan((double)(par4 / 40.0F)) * 40.0F;
        par0Minecraft.thePlayer.rotationPitch = -((float)Math.atan((double)(par5 / 40.0F))) * 20.0F;
        par0Minecraft.thePlayer.rotationYawHead = par0Minecraft.thePlayer.rotationYaw;
        GL11.glTranslatef(0.0F, par0Minecraft.thePlayer.yOffset, 0.0F);
        RenderManager.instance.playerViewY = 180.0F;
        RenderManager.instance.renderEntityWithPosYaw(par0Minecraft.thePlayer, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
        par0Minecraft.thePlayer.renderYawOffset = var6;
        par0Minecraft.thePlayer.rotationYaw = var7;
        par0Minecraft.thePlayer.rotationPitch = var8;
        GL11.glPopMatrix();
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }
    
   
  
    
    
    
    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
   /* public void keyTyped(char c, int i)
    {
        super.keyTyped(c, i);

        if (enabled)
        {
            if (i == 57)
            {
                CheatPack.showgui = !CheatPack.showgui;
                setupMenuBar(CheatPack.showgui);
            }

            byte byte0 = 2;

            if (CheatPack.showgui && i > 1 && i < 11 && ((GuiImageButton)buttonList.get(i - 2)).enabled)
            {
                selectGui(i, 2);
            }
        }
    }
    private void selectGui(int i, int j)
    {
        Object obj = null;

        if (i == 0 + j)
        {
            obj = new GuiItemSelection(this, mc.thePlayer);
        }

        if (i == 1 + j)
        {
            obj = new GuiHealthFood(this);
        }

        if (i == 2 + j)
        {
            obj = new GuiEnchanting(this, new ContainerEnchanting(mc.thePlayer.inventory));
        }

        if (i == 3 + j)
        {
            obj = new GuiExperience(this);
        }

        if (i == 4 + j)
        {
            obj = new GuiPotionEffects(this);
        }

        if (i == 5 + j)
        {
            obj = new GuiBrewing(this, new ContainerBrewing(mc.thePlayer.inventory));
        }

        if (i == 6 + j)
        {
            obj = new GuiTeleport(this);
        }

        if (i == 7 + j)
        {
            obj = null;
        }

        if (i == 8 + j)
        {
            obj = new GuiMoreOptions(this);
        }

        if (obj != null)
        {
            mc.displayGuiScreen(((GuiScreen)(obj)));
        }
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
  /*  public void onGuiClosed()
    {
        CheatPack.saveSettings();
    }


    /**
     * Returns true if this GUI should pause the game when it is displayed in single-player
     */
   /* public boolean doesGuiPauseGame()
    {
        return CheatPack.pausegame;
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
   /* protected void actionPerformed(GuiButton par1GuiButton)
    {
    	if (CheatPack.showgui && enabled)
      {
          selectGui(par1GuiButton.id, 0);
      }
        
    /*	
        if (par1GuiButton.id == 0)
        {
            this.mc.displayGuiScreen(new GuiAchievements(this.mc.statFileWriter));
        }

        if (par1GuiButton.id == 1)
        {
            this.mc.displayGuiScreen(new GuiStats(this, this.mc.statFileWriter));
        }*/
   public class GuiInventory extends GuiContainer  {
    
    	   
    	   /**
          * x size of the inventory window in pixels. Defined as float, passed as int
          */
    private float xSize_lo;

    /**
     * y size of the inventory window in pixels. Defined as float, passed as int.
     */
    private float ySize_lo;
    private boolean enabled;

    public GuiInventory(EntityPlayer par1EntityPlayer)
    {
    	super(par1EntityPlayer.inventoryContainer);
        allowUserInput = true;
        par1EntityPlayer.addStat(AchievementList.openInventory, 1);
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    
    public void initGui()
    {
        super.initGui();
        //Init GUI
        enabled = CheatPack.isEnabled(mc.theWorld.isRemote);

        if (enabled)
        {
            setupMenuBar(CheatPack.showgui);
        }
        
        if (!mc.thePlayer.getActivePotionEffects().isEmpty())
        {
            guiLeft = 160 + (width - xSize - 200) / 2;
        }

        
    }

    private void setupMenuBar(boolean flag)
    {
        if (flag)
        {
            int i = (int)((float)(width - xSize) * 0.5F);

            for (int j = 0; j < 9; j++)
            {
                GuiImageButton guiimagebutton = (new GuiImageButton(j, i + 18 * j + 9, 12, 18, 18)).setImageData("/CP2/resources/menuitems.png", 16 * j, 0, 16, 16).setImageOffset(1, 1);

                if (mc.theWorld.isRemote && (guiimagebutton.id == 0 || guiimagebutton.id == 2 || guiimagebutton.id == 5))
                {
                    guiimagebutton.enabled = false;
                }

                if (guiimagebutton.id == 7)
                {
                    guiimagebutton.enabled = false;
                }

                buttonList.add(j, guiimagebutton);
            }
        }
        else
        {
            buttonList.clear();
        }
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer()
    {
    	//Draw a inexistent thing xD
        fontRenderer.drawString(CheatPack.translate("GuiInventory.LabelCrafting"), 86, 16, 0x404040);
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int par1, int par2, float par3)
    {
         super.drawScreen(par1, par2, par3);
        xSize_lo = par1;
        ySize_lo = par2;
        if (CheatPack.showgui && enabled)
        {
            drawCenteredString(fontRenderer, "Cheat Pack 2.3 #4 by Kodehawa", width / 2, 2, 0x80a0a0);
        }
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        int i = mc.renderEngine.getTexture("/gui/inventory.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(i);
        int j = guiLeft;
        int k = guiTop;
        drawTexturedModalRect(j, k, 0, 0, xSize, ySize);
        displayDebuffEffects();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glPushMatrix();
        GL11.glTranslatef(j + 51, k + 75, 50F);
        float f = 30F;
        GL11.glScalef(-f, f, f);
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        float f1 = mc.thePlayer.renderYawOffset;
        float f2 = mc.thePlayer.rotationYaw;
        float f3 = mc.thePlayer.rotationPitch;
        float f4 = (float)(j + 51) - xSize_lo;
        float f5 = (float)((k + 75) - 50) - ySize_lo;
        GL11.glRotatef(135F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GL11.glRotatef(-135F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-(float)Math.atan(f5 / 40F) * 20F, 1.0F, 0.0F, 0.0F);
        mc.thePlayer.renderYawOffset = (float)Math.atan(f4 / 40F) * 20F;
        mc.thePlayer.rotationYaw = (float)Math.atan(f4 / 40F) * 40F;
        mc.thePlayer.rotationPitch = -(float)Math.atan(f5 / 40F) * 20F;
        mc.thePlayer.rotationYawHead = mc.thePlayer.rotationYaw;
        GL11.glTranslatef(0.0F, mc.thePlayer.yOffset, 0.0F);
        RenderManager.instance.playerViewY = 180F;
        RenderManager.instance.renderEntityWithPosYaw(mc.thePlayer, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
        mc.thePlayer.renderYawOffset = f1;
        mc.thePlayer.rotationYaw = f2;
        mc.thePlayer.rotationPitch = f3;
        GL11.glPopMatrix();
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    protected void actionPerformed(GuiButton par1GuiButton)
    {
    	//Enable selectGui method
        if (CheatPack.showgui && enabled)
        {
            selectGui(par1GuiButton.id, 0);
        }
    }

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    public void keyTyped(char c, int i)
    {
        super.keyTyped(c, i);

        if (enabled)
        {
            if (i == 57)
            {
            	//If space it's pressed hide the CP2 GUI
                CheatPack.showgui = !CheatPack.showgui;
                setupMenuBar(CheatPack.showgui);
            }

            byte byte0 = 2;

            if (CheatPack.showgui && i > 1 && i < 11 && ((GuiImageButton)buttonList.get(i - 2)).enabled)
            {
                selectGui(i, 2);
            }
        }
    }

    /**
     * Returns true if this GUI should pause the game when it is displayed in single-player
     */
    public boolean doesGuiPauseGame()
    {
        return CheatPack.pausegame;
    }

    //How many GUI's created Binkan? He's crazy.
    private void selectGui(int i, int j)
    {
        Object obj = null;

        if (i == 0 + j)
        {
            obj = new GuiItemSelection(this, mc.thePlayer);
        }

        if (i == 1 + j)
        {
            obj = new GuiHealthFood(this);
        }

        if (i == 2 + j)
        {
            obj = new GuiEnchanting(this, new ContainerEnchanting(mc.thePlayer.inventory));
        }

        if (i == 3 + j)
        {
            obj = new GuiExperience(this);
        }

        if (i == 4 + j)
        {
            obj = new GuiPotionEffects(this);
        }

        if (i == 5 + j)
        {
            obj = new GuiBrewing(this, new ContainerBrewing(mc.thePlayer.inventory));
        }

        if (i == 6 + j)
        {
            obj = new GuiTeleport(this);
        }

        if (i == 7 + j)
        {
            obj = null;
        }

        if (i == 8 + j)
        {
            obj = new GuiMoreOptions(this);
        }

        if (obj != null)
        {
            mc.displayGuiScreen(((GuiScreen)(obj)));
        }
    }
    
    public static void drawPlayerOnGui(Minecraft par0Minecraft, int par1, int par2, int par3, float par4, float par5)
    {
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)par1, (float)par2, 50.0F);
        GL11.glScalef((float)(-par3), (float)par3, (float)par3);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        float var6 = par0Minecraft.thePlayer.renderYawOffset;
        float var7 = par0Minecraft.thePlayer.rotationYaw;
        float var8 = par0Minecraft.thePlayer.rotationPitch;
        GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-((float)Math.atan((double)(par5 / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
        par0Minecraft.thePlayer.renderYawOffset = (float)Math.atan((double)(par4 / 40.0F)) * 20.0F;
        par0Minecraft.thePlayer.rotationYaw = (float)Math.atan((double)(par4 / 40.0F)) * 40.0F;
        par0Minecraft.thePlayer.rotationPitch = -((float)Math.atan((double)(par5 / 40.0F))) * 20.0F;
        par0Minecraft.thePlayer.rotationYawHead = par0Minecraft.thePlayer.rotationYaw;
        GL11.glTranslatef(0.0F, par0Minecraft.thePlayer.yOffset, 0.0F);
        RenderManager.instance.playerViewY = 180.0F;
        RenderManager.instance.renderEntityWithPosYaw(par0Minecraft.thePlayer, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
        par0Minecraft.thePlayer.renderYawOffset = var6;
        par0Minecraft.thePlayer.rotationYaw = var7;
        par0Minecraft.thePlayer.rotationPitch = var8;
        GL11.glPopMatrix();
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    public void onGuiClosed()
    {
    	//Save CP2 Settings
        CheatPack.saveSettings();
    }

    /**
     * Displays debuff/potion effects that are currently being applied to the player
     */
    private void displayDebuffEffects()
    {
        int i = guiLeft - 124;
        int j = guiTop;
        int k = mc.renderEngine.getTexture("/gui/inventory.png");
        Collection collection = mc.thePlayer.getActivePotionEffects();

        if (collection.isEmpty())
        {
            return;
        }

        int l = 33;

        if (collection.size() > 5)
        {
            l = 132 / (collection.size() - 1);
        }
        
        //Potions Iterator system.

        for (Iterator iterator = mc.thePlayer.getActivePotionEffects().iterator(); iterator.hasNext();)
        {
            PotionEffect potioneffect = (PotionEffect)iterator.next();
            Potion potion = Potion.potionTypes[potioneffect.getPotionID()];
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            mc.renderEngine.bindTexture(k);
            drawTexturedModalRect(i, j, 0, ySize, 140, 32);

            if (potion.hasStatusIcon())
            {
                int i1 = potion.getStatusIconIndex();
                drawTexturedModalRect(i + 6, j + 7, 0 + (i1 % 8) * 18, ySize + 32 + (i1 / 8) * 18, 18, 18);
            }

            String s = (new StringBuilder()).append(StatCollector.translateToLocal(potion.getName())).append(' ').append(potioneffect.getAmplifier() + 1).toString();
            fontRenderer.drawStringWithShadow(s, i + 10 + 18, j + 6, 0xffffff);
            String s1 = Potion.getDurationString(potioneffect);
            fontRenderer.drawStringWithShadow(s1, i + 10 + 18, j + 6 + 10, 0x7f7f7f);
            j += l;
        }
    }
}

