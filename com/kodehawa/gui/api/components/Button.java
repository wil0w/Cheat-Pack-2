package com.kodehawa.gui.api.components;

import com.kodehawa.CheatBase;
import com.kodehawa.gui.api.render.ModGuiUtils;
import com.kodehawa.mods.Mod;

public class Button extends Item {
	Frame parent;
	Mod m = null;
	int oldColor = 0;
	
	public Button( String s, int color, int color2 ) {
		this( s, color, color2, null );
	}
	
	public Button( String s, int color, int color2, Mod m ) {
		this.text = s;
		this.color = color;
		this.color2 = color2;
		this.oldColor = color2;
		this.m = m;
	}
	
	@Override
	public void draw( ) {
		// TODO Auto-generated method stub
		ModGuiUtils.drawBorderedRect( x, y, x + width, y + height, 2, color, 0x77000077 );
		CheatBase.instance.minecraft.fontRenderer.drawString( text, x + 2, y + 2, color2 );
		// TTFRenderer.drawTTFString( Colony.guiFont, this.text, x + 2, y - 1,
		// color2 );
	}
	
	@Override
	public void update( ) {
		if ( this.m != null ) {
			this.text = this.m.name;
			if ( m.isActive( ) ) {
				color2 = 0x00ff00;
			} else {
				color2 = oldColor;
			}
		}
		this.draw( );
	}
	
	@Override
	public void drag( int x, int y ) {
		// TODO Auto-generated method stub
		return;
	}
	
	@Override
	public void onClick( int x, int y ) {
		// TODO Auto-generated method stub
		if ( this.clickedInside( x, y ) ) {
			if ( this.m != null ) {
				this.m.toggle( );
			} else {
				return;
			}
		}
	}
	
	@Override
	public void setParent( Frame e, int x, int y ) {
		this.parent = e;
		this.x = x;
		this.y = y;
	}
	
	public void setWidth( int w ) {
		this.width = w;
	}
	
	public void setHeight( int h ) {
		this.height = h;
	}
	
	@Override
	public boolean mouseOver( int x, int y ) {
		if ( x >= this.x ) {
			if ( x <= ( this.x + this.width ) ) {
				if ( y >= this.y ) {
					if ( y <= ( this.y + this.height ) ) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
