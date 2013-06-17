package com.kodehawa.util;

import net.minecraft.client.Minecraft;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.FontRenderer;
import net.minecraft.src.RenderGlobal;
import net.minecraft.src.RenderManager;
import net.minecraft.src.Tessellator;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import com.kodehawa.CheatBase;
import com.kodehawa.util.waypoints.Waypoint;

public class GLHelper {
	
	public static void drawBoundingBox( AxisAlignedBB axisalignedbb ) {
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads( ); // starts x
		tessellator.addVertex( axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ );
		tessellator.addVertex( axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ );
		tessellator.addVertex( axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ );
		tessellator.addVertex( axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ );
		tessellator.addVertex( axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ );
		tessellator.addVertex( axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ );
		tessellator.addVertex( axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ );
		tessellator.addVertex( axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ );
		tessellator.draw( );
		tessellator.startDrawingQuads( );
		tessellator.addVertex( axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ );
		tessellator.addVertex( axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ );
		tessellator.addVertex( axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ );
		tessellator.addVertex( axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ );
		tessellator.addVertex( axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ );
		tessellator.addVertex( axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ );
		tessellator.addVertex( axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ );
		tessellator.addVertex( axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ );
		tessellator.draw( ); // ends x
		tessellator.startDrawingQuads( ); // starts y
		tessellator.addVertex( axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ );
		tessellator.addVertex( axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ );
		tessellator.addVertex( axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ );
		tessellator.addVertex( axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ );
		tessellator.addVertex( axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ );
		tessellator.addVertex( axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ );
		tessellator.addVertex( axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ );
		tessellator.addVertex( axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ );
		tessellator.draw( );
		tessellator.startDrawingQuads( );
		tessellator.addVertex( axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ );
		tessellator.addVertex( axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ );
		tessellator.addVertex( axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ );
		tessellator.addVertex( axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ );
		tessellator.addVertex( axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ );
		tessellator.addVertex( axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ );
		tessellator.addVertex( axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ );
		tessellator.addVertex( axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ );
		tessellator.draw( ); // ends y
		tessellator.startDrawingQuads( ); // starts z
		tessellator.addVertex( axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ );
		tessellator.addVertex( axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ );
		tessellator.addVertex( axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ );
		tessellator.addVertex( axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ );
		tessellator.addVertex( axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ );
		tessellator.addVertex( axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ );
		tessellator.addVertex( axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ );
		tessellator.addVertex( axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ );
		tessellator.draw( );
		tessellator.startDrawingQuads( );
		tessellator.addVertex( axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ );
		tessellator.addVertex( axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ );
		tessellator.addVertex( axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ );
		tessellator.addVertex( axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ );
		tessellator.addVertex( axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ );
		tessellator.addVertex( axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ );
		tessellator.addVertex( axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ );
		tessellator.addVertex( axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ );
		tessellator.draw( ); // ends z
	}
	
	public static void drawESP( double d, double d1, double d2 ) {
		GL11.glEnable( GL11.GL_BLEND );
		GL11.glBlendFunc( GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA );
		GL11.glColor4f( 0.0F, 0.0F, 0.0F, 1F );
		GL11.glLineWidth( 4.0F );
		GL11.glDisable( GL11.GL_TEXTURE_2D );
		GL11.glDepthMask( false );
		GL11.glEnable( GL11.GL_LINE_SMOOTH );
		GL11.glBlendFunc( 770, 771 );
		GL11.glDisable( 3553 /* GL_TEXTURE_2D */);
		GL11.glDisable( 2929 /* GL_DEPTH_TEST */);
		GL11.glDepthMask( false );
		GL11.glEnable( GL11.GL_LINE_SMOOTH );
		RenderGlobal.drawOutlinedBoundingBox( new AxisAlignedBB( d - 0.5D, d1 + 0.10000000000000001D, d2 - 0.5D, d + 0.5D, d1 + 2D, d2 + 0.5D ) );
		GL11.glColor4f( 0.0F, 0.0F, 255F, 0.3F );
		drawBoundingBox( new AxisAlignedBB( d - 0.5D, d1 + 0.10000000000000001D, d2 - 0.5D, d + 0.5D, d1 + 2D, d2 + 0.5D ) );
		GL11.glDepthMask( true );
		GL11.glEnable( 3553 /* GL_TEXTURE_2D */);
		GL11.glEnable( 2929 /* GL_DEPTH_TEST */);
		GL11.glColor4f( 255, 255, 255, 255 );
	}
	
