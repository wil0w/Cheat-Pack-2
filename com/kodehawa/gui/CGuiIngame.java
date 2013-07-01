

package com.kodehawa.gui;

import net.minecraft.src.GuiIngame;
import net.minecraft.src.Minecraft;

import org.lwjgl.opengl.GL11;

import com.kodehawa.CheatBase;

/**
 * @author godshawk
 */

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
		 
		
		GL11.glPopMatrix( );
		
	}
}
