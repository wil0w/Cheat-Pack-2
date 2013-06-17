package com.kodehawa.gui.api.components;

import com.kodehawa.CheatBase;

public class Label extends Item {
	Frame parent;
	
	public Label( String s, int color ) {
		this.text = s;
		this.color = color;
	}
	
	@Override
	public void update( ) {
		this.draw( );
	}
	
	@Override
	public void draw( ) {
		// TODO Auto-generated method stub
		
		CheatBase.instance.minecraft.fontRenderer.drawString( text, x + 3, y + 3, color );
		// TTFRenderer.drawTTFString( Colony.guiFont, this.text, x + 3, y - 3,
		// color );
	}
	
	@Override
	public void drag( int x, int y ) {
		// TODO Auto-generated method stub
		return;
	}
	
	@Override
	public void onClick( int x, int y ) {
		// TODO Auto-generated method stub
		return;
	}
	
	@Override
	public void setParent( Frame e, int x, int y ) {
		this.parent = e;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean mouseOver( int x, int y ) {
		// TODO Auto-generated method stub
		return false;
	}
}