	public static void drawChestESP( double d, double d1, double d2 ) {
		GL11.glEnable( GL11.GL_BLEND );
		GL11.glBlendFunc( GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA );
		GL11.glColor4f( 0.0F, 0.0F, 0.0F, 1F );
		GL11.glLineWidth( 4.0F );
		GL11.glDisable( GL11.GL_TEXTURE_2D );
		GL11.glDepthMask( false );
		GL11.glEnable( GL11.GL_LINE_SMOOTH );
		GL11.glBlendFunc( 770, 771 );
		GL11.glDisable( 3553 /* GL_TEXTURE_2D */);
		GL11.glDisable( 2929 /* GL_DEPTH_TEST */);
		GL11.glDepthMask( false );
		GL11.glEnable( GL11.GL_LINE_SMOOTH );
		RenderGlobal.drawOutlinedBoundingBox( new AxisAlignedBB( d, d1, d2, d + 1D, d1 + 1D, d2 + 1D ) );
		GL11.glColor4f( 0.0F, 255.0F, 255F, 0.3F );
		drawBoundingBox( new AxisAlignedBB( d, d1, d2, d + 1D, d1 + 1D, d2 + 1D ) );
		GL11.glDepthMask( true );
		GL11.glEnable( 3553 /* GL_TEXTURE_2D */);
		GL11.glEnable( 2929 /* GL_DEPTH_TEST */);
		GL11.glColor4f( 255, 255, 255, 255 );
	}
	
	public static void drawPlayerESP( double d, double d1, double d2 ) {
		drawESP( d, d1, d2 );
	}
	
	public static void enableDefaults( ) {
		Minecraft.getMinecraft( ).entityRenderer.disableLightmap( 0.0D );
		GL11.glEnable( 3042 );
		GL11.glDisable( 2896 );
		GL11.glDisable( 2929 );
		GL11.glEnable( 2848 );
		GL11.glDisable( 3553 );
		GL11.glHint( 3154, 4354 );
		GL11.glBlendFunc( 770, 771 );
		GL11.glEnable( 32925 );
		GL11.glEnable( 32926 );
		GL11.glShadeModel( 7425 );
		GL11.glLineWidth( 1.8F );
		GL11.glEnable( GL11.GL_BLEND );
		GL11.glDisable( GL11.GL_LIGHTING );
		GL11.glDisable( GL11.GL_DEPTH_TEST );
		GL11.glEnable( GL11.GL_LINE_SMOOTH );
		GL11.glDisable( GL11.GL_TEXTURE_2D );
		GL11.glHint( GL11.GL_LINE_SMOOTH_HINT, GL11.GL_NICEST );
		GL11.glBlendFunc( GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA );
		GL11.glEnable( GL13.GL_MULTISAMPLE );
		GL11.glEnable( GL13.GL_SAMPLE_ALPHA_TO_COVERAGE );
		GL11.glShadeModel( GL11.GL_SMOOTH );
		GL11.glDepthMask( false );
	}
	
	public static void disableDefaults( ) {
		GL11.glDisable( 3042 );
		GL11.glEnable( 3553 );
		GL11.glDisable( 2848 );
		GL11.glEnable( 2896 );
		GL11.glEnable( 2929 );
		GL11.glDisable( 32925 );
		GL11.glDisable( 32926 );
		GL11.glDepthMask( true );
		GL11.glDisable( GL13.GL_SAMPLE_ALPHA_TO_COVERAGE );
		GL11.glDisable( GL13.GL_MULTISAMPLE );
		GL11.glEnable( GL11.GL_TEXTURE_2D );
		GL11.glDisable( GL11.GL_LINE_SMOOTH );
		GL11.glEnable( GL11.GL_DEPTH_TEST );
		GL11.glEnable( GL11.GL_LIGHTING );
		GL11.glDisable( GL11.GL_BLEND );
		Minecraft.getMinecraft( ).entityRenderer.enableLightmap( 0.0D );
	}
	
