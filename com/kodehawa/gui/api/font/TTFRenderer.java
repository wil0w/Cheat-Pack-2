package com.kodehawa.gui.api.font;

import net.minecraft.client.Minecraft;
import net.minecraft.src.StringUtils;

import org.lwjgl.opengl.GL11;

import com.kodehawa.CheatBase;

public class TTFRenderer {
	static Minecraft mc = CheatBase.instance.minecraft;
	
	public static void drawTTFString( CustomFont f, String text, double x, double y, int color )
	{
		GL11.glEnable( 3042 );
		GL11.glDisable( GL11.GL_DEPTH_TEST );
		GL11.glEnable( GL11.GL_POINT_SMOOTH );
		GL11.glHint( GL11.GL_POINT_SMOOTH_HINT, GL11.GL_NICEST );
		GL11.glDepthMask( false );
		f.drawGoodString( mc.ingameGUI, text, x, y, color, false );
		GL11.glDepthMask( true );
		GL11.glDisable( GL11.GL_POINT_SMOOTH );
		GL11.glEnable( GL11.GL_DEPTH_TEST );
		GL11.glDisable( 3042 );
	}
	
	public static void drawTTFStringWithShadow( CustomFont f, String text, double x, double y, int color )
	{
		text = text.replace( "\247r", "\247f" );
		drawTTFString( f, StringUtils.stripControlCodes( text ), x + 0.5, y + 0.5, 0x000000 );
		drawTTFString( f, text, x, y, color );
	}
	
	public static void drawTTFStringWithShadow( CustomFont f, String text, double x, double y, int color, double shadow )
	{
		drawTTFString( f, StringUtils.stripControlCodes( text ), x + shadow, y + shadow, 0x000000 );
		drawTTFString( f, text, x, y, color );
	}
	
	public static void drawOutlinedTTFString( CustomFont f, String text, double x, double y, int color, double shadow )
	{
		drawTTFString( f, StringUtils.stripControlCodes( text ), x - shadow, y, 0x000000 );
		drawTTFString( f, StringUtils.stripControlCodes( text ), x + shadow, y, 0x000000 );
		drawTTFString( f, StringUtils.stripControlCodes( text ), x, y - shadow, 0x000000 );
		drawTTFString( f, StringUtils.stripControlCodes( text ), x, y + shadow, 0x000000 );
		drawTTFString( f, text, x, y, color );
	}
	
}
