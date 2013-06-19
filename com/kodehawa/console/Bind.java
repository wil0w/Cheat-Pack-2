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

package com.kodehawa.console;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatBase;
import com.kodehawa.mods.Mod;
import com.kodehawa.util.ChatColour;

public class Bind implements BaseCommand {
	
	private final String name;
	String endres = "";
	
	public Bind( ) {
		this.name = "bind";
	}
	
	@Override
	public void onRun( String[ ] cmd ) {
		// TODO Auto-generated method stub
		endres = output( cmd );
	}
	
	@Override
	public String getName( ) {
		// TODO Auto-generated method stub
		return this.name;
	}
	
	@Override
	public String showHelp( ) {
		// TODO Auto-generated method stub
		return new String( ChatColour.RED + "Usage: " + ChatColour.AQUA + this.getName( ) + " <hack> <key>" );
		// ConsoleHelper.addMessage( ChatColour.RED + "Hint: " + ChatColour.AQUA
		// + "To remove a keybind, do \'bind <hack> none\'!" );
	}
	
	@Override
	public String output( ) {
		// TODO Auto-generated method stub
		return endres;
	}
	
	public String output( String[ ] cmd ) {
		try {
			if ( cmd.length <= 1 ) {
				throw new NullPointerException( );
			} else if ( cmd.length == 2 ) {
				Mod m = CheatBase.mmanager.getHackByName( cmd [ 1 ] );
				CheatBase.instance.kmanager.writeNewKeybinds( );
				return new String( ChatColour.AQUA + m.name + " unbound!" );
			} else if ( cmd.length == 3 ) {
				Mod m = CheatBase.mmanager.getHackByName( cmd [ 1 ] );
				m.keyBind = Keyboard.getKeyIndex( cmd [ 2 ].toUpperCase( ) );
				CheatBase.instance.kmanager.writeNewKeybinds( );
				return new String( ChatColour.AQUA + m.name + " bound to " + cmd [ 2 ] + "!" );
			}
			return showHelp( );
		} catch ( Exception e ) {
			return showHelp( );
		}
	}
}
