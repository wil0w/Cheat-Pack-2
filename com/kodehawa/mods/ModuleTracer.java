package com.kodehawa.mods;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Minecraft;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import com.kodehawa.CheatBase;
import com.kodehawa.util.Tickable;

public class ModuleTracer extends Mod implements Tickable {
	
	private final CheatBase cb;
	private static Minecraft mc;
	
	public ModuleTracer( CheatBase co, Minecraft mcx ) {
		super( Mods.TRACER );
		cb = co;
		mc = mcx;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void tick( ) {
		// TODO Auto-generated method stub
		
		tracerLine( );
	}
	
	@Override
	public void onEnable( ) {
		cb.addToTick( this );
		Vars.tracer = true;
		cb.getUtils( ).addChatMessage( getActive( ) );
	}
	
	@Override
	public void onDisable( ) {
		cb.removeFromTick( this );
		Vars.tracer = false;
		cb.getUtils( ).addChatMessage( getActive( ) );
	}
	
	public static void tracerLine( ) {
		
		GL11.glPushMatrix( );
		GL11.glDisable( GL11.GL_TEXTURE_2D );
		GL11.glDisable( GL11.GL_FOG );
		GL11.glEnable( GL11.GL_BLEND );
		GL11.glDisable( GL11.GL_DEPTH_TEST );
		GL11.glHint( GL11.GL_LINE_SMOOTH_HINT, GL11.GL_NICEST );
		GL11.glBlendFunc( GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA );
		
		GL11.glLineWidth( 3.8F );
		
		double mX = mc.thePlayer.posX;
		double mY = mc.thePlayer.posY;
		double mZ = mc.thePlayer.posZ;
		GL11.glBegin( GL11.GL_LINES );
		GL11.glColor3f( 0, 1, 1 );
		for ( Object o : mc.theWorld.playerEntities ) {
			EntityPlayer p = (EntityPlayer) o;
			if ( p != mc.thePlayer ) {
				GL11.glVertex3d( 0d, 0d, 0d );
				GL11.glVertex3d( ( p.posX - mX ), ( p.posY - mY ) + 1, ( p.posZ - mZ ) );
			}
		}
		GL11.glEnd( );
		GL11.glEnable( GL11.GL_TEXTURE_2D );
		// GL11.glEnable( GL11.GL_FOG );
		GL11.glDisable( GL11.GL_BLEND );
		GL11.glEnable( GL11.GL_DEPTH_TEST );
		GL11.glPopMatrix( );
	}
	
	public static void enableDefaults( ) {
		GL11.glDisable( GL11.GL_LIGHTING );
		GL11.glEnable( GL11.GL_LINE_SMOOTH );
		GL11.glBlendFunc( 770, 771 );
		GL11.glEnable( 3042 );
		GL11.glDisable( 3553 );
		GL11.glDisable( 2929 );
		GL11.glEnable( GL13.GL_MULTISAMPLE );
		GL11.glDepthMask( false );
	}
	
	public static void disableDefaults( ) {
		GL11.glDisable( 3042 );
		GL11.glEnable( 3553 );
		GL11.glEnable( 2929 );
		GL11.glDisable( GL13.GL_MULTISAMPLE );
		GL11.glDepthMask( true );
		GL11.glDisable( GL11.GL_LINE_SMOOTH );
		GL11.glEnable( GL11.GL_LIGHTING );
	}
	
}
