/**
 * I've does my most because I can  *Portal reference :)
 */

package com.kodehawa.mods;

import com.kodehawa.util.ChatColour;

public abstract class Mod {
	
	public Mod( Mods mod ) {
		name = mod.getName( );
	}
	
	public final void turnOn( ) {
		active = true;
		onEnable( );
	}
	
	public final void turnOff( ) {
		active = false;
		onDisable( );
	}
	
	public final void toggle( ) {
		active = !active;
		if ( active ) {
			onEnable( );
		} else {
			onDisable( );
		}
	}
	
	public final boolean isActive( ) {
		return active;
	}
	
	public final String getActive( ) {
		if ( active ) {
			return ChatColour.WHITE + name + ChatColour.GREEN + " Active";
		} else {
			return ChatColour.WHITE + name + ChatColour.RED + " Inactive";
		}
	}
	
	public abstract void onEnable( );
	
	public abstract void onDisable( );
	
	public String name;
	//Make booleans! Ready go! *Ehmm... you forgot the static okay no xD
	private boolean active = false;
	public int keyBind;
}
