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
	private boolean active = false;
	public int keyBind;
}
