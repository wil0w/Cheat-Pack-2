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

package com.kodehawa.gui.api.testing;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.lang.reflect.Field;
import java.nio.FloatBuffer;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.src.ActiveRenderInfo;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.BossStatus;
import net.minecraft.src.ClippingHelperImpl;
import net.minecraft.src.CrashReport;
import net.minecraft.src.EffectRenderer;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerSP;
import net.minecraft.src.EntityRainFX;
import net.minecraft.src.EntityRenderer;
import net.minecraft.src.EntitySmokeFX;
import net.minecraft.src.Frustrum;
import net.minecraft.src.GLAllocation;
import net.minecraft.src.GuiDownloadTerrain;
import net.minecraft.src.GuiMainMenu;
import net.minecraft.src.IntegratedServer;
import net.minecraft.src.ItemRenderer;
import net.minecraft.src.Material;
import net.minecraft.src.MathHelper;
import net.minecraft.src.MouseFilter;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.OpenGlHelper;
import net.minecraft.src.Potion;
import net.minecraft.src.RenderBlocks;
import net.minecraft.src.RenderGlobal;
import net.minecraft.src.RenderHelper;
import net.minecraft.src.ReportedException;
import net.minecraft.src.ScaledResolution;
import net.minecraft.src.Tessellator;
import net.minecraft.src.Vec3;
import net.minecraft.src.World;
import net.minecraft.src.WorldClient;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.glu.GLU;

import com.kodehawa.CheatBase;
import com.kodehawa.mods.Vars;

public class TestEntityRenderer
{
	public static boolean anaglyphEnable = false;
	
	/** Anaglyph field (0=R, 1=GB) */
	public static int anaglyphField;
	
	/** A reference to the Minecraft object. */
	protected static Minecraft mc;
	protected float farPlaneDistance = 0.0F;
	public ItemRenderer itemRenderer;
	
	/** Entity renderer update count */
	protected int rendererUpdateCount;
	
	/** Pointed entity */
	private Entity pointedEntity = null;
	private MouseFilter mouseFilterXAxis = new MouseFilter( );
	private MouseFilter mouseFilterYAxis = new MouseFilter( );
	
	/** Mouse filter dummy 1 */
	private final MouseFilter mouseFilterDummy1 = new MouseFilter( );
	
	/** Mouse filter dummy 2 */
	private final MouseFilter mouseFilterDummy2 = new MouseFilter( );
	
	/** Mouse filter dummy 3 */
	private final MouseFilter mouseFilterDummy3 = new MouseFilter( );
	
	/** Mouse filter dummy 4 */
	private final MouseFilter mouseFilterDummy4 = new MouseFilter( );
	private final float thirdPersonDistance = 4.0F;
	
	/** Third person distance temp */
	private float thirdPersonDistanceTemp = 4.0F;
	private final float debugCamYaw = 0.0F;
	private float prevDebugCamYaw = 0.0F;
	private final float debugCamPitch = 0.0F;
	private float prevDebugCamPitch = 0.0F;
	
	/** Smooth cam yaw */
	protected float smoothCamYaw;
	
	/** Smooth cam pitch */
	protected float smoothCamPitch;
	
	/** Smooth cam filter X */
	protected float smoothCamFilterX;
	
	/** Smooth cam filter Y */
	protected float smoothCamFilterY;
	
	/** Smooth cam partial ticks */
	protected float smoothCamPartialTicks;
	private final float debugCamFOV = 0.0F;
	private float prevDebugCamFOV = 0.0F;
	private final float camRoll = 0.0F;
	private float prevCamRoll = 0.0F;
	
	/**
	 * The texture id of the blocklight/skylight texture used for lighting
	 * effects
	 */
	public int lightmapTexture;
	
	/**
	 * Colors computed in updateLightmap() and loaded into the lightmap
	 * emptyTexture
	 */
	private final int[ ] lightmapColors;
	
	/** FOV modifier hand */
	private float fovModifierHand;
	
	/** FOV modifier hand prev */
	private float fovModifierHandPrev;
	
	/** FOV multiplier temp */
	private float fovMultiplierTemp;
	private float field_82831_U;
	private float field_82832_V;
	
