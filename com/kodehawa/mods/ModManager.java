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
		addWMod( new AutoFish( c, c.minecraft ) );
		//addWMod( new ChestESP( c, c.minecraft ) );
		addWMod( new FastPlace( c, c.minecraft ) );
		addWMod( new Fullbright( c, c.minecraft ) );
		addWMod( new Waterwalk( c, c.minecraft ) );
		addWMod( new Xray( c, c.minecraft ) );
		addWMod( new Waypoints( c, c.minecraft ) );
		addWMod( new Trajectories( c, c.minecraft ) );
		
		
		// Player mods
		addPMod( new Fly( c, c.minecraft ) );
		addPMod( new KillAura( c, c.minecraft ) );
		addPMod( new Invulnerable(c, c.minecraft));
		addPMod( new NoFall( c, c.minecraft ) );
		addPMod( new PlayerESP( c, c.minecraft ) );
		addPMod( new Sprint( c, c.minecraft ) );
		addPMod( new Tracer( c, c.minecraft ) );
		addPMod( new FasterWalk(c, c.minecraft ) );
		addPMod( new AutoHealth( c, c.minecraft ) );
		//addPMod( new InvEdit( c,c.minecraft ) );
		
		
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
