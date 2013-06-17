package com.kodehawa.mods;

import net.minecraft.client.Minecraft;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.EntityPlayerSP;
import net.minecraft.src.Item;
import net.minecraft.src.ItemBow;
import net.minecraft.src.ItemEgg;
import net.minecraft.src.ItemEnderPearl;
import net.minecraft.src.ItemSnowball;
import net.minecraft.src.MathHelper;
import net.minecraft.src.RenderManager;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import com.kodehawa.CheatBase;
import com.kodehawa.util.GLHelper;
import com.kodehawa.util.Tickable;

public class Trajectories extends Mod implements Tickable {
	private double x, y, z;
	private double motionX, motionY, motionZ;
	private double r, g, b;
	
	public CheatBase cb;
	public Minecraft mc;
	
	public Trajectories( CheatBase c, Minecraft m ) {
		super( Mods.TRAJECTORIES );
		this.cb = c;
		this.mc = m;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void tick( ) {
		// TODO Auto-generated method stub
		this.onUpdate( this.mc.thePlayer );
	}
	
	@Override
	public void onEnable( ) {
		cb.addToTick( this );
		cb.getUtils( ).addChatMessage( getActive( ) );
	}
	
	@Override
	public void onDisable( ) {
		cb.removeFromTick( this );
		cb.getUtils( ).addChatMessage( getActive( ) );
	}
	
	public void onRender( ) {
		EntityClientPlayerMP player = mc.thePlayer;
		if ( ( player.getCurrentEquippedItem( ) != null ) && isActive( ) ) {
			if ( this.isThrowable( mc.thePlayer.getCurrentEquippedItem( ).getItem( ) ) ) {
				this.x = ( player.lastTickPosX
				        + ( ( player.posX - player.lastTickPosX )
				        * mc.timer.renderPartialTicks ) )
				        - ( MathHelper.cos( (float) Math.toRadians( player.rotationYaw ) ) * 0.16F );
				this.y = ( player.lastTickPosY
				        + ( ( player.posY - player.lastTickPosY ) * mc.timer.renderPartialTicks )
				        + player.getEyeHeight( ) ) - 0.100149011612D;
				this.z = ( player.lastTickPosZ
				        + ( ( player.posZ - player.lastTickPosZ )
				        * mc.timer.renderPartialTicks ) )
				        - ( MathHelper.sin( (float) Math.toRadians( player.rotationYaw ) ) * 0.16F );
				float con = 1.0F;
				if ( !( player.getCurrentEquippedItem( ).getItem( ) instanceof ItemBow ) ) {
					con = 0.4F;
				}
				
				this.motionX = ( -MathHelper.sin( (float) Math.toRadians( player.rotationYaw ) )
				        * MathHelper.cos( (float) Math.toRadians( player.rotationPitch ) ) * con );
				this.motionZ = ( MathHelper.cos( (float) Math.toRadians( player.rotationYaw ) )
				        * MathHelper.cos( (float) Math.toRadians( player.rotationPitch ) ) * con );
				this.motionY = ( -MathHelper.sin( (float) Math.toRadians( player.rotationPitch ) ) * con );
				double ssum = Math.sqrt( ( this.motionX * this.motionX )
				        + ( this.motionY * this.motionY ) + ( this.motionZ
				        * this.motionZ ) );
				
				this.motionX /= ssum;
				this.motionY /= ssum;
				this.motionZ /= ssum;
				
				if ( player.getCurrentEquippedItem( ).getItem( ) instanceof ItemBow ) {
					float pow = ( 72000 - player.getItemInUseCount( ) ) / 20.0F;
					pow = ( ( pow * pow ) + ( pow * 2.0F ) ) / 3.0F;
					
					if ( pow > 1.0F ) {
						pow = 1.0F;
					}
					
					if ( pow <= 0.1F ) {
						pow = 1.0F;
					}
					
					pow *= 2.0F;
					pow *= 1.5F;
					this.motionX *= pow;
					this.motionY *= pow;
					this.motionZ *= pow;
				} else {
					this.motionX *= 1.5D;
					this.motionY *= 1.5D;
					this.motionZ *= 1.5D;
				}
				
				GL11.glPushMatrix( );
				enableDefaults( );
				GL11.glLineWidth( 1.8F );
				GL11.glColor3d( r, g, b );
				GL11.glBegin( GL11.GL_LINE_STRIP );
				boolean hasHitBlock = false;
				double gravity = this.getGravity( player.getCurrentEquippedItem( ).getItem( ) );
				
				for ( ; !hasHitBlock; ) {
					double rx = ( this.x ) - RenderManager.renderPosX;
					double ry = ( this.y ) - RenderManager.renderPosY;
					double rz = ( this.z ) - RenderManager.renderPosZ;
					GL11.glVertex3d( rx, ry, rz );
					
					this.x += this.motionX;
					this.y += this.motionY;
					this.z += this.motionZ;
					this.motionX *= 0.99D;
					this.motionY *= 0.99D;
					this.motionZ *= 0.99D;
					this.motionY -= gravity;
					
					hasHitBlock = mc.theWorld.rayTraceBlocks( mc.theWorld.getWorldVec3Pool( ).getVecFromPool( player.posX, player.posY + player.getEyeHeight( ), player.posZ ), mc.theWorld.getWorldVec3Pool( ).getVecFromPool( this.x, this.y, this.z ) ) != null;
				}
				
				GL11.glEnd( );
				
				new AxisAlignedBB( x - 0.5 - RenderManager.renderPosX, y - 0.5 - RenderManager.renderPosY, z - 0.5 - RenderManager.renderPosZ, ( x - 0.5 - RenderManager.renderPosX ) + 1, ( y - 0.5 - RenderManager.renderPosY ) + 1, ( z - 0.5 - RenderManager.renderPosZ ) + 1 );
				
				GL11.glTranslated( x - RenderManager.renderPosX, y - RenderManager.renderPosY, z - RenderManager.renderPosZ );
				GL11.glRotatef( mc.thePlayer.rotationYaw, 0.0F, (float) ( y - RenderManager.renderPosY ), 0.0F );
				GL11.glTranslated( -( x - RenderManager.renderPosX ), -( y - RenderManager.renderPosY ), -( z - RenderManager.renderPosZ ) );
				GLHelper.drawESP( x - 0.35 - RenderManager.renderPosX, y - 0.5 - RenderManager.renderPosY, z - 0.5 - RenderManager.renderPosZ, r, b, g );
				disableDefaults( );
				GL11.glPopMatrix( );
			}
		}
	}
	
	public void onUpdate( EntityPlayerSP player ) {
		if ( this.isActive( ) ) {
			r = player.getDistance( x, y, z ) / 100;
			g = 1.0;
			b = 0.0;
		}
	}
	
	public void enableDefaults( ) {
		GL11.glDisable( GL11.GL_LIGHTING );
		GL11.glEnable( GL11.GL_LINE_SMOOTH );
		GL11.glBlendFunc( 770, 771 );
		GL11.glEnable( 3042 );
		GL11.glDisable( 3553 );
		GL11.glDisable( 2929 );
		GL11.glEnable( GL13.GL_MULTISAMPLE );
		GL11.glDepthMask( false );
	}
	
	public void disableDefaults( ) {
		GL11.glDisable( 3042 );
		GL11.glEnable( 3553 );
		GL11.glEnable( 2929 );
		GL11.glDisable( GL13.GL_MULTISAMPLE );
		GL11.glDepthMask( true );
		GL11.glDisable( GL11.GL_LINE_SMOOTH );
		GL11.glEnable( GL11.GL_LIGHTING );
	}
	
	private double getGravity( Item item ) {
		return item instanceof ItemBow ? 0.05D : 0.03D;
	}
	
	private boolean isThrowable( Item item ) {
		return ( item instanceof ItemBow ) || ( item instanceof ItemSnowball )
		        || ( item instanceof ItemEgg ) || ( item instanceof ItemEnderPearl );
	}
	
}