	/** Cloud fog mode */
	private boolean cloudFog = false;
	protected final double cameraZoom = 1.0D;
	protected final double cameraYaw = 0.0D;
	protected final double cameraPitch = 0.0D;
	
	/** Previous frame time in milliseconds */
	protected long prevFrameTime = Minecraft.getSystemTime( );
	
	/** End time of last render (ns) */
	protected long renderEndNanoTime = 0L;
	
	/**
	 * Is set, updateCameraAndRender() calls updateLightmap(); set by
	 * updateTorchFlicker()
	 */
	protected boolean lightmapUpdateNeeded = false;
	
	/** Torch flicker X */
	float torchFlickerX = 0.0F;
	
	/** Torch flicker DX */
	float torchFlickerDX = 0.0F;
	
	/** Torch flicker Y */
	float torchFlickerY = 0.0F;
	
	/** Torch flicker DY */
	float torchFlickerDY = 0.0F;
	private final Random random = new Random( );
	
	/** Rain sound counter */
	private int rainSoundCounter = 0;
	
	/** Rain X coords */
	float[ ] rainXCoords;
	
	/** Rain Y coords */
	float[ ] rainYCoords;
	volatile int field_78523_k = 0;
	volatile int field_78520_l = 0;
	
	/** Fog color buffer */
	FloatBuffer fogColorBuffer = GLAllocation.createDirectFloatBuffer( 16 );
	
	/** red component of the fog color */
	float fogColorRed;
	
	/** green component of the fog color */
	float fogColorGreen;
	
	/** blue component of the fog color */
	float fogColorBlue;
	
	/** Fog color 2 */
	private float fogColor2;
	
	/** Fog color 1 */
	private float fogColor1;
	
	/**
	 * Debug view direction (0=OFF, 1=Front, 2=Right, 3=Back, 4=Left,
	 * 5=TiltLeft, 6=TiltRight)
	 */
	public int debugViewDirection;
	private World updatedWorld = null;
	private boolean fullscreenModeChecked = false;
	private boolean desktopModeChecked = false;
	private String lastTexturePack = null;
	private long lastServerTime = 0L;
	private int lastServerTicks = 0;
	private int serverWaitTime = 0;
	private int serverWaitTimeCurrent = 0;
	public long[ ] frameTimes = new long[ 512 ];
	public long[ ] tickTimes = new long[ 512 ];
	public long[ ] chunkTimes = new long[ 512 ];
	public long[ ] serverTimes = new long[ 512 ];
	public int numRecordedFrameTimes = 0;
	public long prevFrameTimeNano = -1L;
	private boolean lastShowDebugInfo = false;
	private boolean showExtendedDebugInfo = false;
	
	public TestEntityRenderer( Minecraft par1Minecraft )
	{
		Vars.spiedOn = CheatBase.instance.minecraft.getMinecraft( ).thePlayer;
		this.mc = par1Minecraft;
		this.itemRenderer = new ItemRenderer( par1Minecraft );
		this.lightmapTexture = par1Minecraft.renderEngine.allocateAndSetupTexture( new BufferedImage( 16, 16, 1 ) );
		this.lightmapColors = new int[ 256 ];
	}
	
