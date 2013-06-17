package com.kodehawa.gui.api.testing;

import net.minecraft.src.Gui;
import net.minecraft.src.GuiScreen;

import org.lwjgl.input.Keyboard;

public class GuiHack extends GuiScreen {
	
	public static int offsetX = 0, offsetY = 0;
	
	@Override
	public boolean doesGuiPauseGame( ) {
		return false;
	}
	
	@Override
	public void initGui( ) {
		Keyboard.enableRepeatEvents( true );
		GuiHackTabs.createTabs( );
	}
	
	@Override
	public void onGuiClosed( ) {
		Keyboard.enableRepeatEvents( true );
	}
	
	@Override
	protected void keyTyped( char c, int i ) {
		if ( i == 1 ) {
			mc.displayGuiScreen( null );
			return;
		}
	}
	
	@Override
	public void drawScreen( int i, int j, float f ) {
		mouseDraggedMode( i, j );
		
		GuiHackTabs.player.draw( fontRenderer ); // every tab needs this
		GuiHackTabs.testings.draw( fontRenderer ); // every tab needs this
	}
	
	@Override
	protected void mouseClicked( int i, int j, int k ) {
		switch ( GuiHackTabs.player.clicked( i, j ) ) {
			case 0:
				// code to execute when first item in this tab is clicked
				GuiHackTabs.player.toggleItem( ); // use this to toggle the
				                                  // current item (the color in
				                                  // the rectangle)
				break;
			case 1:
				// code to execute when second item in this tab is clicked
				GuiHackTabs.player.toggleItem( ); // use this to toggle the
				                                  // current item (the color in
				                                  // the rectangle)
				break;
		}
		
		switch ( GuiHackTabs.testings.clicked( i, j ) ) {
			case 0:
				// code to execute when first item in this tab is clicked
				GuiHackTabs.testings.toggleItem( ); // use this to toggle the
				                                    // current item (the color
				                                    // in the rectangle)
				break;
			case 1:
				// code to execute when second item in this tab is clicked
				GuiHackTabs.testings.toggleItem( ); // use this to toggle the
				                                    // current item (the color
				                                    // in the rectangle)
				break;
		}
		
	}
	
	public void mouseDraggedMode( int i, int j )
	{
		GuiHackTabs.player.drag( i + offsetX, j + offsetY ); // every tab need
		                                                     // this
		GuiHackTabs.testings.drag( i + offsetX, j + offsetY ); // every tab need
		                                                       // this
	}
	
	@Override
	protected void mouseMovedOrUp( int i, int j, int k ) {
		if ( k == 0 ) {
			GuiHackTabs.player.dragging( false ); // every tab need this
			GuiHackTabs.testings.dragging( false ); // every tab need this
		}
	}
	
	/** utils **/
	public static boolean clickedInRect( int par0, int par1, int par2, int par3, int par4, int par5 ) {
		if ( ( par2 <= par0 ) && ( par3 <= par1 ) && ( par4 >= par0 ) && ( par5 >= par1 ) ) {
			return true;
		}
		return false;
	}
	
	public static void drawOutlinedRect( int x1, int y1, int x2, int y2, int rcolor, int color, int thickness ) {
		Gui.drawRect( x1, y1, x2, y2, rcolor );
		Gui.drawRect( x1, y1, x2, y1 + thickness, color );
		Gui.drawRect( x1, y2, x2, y2 - thickness, color );
		Gui.drawRect( x1, y1, x1 + thickness, y2, color );
		Gui.drawRect( x2, y1, x2 - thickness, y2, color );
	}
}
