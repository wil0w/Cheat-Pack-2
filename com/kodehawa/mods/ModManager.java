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

import java.util.ArrayList;

import com.kodehawa.CheatBase;

public class ModManager {
	
	public ArrayList<Mod> mods;
	public ArrayList<Mod> worldMods;
	public ArrayList<Mod> playerMods;
	
	public ModManager( CheatBase c ) {
		mods = new ArrayList<Mod>( );
		worldMods = new ArrayList<Mod>( );
		playerMods = new ArrayList<Mod>( );
		
		// World mods
		addWMod( new ModuleAutoFish( c, c.minecraft ) );
		addWMod( new ModuleFastPlace( c, c.minecraft ) );
		addWMod( new ModuleFullbright( c, c.minecraft ) );
		addWMod( new ModuleWaterwalk( c, c.minecraft ) );
		addWMod( new ModuleXray( c, c.minecraft ) );
		
		
		
		// Player mods
		addPMod( new ModuleFly( c, c.minecraft ) );
		addPMod( new ModuleKillAura( c, c.minecraft ) );
		addPMod( new ModuleInvulnerable(c, c.minecraft));
		addPMod( new ModuleNoFall( c, c.minecraft ) );
		addPMod( new ModuleSprint( c, c.minecraft ) );
		addPMod( new ModuleTracer( c, c.minecraft ) );
		addPMod( new ModuleFasterWalk(c, c.minecraft ) );
		addPMod( new ModuleAutoHealth( c, c.minecraft ) );
		
		
		
		// Main mod list
		for ( Mod m : worldMods ) {
			mods.add( m );
		}
		for ( Mod m : playerMods ) {
			mods.add( m );
		}
	}
	
	public void addMod( Mod m ) {
		mods.add( m );
	}
	
	public void addWMod( Mod m ) {
		worldMods.add( m );
	}
	
	public void addPMod( Mod m ) {
		playerMods.add( m );
	}
	
	public Mod getHackByName( String name ) {
		for ( int i = 0; i < mods.size( ); i++ ) {
			if ( name.equalsIgnoreCase( mods.get( i ).name ) ) {
				return mods.get( i );
			}
		}
		return null;
	}
}