	/**
	 * Updates the entity renderer
	 */
	public void updateRenderer( )
	{
		this.updateFovModifierHand( );
		this.updateTorchFlicker( );
		this.fogColor2 = this.fogColor1;
		this.thirdPersonDistanceTemp = this.thirdPersonDistance;
		this.prevDebugCamYaw = this.debugCamYaw;
		this.prevDebugCamPitch = this.debugCamPitch;
		this.prevDebugCamFOV = this.debugCamFOV;
		this.prevCamRoll = this.camRoll;
		float var1;
		float var2;
		
		if ( this.mc.gameSettings.smoothCamera )
		{
			var1 = ( this.mc.gameSettings.mouseSensitivity * 0.6F ) + 0.2F;
			var2 = var1 * var1 * var1 * 8.0F;
			this.smoothCamFilterX = this.mouseFilterXAxis.smooth( this.smoothCamYaw, 0.05F * var2 );
			this.smoothCamFilterY = this.mouseFilterYAxis.smooth( this.smoothCamPitch, 0.05F * var2 );
			this.smoothCamPartialTicks = 0.0F;
			this.smoothCamYaw = 0.0F;
			this.smoothCamPitch = 0.0F;
		}
		
		if ( this.mc.renderViewEntity == null )
		{
			this.mc.renderViewEntity = this.mc.thePlayer;
		}
		
		var1 = this.mc.theWorld.getLightBrightness( MathHelper.floor_double( Vars.spiedOn.posX ), MathHelper.floor_double( Vars.spiedOn.posY ), MathHelper.floor_double( Vars.spiedOn.posZ ) );
		var2 = ( 3 - this.mc.gameSettings.renderDistance ) / 3.0F;
		float var3 = ( var1 * ( 1.0F - var2 ) ) + var2;
		this.fogColor1 += ( var3 - this.fogColor1 ) * 0.1F;
		++this.rendererUpdateCount;
		this.itemRenderer.updateEquippedItem( );
		//this.addRainParticles( );
		this.field_82832_V = this.field_82831_U;
		
		if ( BossStatus.field_82825_d )
		{
			this.field_82831_U += 0.05F;
			
			if ( this.field_82831_U > 1.0F )
			{
				this.field_82831_U = 1.0F;
			}
			
			BossStatus.field_82825_d = false;
		}
		else if ( this.field_82831_U > 0.0F )
		{
			this.field_82831_U -= 0.0125F;
		}
	}
	
	/**
	 * Finds what block or object the mouse is over at the specified partial
	 * tick time. Args: partialTickTime
	 */
	public void getMouseOver( float par1 )
	{
		if ( ( this.mc.renderViewEntity != null ) && ( this.mc.theWorld != null ) )
		{
			this.mc.pointedEntityLiving = null;
			double var2 = this.mc.playerController.getBlockReachDistance( );
			this.mc.objectMouseOver = this.mc.renderViewEntity.rayTrace( var2, par1 );
			double var4 = var2;
			Vec3 var6 = this.mc.renderViewEntity.getPosition( par1 );
			
			if ( this.mc.playerController.extendedReach( ) )
			{
				var2 = 6.0D;
				var4 = 6.0D;
			}
			else
			{
				if ( var2 > 3.0D )
				{
					var4 = 3.0D;
				}
				
				var2 = var4;
			}
			
			if ( this.mc.objectMouseOver != null )
			{
				var4 = this.mc.objectMouseOver.hitVec.distanceTo( var6 );
			}
			
			Vec3 var7 = this.mc.renderViewEntity.getLook( par1 );
			Vec3 var8 = var6.addVector( var7.xCoord * var2, var7.yCoord * var2, var7.zCoord * var2 );
			this.pointedEntity = null;
			float var9 = 1.0F;
			List var10 = this.mc.theWorld.getEntitiesWithinAABBExcludingEntity( this.mc.renderViewEntity, this.mc.renderViewEntity.boundingBox.addCoord( var7.xCoord * var2, var7.yCoord * var2, var7.zCoord * var2 ).expand( var9, var9, var9 ) );
			double var11 = var4;
			
			for ( int var13 = 0; var13 < var10.size( ); ++var13 )
			{
				Entity var14 = (Entity) var10.get( var13 );
				
				if ( var14.canBeCollidedWith( ) )
				{
					float var15 = var14.getCollisionBorderSize( );
					AxisAlignedBB var16 = var14.boundingBox.expand( var15, var15, var15 );
					MovingObjectPosition var17 = var16.calculateIntercept( var6, var8 );
					
					if ( var16.isVecInside( var6 ) )
					{
						if ( ( 0.0D < var11 ) || ( var11 == 0.0D ) )
						{
							this.pointedEntity = var14;
							var11 = 0.0D;
						}
					}
					else if ( var17 != null )
					{
						double var18 = var6.distanceTo( var17.hitVec );
						
						if ( ( var18 < var11 ) || ( var11 == 0.0D ) )
						{
							this.pointedEntity = var14;
							var11 = var18;
						}
					}
				}
			}
			
			if ( ( this.pointedEntity != null ) && ( ( var11 < var4 ) || ( this.mc.objectMouseOver == null ) ) )
			{
				this.mc.objectMouseOver = new MovingObjectPosition( this.pointedEntity );
				
				if ( this.pointedEntity instanceof EntityLiving )
				{
					this.mc.pointedEntityLiving = (EntityLiving) this.pointedEntity;
				}
			}
		}
	}
	
