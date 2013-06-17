package com.kodehawa.gui.minecraftgui;

import net.minecraft.client.Minecraft;
import net.minecraft.src.GuiButton;

import com.kodehawa.CheatBase;
import com.kodehawa.gui.api.font.TTFRenderer;
import com.kodehawa.gui.api.render.ModGuiUtils;

public class GuiCustomButton extends GuiButton {
	
	public GuiCustomButton( int i, int j, int k, String s )
	{
		this( i, j, k, 120, 20, s );
	}
	
	public GuiCustomButton( int i, int j, int k, int l, int i1, String s )
	{
		super( i, j, k, l, i1, s );
	}
	
	@Override
	protected int getHoverState( boolean flag )
	{
		byte byte0 = 1;
		if ( !enabled )
		{
			byte0 = 0;
		}
		else if ( flag )
		{
			byte0 = 2;
		}
		return byte0;
	}
	
	@Override
	public void drawButton( Minecraft mc, int mx, int my )
	{
		boolean flag = ( mx >= xPosition ) && ( my >= yPosition ) && ( mx < ( xPosition + width ) ) && ( my < ( yPosition + height ) );
		
		if ( flag )
		{ // Hover Action
			ModGuiUtils.drawBorderedRect( xPosition, yPosition, xPosition + width, yPosition + height, 3, 0xff000099, 0x77000055 );
			TTFRenderer.drawTTFString( CheatBase.guiFont, displayString, xPosition + ( this.width / 10 ), yPosition + ( this.height / 6 ), 0xffcccccc );
		}
		else { // Normal
			ModGuiUtils.drawBorderedRect( xPosition, yPosition, xPosition + width, yPosition + height, 3, 0xff000099, 0x77000088 );
			// drawCenteredString( fontrenderer, displayString, xPosition + (
			// width / 2 ), yPosition + ( ( height - 8 ) / 2 ), 0xFFCCCCCC );
			TTFRenderer.drawTTFString( CheatBase.guiFont, displayString, xPosition + ( this.width / 10 ), yPosition + ( this.height / 6 ), 0xffcccccc );
		}
	}
	
}
