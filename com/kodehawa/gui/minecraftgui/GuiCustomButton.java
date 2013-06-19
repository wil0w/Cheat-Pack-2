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