	/**
	 * Update FOV modifier hand
	 */
	private void updateFovModifierHand( )
	{
		if ( this.mc.renderViewEntity instanceof EntityPlayerSP )
		{
			EntityPlayerSP var1 = (EntityPlayerSP) this.mc.renderViewEntity;
			this.fovMultiplierTemp = var1.getFOVMultiplier( );
		}
		else
		{
			this.fovMultiplierTemp = this.mc.thePlayer.getFOVMultiplier( );
		}
		
		this.fovModifierHandPrev = this.fovModifierHand;
		this.fovModifierHand += ( this.fovMultiplierTemp - this.fovModifierHand ) * 0.5F;
		
		if ( this.fovModifierHand > 1.5F )
		{
			this.fovModifierHand = 1.5F;
		}
		
		if ( this.fovModifierHand < 0.1F )
		{
			this.fovModifierHand = 0.1F;
		}
	}
	
	/**
	 * Changes the field of view of the player depending on if they are
	 * underwater or not
	 */
	protected float getFOVModifier( float par1, boolean par2 )
	{
		if ( this.debugViewDirection > 0 )
		{
			return 90.0F;
		}
		else
		{
			EntityLiving var3 = this.mc.renderViewEntity;
			float var4 = 70.0F;
			
			if ( par2 )
			{
				var4 += this.mc.gameSettings.fovSetting * 40.0F;
				var4 *= this.fovModifierHandPrev + ( ( this.fovModifierHand - this.fovModifierHandPrev ) * par1 );
			}
			
			boolean var5 = false;
			
			
			if ( var3.getHealth( ) <= 0 )
			{
				float var6 = var3.deathTime + par1;
				var4 /= ( ( 1.0F - ( 500.0F / ( var6 + 500.0F ) ) ) * 2.0F ) + 1.0F;
			}
			
			int var7 = ActiveRenderInfo.getBlockIdAtEntityViewpoint( this.mc.theWorld, var3, par1 );
			
			if ( ( var7 != 0 ) && ( Block.blocksList [ var7 ].blockMaterial == Material.water ) )
			{
				var4 = ( var4 * 60.0F ) / 70.0F;
			}
			
			return var4 + this.prevDebugCamFOV + ( ( this.debugCamFOV - this.prevDebugCamFOV ) * par1 );
		}
	}
	
	protected void hurtCameraEffect( float par1 )
	{
		EntityLiving var2 = this.mc.renderViewEntity;
		float var3 = var2.hurtTime - par1;
		float var4;
		
		if ( var2.getHealth( ) <= 0 )
		{
			var4 = var2.deathTime + par1;
			GL11.glRotatef( 40.0F - ( 8000.0F / ( var4 + 200.0F ) ), 0.0F, 0.0F, 1.0F );
		}
		
		if ( var3 >= 0.0F )
		{
			var3 /= var2.maxHurtTime;
			var3 = MathHelper.sin( var3 * var3 * var3 * var3 * (float) Math.PI );
			var4 = var2.attackedAtYaw;
			GL11.glRotatef( -var4, 0.0F, 1.0F, 0.0F );
			GL11.glRotatef( -var3 * 14.0F, 0.0F, 0.0F, 1.0F );
			GL11.glRotatef( var4, 0.0F, 1.0F, 0.0F );
		}
	}
	