	public static void lines( AxisAlignedBB ax ) {
		GL11.glLineWidth( 1.8F );
		GL11.glPushMatrix( );
		GL11.glBegin( GL11.GL_LINES );
		GL11.glVertex3d( ax.minX, ax.maxY, ax.minZ );
		GL11.glVertex3d( ax.minX, ax.minY, ax.maxZ );
		GL11.glEnd( );
		GL11.glBegin( GL11.GL_LINES );
		GL11.glVertex3d( ax.maxX, ax.maxY, ax.minZ );
		GL11.glVertex3d( ax.maxX, ax.minY, ax.maxZ );
		GL11.glEnd( );
		GL11.glBegin( GL11.GL_LINES );
		GL11.glVertex3d( ax.minX, ax.maxY, ax.minZ );
		GL11.glVertex3d( ax.maxX, ax.maxY, ax.maxZ );
		GL11.glEnd( );
		GL11.glBegin( GL11.GL_LINES );
		GL11.glVertex3d( ax.maxX, ax.minY, ax.maxZ );
		GL11.glVertex3d( ax.minX, ax.maxY, ax.maxZ );
		GL11.glEnd( );
		GL11.glBegin( GL11.GL_LINES );
		GL11.glVertex3d( ax.maxX, ax.maxY, ax.minZ );
		GL11.glVertex3d( ax.minX, ax.minY, ax.minZ );
		GL11.glEnd( );
		GL11.glBegin( GL11.GL_LINES );
		GL11.glVertex3d( ax.maxX, ax.minY, ax.maxZ );
		GL11.glVertex3d( ax.minX, ax.minY, ax.minZ );
		GL11.glEnd( );
		GL11.glPopMatrix( );
	}
	
	public static void drawCrossedOutlinedBoundingBox( AxisAlignedBB var0 ) {
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawing( 3 );
		tessellator.addVertex( var0.minX, var0.minY, var0.minZ );
		tessellator.addVertex( var0.maxX, var0.minY, var0.minZ );
		tessellator.addVertex( var0.maxX, var0.minY, var0.maxZ );
		tessellator.addVertex( var0.minX, var0.minY, var0.maxZ );
		tessellator.addVertex( var0.minX, var0.minY, var0.minZ );
		tessellator.draw( );
		tessellator.startDrawing( 3 );
		tessellator.addVertex( var0.minX, var0.maxY, var0.minZ );
		tessellator.addVertex( var0.maxX, var0.maxY, var0.minZ );
		tessellator.addVertex( var0.maxX, var0.maxY, var0.maxZ );
		tessellator.addVertex( var0.minX, var0.maxY, var0.maxZ );
		tessellator.addVertex( var0.minX, var0.maxY, var0.minZ );
		tessellator.draw( );
		tessellator.startDrawing( 1 );
		tessellator.addVertex( var0.minX, var0.minY, var0.minZ );
		tessellator.addVertex( var0.minX, var0.maxY, var0.minZ );
		tessellator.addVertex( var0.maxX, var0.minY, var0.minZ );
		tessellator.addVertex( var0.maxX, var0.maxY, var0.minZ );
		tessellator.addVertex( var0.maxX, var0.minY, var0.maxZ );
		tessellator.addVertex( var0.maxX, var0.maxY, var0.maxZ );
		tessellator.addVertex( var0.minX, var0.minY, var0.maxZ );
		tessellator.addVertex( var0.minX, var0.maxY, var0.maxZ );
		tessellator.addVertex( var0.minX, var0.minY, var0.minZ );
		tessellator.addVertex( var0.minX, var0.maxY, var0.maxZ );
		tessellator.addVertex( var0.maxX, var0.minY, var0.minZ );
		tessellator.addVertex( var0.maxX, var0.maxY, var0.maxZ );
		tessellator.draw( );
	}
	
