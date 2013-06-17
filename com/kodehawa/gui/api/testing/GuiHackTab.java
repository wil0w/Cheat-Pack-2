package com.kodehawa.gui.api.testing;

import java.util.ArrayList;

import net.minecraft.src.FontRenderer;
import net.minecraft.src.Gui;

public class GuiHackTab {
	public int posX, posY;
	private final int x, y;
	public int width;
	public boolean draggable = false;
	private boolean dragging = false;
	private final String title;
	private final int toptextcolor;
	private final int textcolor;
	private final int bgcolor;
	private final int bgtopcolor;
	private final int btnbordercolor;
	private final ArrayList Items = new ArrayList( );
	private final ArrayList eItems = new ArrayList( );
	private int curItem;
	private int onColor = 0xFF00FF00, offColor = 0xFFFF0000;
	
	public GuiHackTab( int par0, int par1, int par2, String par3, int par4, int par5, int par6, int par7, int par8, boolean par9 ) {
		// posX, posY, width, title, toptextcolor, textcolor, bgcolor,
		// bgtopcolor, btnbordercolor
		x = posX = par0;
		y = posY = par1;
		width = par2;
		title = par3;
		toptextcolor = par4;
		textcolor = par5;
		bgcolor = par6;
		bgtopcolor = par7;
		btnbordercolor = par8;
		draggable = par9;
	}
	
	public void setColors( int par0, int par1 ) {
		onColor = par0;
		offColor = par1;
	}
	
	public void resetPosition( ) {
		posX = x;
		posY = y;
	}
	
	public void addItem( String par0, boolean par1 ) {
		Items.add( par0 );
		eItems.add( par1 );
	}
	
	public void setDraggable( boolean par0 ) {
		draggable = par0;
		if ( !par0 ) {
			dragging = false;
		}
	}
	
	public void toggleItem( ) {
		eItems.set( curItem, !(Boolean) eItems.get( curItem ) );
	}
	
	public void toggleItem( int par0 ) {
		eItems.set( par0, !(Boolean) eItems.get( par0 ) );
	}
	
	public void toggleItem( String par0 ) {
		for ( int i = 0; i < Items.size( ); i++ ) {
			if ( Items.get( i ) == par0 ) {
				eItems.set( i, !(Boolean) eItems.get( i ) );
				return;
			}
		}
	}
	
	public void setItem( int par0, boolean par1 ) {
		eItems.set( par0, par1 );
	}
	
	public void setItem( String par0, boolean par1 ) {
		for ( int i = 0; i < Items.size( ); i++ ) {
			if ( Items.get( i ) == par0 ) {
				eItems.set( i, par1 );
				return;
			}
		}
	}
	
	public boolean getItem( int par0 ) {
		return (Boolean) eItems.get( par0 );
	}
	
	public boolean getItem( String par0 ) {
		for ( int i = 0; i < Items.size( ); i++ ) {
			if ( Items.get( i ) == par0 ) {
				return (Boolean) eItems.get( i );
			}
		}
		return false;
	}
	
	public void draw( FontRenderer f ) {
		Gui.drawRect( posX, posY, posX + width, posY + 12, bgtopcolor );
		f.drawString( title, posX + 2, posY + 2, toptextcolor );
		
		for ( int i = 0; i < Items.size( ); i++ ) {
			Gui.drawRect( posX, posY + 13 + ( i * 12 ), posX + width, posY + 13 + ( ( i + 1 ) * 12 ), bgcolor );
			GuiHack.drawOutlinedRect( ( posX + width ) - 18, posY + 13 + ( i * 12 ) + 3, ( posX + width ) - 3, posY + 13 + ( i * 12 ) + 9, (Boolean) eItems.get( i ) ? onColor : offColor, btnbordercolor, -1 );
			f.drawString( Items.get( i ).toString( ), posX + 2, posY + 13 + ( i * 12 ) + 2, textcolor );
		}
	}
	
	public int clicked( int par0, int par1 ) {
		if ( GuiHack.clickedInRect( par0, par1, posX, posY, posX + width, posY + 12 ) ) {
			GuiHack.offsetX = posX - par0;
			GuiHack.offsetY = posY - par1;
			this.dragging( true );
		}
		for ( int i = 0; i < Items.size( ); i++ ) {
			if ( ( par0 >= ( ( posX + width ) - 19 ) ) && ( par1 >= ( posY + 13 + ( i * 12 ) + 2 ) ) && ( par0 <= ( ( posX + width ) - 3 ) ) && ( par1 <= ( posY + 13 + ( i * 12 ) + 9 ) ) ) {
				curItem = i;
				return i;
			}
		}
		return -1;
	}
	
	public void drag( int par0, int par1 ) {
		if ( !draggable || !dragging ) {
			return;
		}
		posX = par0;
		posY = par1;
	}
	
	public void dragging( boolean par0 ) {
		dragging = par0;
	}
}