	/**
	 * Setups all the GL settings for view bobbing. Args: partialTickTime
	 */
	protected void setupViewBobbing( float par1 )
	{
		if ( this.mc.renderViewEntity instanceof EntityPlayer )
		{
			EntityPlayer var2 = Vars.spiedOn;
			float var3 = var2.distanceWalkedModified - var2.prevDistanceWalkedModified;
			float var4 = -( var2.distanceWalkedModified + ( var3 * par1 ) );
			float var5 = var2.prevCameraYaw + ( ( var2.cameraYaw - var2.prevCameraYaw ) * par1 );
			float var6 = var2.prevCameraPitch + ( ( var2.cameraPitch - var2.prevCameraPitch ) * par1 );
			GL11.glTranslatef( MathHelper.sin( var4 * (float) Math.PI ) * var5 * 0.5F, -Math.abs( MathHelper.cos( var4 * (float) Math.PI ) * var5 ), 0.0F );
			GL11.glRotatef( MathHelper.sin( var4 * (float) Math.PI ) * var5 * 3.0F, 0.0F, 0.0F, 1.0F );
			GL11.glRotatef( Math.abs( MathHelper.cos( ( var4 * (float) Math.PI ) - 0.2F ) * var5 ) * 5.0F, 1.0F, 0.0F, 0.0F );
			GL11.glRotatef( var6, 1.0F, 0.0F, 0.0F );
		}
	}
	
	/**
	 * sets up player's eye (or camera in third person mode)
	 */
	protected void orientCamera( float par1 )
	{
		EntityLiving var2 = Vars.spiedOn;
		float var3 = var2.yOffset - 1.62F;
		double var4 = var2.prevPosX + ( ( var2.posX - var2.prevPosX ) * par1 );
		double var6 = ( var2.prevPosY + ( ( var2.posY - var2.prevPosY ) * par1 ) ) - var3;
		double var8 = var2.prevPosZ + ( ( var2.posZ - var2.prevPosZ ) * par1 );
		GL11.glRotatef( this.prevCamRoll + ( ( this.camRoll - this.prevCamRoll ) * par1 ), 0.0F, 0.0F, 1.0F );
	}
		
	/**
	 * Render player hand
	 */
	private void renderHand( float par1, int par2 )
	{
		if ( this.debugViewDirection <= 0 )
		{
			GL11.glMatrixMode( GL11.GL_PROJECTION );
			GL11.glLoadIdentity( );
			float var3 = 0.07F;
			
			if ( this.mc.gameSettings.anaglyph )
			{
				GL11.glTranslatef( ( -( ( par2 * 2 ) - 1 ) ) * var3, 0.0F, 0.0F );
			}
			
			if ( this.cameraZoom != 1.0D )
			{
				GL11.glTranslatef( (float) this.cameraYaw, (float) ( -this.cameraPitch ), 0.0F );
				GL11.glScaled( this.cameraZoom, this.cameraZoom, 1.0D );
			}
			
			GLU.gluPerspective( this.getFOVModifier( par1, false ), (float) this.mc.displayWidth / (float) this.mc.displayHeight, 0.05F, this.farPlaneDistance * 2.0F );
			
			if ( this.mc.playerController.enableEverythingIsScrewedUpMode( ) )
			{
				float var4 = 0.6666667F;
				GL11.glScalef( 1.0F, var4, 1.0F );
			}
			
			GL11.glMatrixMode( GL11.GL_MODELVIEW );
			GL11.glLoadIdentity( );
			
			if ( this.mc.gameSettings.anaglyph )
			{
				GL11.glTranslatef( ( ( par2 * 2 ) - 1 ) * 0.1F, 0.0F, 0.0F );
			}
			
			GL11.glPushMatrix( );
			this.hurtCameraEffect( par1 );
			
			if ( this.mc.gameSettings.viewBobbing )
			{
				this.setupViewBobbing( par1 );
			}
			
			if ( ( this.mc.gameSettings.thirdPersonView == 0 ) && !Vars.spiedOn.isPlayerSleeping( ) && !this.mc.gameSettings.hideGUI && !this.mc.playerController.enableEverythingIsScrewedUpMode( ) )
			{
				this.enableLightmap( par1 );
				this.itemRenderer.renderItemInFirstPerson( par1 );
				this.disableLightmap( par1 );
			}
			
			GL11.glPopMatrix( );
			
			if ( ( this.mc.gameSettings.thirdPersonView == 0 ) && !Vars.spiedOn.isPlayerSleeping( ) )
			{
				this.itemRenderer.renderOverlays( par1 );
				this.hurtCameraEffect( par1 );
			}
			
			if ( this.mc.gameSettings.viewBobbing )
			{
				this.setupViewBobbing( par1 );
			}
		}
	}
	