	public static void drawABox( int x, int y, int z )
	{
		GL11.glPushMatrix( );
		GL11.glBegin( GL11.GL_QUADS );
		{
			// oben
			GL11.glColor3f( 0.0f, 1.0f, 0.0f ); // Set The Color To Green
			GL11.glVertex3f( x, y, z + 1 ); // Top Right Of The Quad (Top)
			GL11.glVertex3f( x + 1, y, z + 1 ); // Top Left Of The Quad (Top)
			GL11.glVertex3f( x + 1, y, z ); // Bottom Left Of The Quad (Top)
			GL11.glVertex3f( x, y, z ); // Bottom Right Of The Quad (Top)
			
			// vorne
			GL11.glColor3f( 1.0f, 0.5f, 0.0f ); // Set The Color To Orange
			GL11.glVertex3f( x, y + 1, z ); // Top Right Of The Quad (Bottom)
			GL11.glVertex3f( x + 1, y + 1, z ); // Top Left Of The Quad (Bottom)
			GL11.glVertex3f( x + 1, y + 1, z + 1 ); // Bottom Left Of The Quad
			                                        // (Bottom)
			GL11.glVertex3f( x, y + 1, z + 1 ); // Bottom Right Of The Quad
			                                    // (Bottom)
			// unten
			GL11.glColor3f( 1.0f, 0.0f, 0.0f ); // Set The Color To Red
			GL11.glVertex3f( x, y, z ); // Top Right Of The Quad (Front)
			GL11.glVertex3f( x + 1, y, z ); // Top Left Of The Quad (Front)
			GL11.glVertex3f( x + 1, y + 1, z ); // Bottom Left Of The Quad
			                                    // (Front)
			GL11.glVertex3f( x, y + 1, z ); // Bottom Right Of The Quad (Front)
			// hinten
			GL11.glColor3f( 1.0f, 1.0f, 0.0f ); // Set The Color To Yellow
			GL11.glVertex3f( x, y + 1, z + 1 ); // Bottom Left Of The Quad
			                                    // (Back)
			GL11.glVertex3f( x + 1, y + 1, z + 1 ); // Bottom Right Of The Quad
			                                        // (Back)
			GL11.glVertex3f( x + 1, y, z + 1 ); // Top Right Of The Quad (Back)
			GL11.glVertex3f( x, y, z + 1 ); // Top Left Of The Quad (Back)
			
			GL11.glColor3f( 0.0f, 0.0f, 1.0f ); // Set The Color To Blue
			GL11.glVertex3f( x + 1, y, z + 1 ); // Top Right Of The Quad (Left)
			GL11.glVertex3f( x + 1, y, z + 1 ); // Top Left Of The Quad (Left)
			GL11.glVertex3f( x + 1, y + 1, z + 1 ); // Bottom Left Of The Quad
			                                        // (Left)
			GL11.glVertex3f( x + 1, y + 1, z + 1 ); // Bottom Right Of The Quad
			                                        // (Left)
			
			GL11.glColor3f( 1.0f, 0.0f, 1.0f ); // Set The Color To Violet
			GL11.glVertex3f( x, y, z + 1 ); // Top Right Of The Quad (Right)
			GL11.glVertex3f( x, y, z + 1 ); // Top Left Of The Quad (Right)
			GL11.glVertex3f( x, y + 1, z + 1 ); // Bottom Left Of The Quad
			                                    // (Right)
			GL11.glVertex3f( x, y + 1, z + 1 ); // Bottom Right Of The Quad
			                                    // (Right)
			
		}
		
		GL11.glEnd( );
		GL11.glPopMatrix( );
	}
	
