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

package com.kodehawa.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.src.GuiIngame;

import org.lwjgl.opengl.GL11;

import com.kodehawa.CheatBase;

public class CGuiIngame extends GuiIngame {
	
	public static int tick = 0;
	public static CheatBase cb;
	
	
	public CGuiIngame( Minecraft par1Minecraft ) {
		super( par1Minecraft );
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Render the ingame overlay with quick icon bar, ...
	 */
	@Override
	public void renderGameOverlay( float par1, boolean par2, int par3, int par4 ) {
		super.renderGameOverlay( par1, par2, par3, par4 );
		
		GL11.glPushMatrix( );
		this.drawString( CheatBase.instance.minecraft.fontRenderer, CheatBase.instance.modName + " " + "-" + CheatBase.instance.version + " " + "- Game FPS: " + mc.debugFPS + " ", 2, 2, 0xffffff );
		GL11.glPopMatrix( );
		
	}
}