	/**
	 * Disable secondary texture unit used by lightmap
	 */
	public void disableLightmap( double par1 )
	{
		OpenGlHelper.setActiveTexture( OpenGlHelper.lightmapTexUnit );
		GL11.glDisable( GL11.GL_TEXTURE_2D );
		OpenGlHelper.setActiveTexture( OpenGlHelper.defaultTexUnit );
	}
	
	/**
	 * Enable lightmap in secondary texture unit
	 */
	public void enableLightmap( double par1 )
	{
		OpenGlHelper.setActiveTexture( OpenGlHelper.lightmapTexUnit );
		GL11.glMatrixMode( GL11.GL_TEXTURE );
		GL11.glLoadIdentity( );
		float var3 = 0.00390625F;
		GL11.glScalef( var3, var3, var3 );
		GL11.glTranslatef( 8.0F, 8.0F, 8.0F );
		GL11.glMatrixMode( GL11.GL_MODELVIEW );
		GL11.glBindTexture( GL11.GL_TEXTURE_2D, this.lightmapTexture );
		GL11.glTexParameteri( GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR );
		GL11.glTexParameteri( GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR );
		GL11.glTexParameteri( GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_CLAMP );
		GL11.glTexParameteri( GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_CLAMP );
		GL11.glColor4f( 1.0F, 1.0F, 1.0F, 1.0F );
		GL11.glEnable( GL11.GL_TEXTURE_2D );
		this.mc.renderEngine.resetBoundTexture( );
		OpenGlHelper.setActiveTexture( OpenGlHelper.defaultTexUnit );
	}
	
	/**
	 * Recompute a random value that is applied to block color in
	 * updateLightmap()
	 */
	private void updateTorchFlicker( )
	{
		this.torchFlickerDX = (float) ( this.torchFlickerDX + ( ( Math.random( ) - Math.random( ) ) * Math.random( ) * Math.random( ) ) );
		this.torchFlickerDY = (float) ( this.torchFlickerDY + ( ( Math.random( ) - Math.random( ) ) * Math.random( ) * Math.random( ) ) );
		this.torchFlickerDX = (float) ( this.torchFlickerDX * 0.9D );
		this.torchFlickerDY = (float) ( this.torchFlickerDY * 0.9D );
		this.torchFlickerX += ( this.torchFlickerDX - this.torchFlickerX ) * 1.0F;
		this.torchFlickerY += ( this.torchFlickerDY - this.torchFlickerY ) * 1.0F;
		this.lightmapUpdateNeeded = true;
	}
	