	public static void drawESP( double d, double d1, double d2, double r, double b, double g )
	{
		GL11.glPushMatrix( );
		GL11.glEnable( 3042 );
		GL11.glBlendFunc( 770, 771 );
		GL11.glLineWidth( 1.5F );
		GL11.glDisable( GL11.GL_LIGHTING );
		GL11.glDisable( GL11.GL_TEXTURE_2D );
		GL11.glEnable( GL11.GL_LINE_SMOOTH );
		GL11.glDisable( 2929 );
		GL11.glDepthMask( false );
		GL11.glColor4d( r, g, b, 0.1825F );
		drawBoundingBox( new AxisAlignedBB( d, d1, d2, d + 1.0, d1 + 1.0, d2 + 1.0 ) );
		GL11.glColor4d( r, g, b, 1.0F );
		RenderGlobal.drawOutlinedBoundingBox( new AxisAlignedBB( d, d1, d2, d + 1.0, d1 + 1.0, d2 + 1.0 ) );
		GL11.glLineWidth( 2.0F );
		GL11.glDisable( GL11.GL_LINE_SMOOTH );
		GL11.glEnable( GL11.GL_TEXTURE_2D );
		GL11.glEnable( GL11.GL_LIGHTING );
		GL11.glEnable( 2929 );
		GL11.glDepthMask( true );
		GL11.glDisable( 3042 );
		GL11.glPopMatrix( );
	}
	
	public static void drawWayPointTracer( Waypoint w )
	{
		try
		{
			GL11.glPushMatrix( );
			GL11.glEnable( GL11.GL_LINE_SMOOTH );
			GL11.glDisable( GL11.GL_LIGHTING );
			GL11.glDisable( GL11.GL_DEPTH_TEST );
			GL11.glColor4f( 1.0F, 1.0F, 1.0F, 1.0F );
			GL11.glDisable( GL11.GL_TEXTURE_2D );
			GL11.glDepthMask( false );
			GL11.glBlendFunc( GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA );
			GL11.glEnable( GL11.GL_BLEND );
			GL11.glLineWidth( 1.5F );
			
			GL11.glColor3d( w.red, w.green, w.blue );
			GL11.glBegin( GL11.GL_LINE_LOOP );
			GL11.glVertex3d( 0, 0, 0 );
			GL11.glVertex3d( w.dX + 0.5, w.dY + 0.5, w.dZ + 0.5 );
			GL11.glEnd( );
			
			GL11.glDisable( GL11.GL_BLEND );
			GL11.glDepthMask( true );
			GL11.glEnable( GL11.GL_TEXTURE_2D );
			GL11.glColor4f( 1.0F, 1.0F, 1.0F, 1.0F );
			GL11.glEnable( GL11.GL_DEPTH_TEST );
			GL11.glEnable( GL11.GL_LIGHTING );
			GL11.glDisable( GL11.GL_LINE_SMOOTH );
			GL11.glPopMatrix( );
		} catch ( Exception e ) {
		}
	}
	
	public static void drawTag( String s, double d, double d1, double d2 ) {
		Minecraft mc = CheatBase.instance.minecraft;
		float f = 5;
		
		mc.entityRenderer.disableLightmap( 0D );
		d += 0.5D;
		d2 += 0.5D;
		FontRenderer fontrenderer = mc.fontRenderer;
		int color = 0xFFFFFFFF;
		
		float scale = f / 100;
		RenderManager renderManager1 = RenderManager.instance;
		GL11.glPushMatrix( );
		GL11.glTranslatef( (float) d, (float) d1 + 1.5F, (float) d2 - 0.5F );
		GL11.glNormal3f( 0.0F, 1.0F, 0.0F );
		GL11.glRotatef( -renderManager1.playerViewY, 0.0F, 1.0F, 0.0F );
		GL11.glRotatef( renderManager1.playerViewX, 1.0F, 0.0F, 0.0F );
		
		GL11.glScalef( -scale, -scale, scale );
		
		GL11.glDisable( GL11.GL_LIGHTING );
		GL11.glDisable( GL11.GL_DEPTH_TEST );
		GL11.glEnable( GL11.GL_BLEND );
		GL11.glBlendFunc( 770, 771 );
		byte byte0 = 0;
		fontrenderer.drawStringWithShadow( s, -fontrenderer.getStringWidth( s ) / 2, byte0, color );
		fontrenderer.drawStringWithShadow( s, -fontrenderer.getStringWidth( s ) / 2, byte0, color );
		GL11.glDisable( GL11.GL_BLEND );
		GL11.glEnable( GL11.GL_DEPTH_TEST );
		GL11.glEnable( GL11.GL_LIGHTING );
		GL11.glColor4f( 1.0F, 1.0F, 1.0F, 1.0F );
		GL11.glPopMatrix( );
		mc.entityRenderer.enableLightmap( 0D );
	}
}