	protected void updateLightmap( float par1 )
	{
		WorldClient var2 = this.mc.theWorld;
		
		if ( var2 != null )
		{
			
			for ( int var3 = 0; var3 < 256; ++var3 )
			{
				float var4 = ( var2.getSunBrightness( 1.0F ) * 0.95F ) + 0.05F;
				float var5 = var2.provider.lightBrightnessTable [ var3 / 16 ] * var4;
				float var6 = var2.provider.lightBrightnessTable [ var3 % 16 ] * ( ( this.torchFlickerX * 0.1F ) + 1.5F );
				
				if ( var2.lastLightningBolt > 0 )
				{
					var5 = var2.provider.lightBrightnessTable [ var3 / 16 ];
				}
				
				float var7 = var5 * ( ( var2.getSunBrightness( 1.0F ) * 0.65F ) + 0.35F );
				float var8 = var5 * ( ( var2.getSunBrightness( 1.0F ) * 0.65F ) + 0.35F );
				float var9 = var6 * ( ( ( ( var6 * 0.6F ) + 0.4F ) * 0.6F ) + 0.4F );
				float var10 = var6 * ( ( var6 * var6 * 0.6F ) + 0.4F );
				float var11 = var7 + var6;
				float var12 = var8 + var9;
				float var13 = var5 + var10;
				var11 = ( var11 * 0.96F ) + 0.03F;
				var12 = ( var12 * 0.96F ) + 0.03F;
				var13 = ( var13 * 0.96F ) + 0.03F;
				float var14;
				
				if ( this.field_82831_U > 0.0F )
				{
					var14 = this.field_82832_V + ( ( this.field_82831_U - this.field_82832_V ) * par1 );
					var11 = ( var11 * ( 1.0F - var14 ) ) + ( var11 * 0.7F * var14 );
					var12 = ( var12 * ( 1.0F - var14 ) ) + ( var12 * 0.6F * var14 );
					var13 = ( var13 * ( 1.0F - var14 ) ) + ( var13 * 0.6F * var14 );
				}
				
				if ( var2.provider.dimensionId == 1 )
				{
					var11 = 0.22F + ( var6 * 0.75F );
					var12 = 0.28F + ( var9 * 0.75F );
					var13 = 0.25F + ( var10 * 0.75F );
				}
				
				float var15;
				
				if ( this.mc.thePlayer.isPotionActive( Potion.nightVision ) )
				{
					var14 = this.getNightVisionBrightness( this.mc.thePlayer, par1 );
					var15 = 1.0F / var11;
					
					if ( var15 > ( 1.0F / var12 ) )
					{
						var15 = 1.0F / var12;
					}
					
					if ( var15 > ( 1.0F / var13 ) )
					{
						var15 = 1.0F / var13;
					}
					
					var11 = ( var11 * ( 1.0F - var14 ) ) + ( var11 * var15 * var14 );
					var12 = ( var12 * ( 1.0F - var14 ) ) + ( var12 * var15 * var14 );
					var13 = ( var13 * ( 1.0F - var14 ) ) + ( var13 * var15 * var14 );
				}
				
				if ( var11 > 1.0F )
				{
					var11 = 1.0F;
				}
				
				if ( var12 > 1.0F )
				{
					var12 = 1.0F;
				}
				
				if ( var13 > 1.0F )
				{
					var13 = 1.0F;
				}
				
				var14 = this.mc.gameSettings.gammaSetting;
				var15 = 1.0F - var11;
				float var16 = 1.0F - var12;
				float var17 = 1.0F - var13;
				var15 = 1.0F - ( var15 * var15 * var15 * var15 );
				var16 = 1.0F - ( var16 * var16 * var16 * var16 );
				var17 = 1.0F - ( var17 * var17 * var17 * var17 );
				var11 = ( var11 * ( 1.0F - var14 ) ) + ( var15 * var14 );
				var12 = ( var12 * ( 1.0F - var14 ) ) + ( var16 * var14 );
				var13 = ( var13 * ( 1.0F - var14 ) ) + ( var17 * var14 );
				var11 = ( var11 * 0.96F ) + 0.03F;
				var12 = ( var12 * 0.96F ) + 0.03F;
				var13 = ( var13 * 0.96F ) + 0.03F;
				
				if ( var11 > 1.0F )
				{
					var11 = 1.0F;
				}
				
				if ( var12 > 1.0F )
				{
					var12 = 1.0F;
				}
				
				if ( var13 > 1.0F )
				{
					var13 = 1.0F;
				}
				
				if ( var11 < 0.0F )
				{
					var11 = 0.0F;
				}
				
				if ( var12 < 0.0F )
				{
					var12 = 0.0F;
				}
				
				if ( var13 < 0.0F )
				{
					var13 = 0.0F;
				}
				
				short var18 = 255;
				int var19 = (int) ( var11 * 255.0F );
				int var20 = (int) ( var12 * 255.0F );
				int var21 = (int) ( var13 * 255.0F );
				this.lightmapColors [ var3 ] = ( var18 << 24 ) | ( var19 << 16 ) | ( var20 << 8 ) | var21;
			}
			
			this.mc.renderEngine.createTextureFromBytes( this.lightmapColors, 16, 16, this.lightmapTexture );
		}
	}
	
	/**
	 * Gets the night vision brightness
	 */
	private float getNightVisionBrightness( EntityPlayer par1EntityPlayer, float par2 )
	{
		int var3 = par1EntityPlayer.getActivePotionEffect( Potion.nightVision ).getDuration( );
		return var3 > 200 ? 1.0F : 0.7F + ( MathHelper.sin( ( var3 - par2 ) * (float) Math.PI * 0.2F ) * 0.3F );
	}
	
	/**
	 * Will update any inputs that effect the camera angle (mouse) and then
	 * render the world and GUI
	 */

	/**
	 * Get minecraft reference from the EntityRenderer
	 */
	static Minecraft getRendererMinecraft( EntityRenderer par0EntityRenderer )
	{
		return mc;
	}
}
