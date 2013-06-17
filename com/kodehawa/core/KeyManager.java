package com.kodehawa.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatBase;
import com.kodehawa.console.ConsoleHelper;
import com.kodehawa.mods.Mod;
import com.kodehawa.util.ChatColour;



public class KeyManager {
	
	public File keybindsFile;
	
	public KeyManager( ) {
		keybindsFile = new File( CheatBase.instance.minecraft.mcDataDir, "/CP2/keybinds.cheatpack" );
		if ( !keybindsFile.exists( ) ) {
			try {
				// Create file
				keybindsFile.getParentFile( ).mkdirs( );
				keybindsFile.createNewFile( );
				FileWriter fstream = new FileWriter( keybindsFile );
				BufferedWriter out = new BufferedWriter( fstream );
				
		//Bugs, what bugs? :(		
				out.write( "FULLBRIGHT=" + Keyboard.KEY_F + "\r\n" );
			    out.write( "AUTOFISH=" + Keyboard.KEY_M + "\r\n" );
				out.write( "KILLAURA=" + Keyboard.KEY_P + "\r\n" );
				out.write( "SPRINT=" + Keyboard.KEY_K + "\r\n" );
				out.write( "FASTPLACE=" + Keyboard.KEY_N + "\r\n" );
				out.write( "WATERWALK=" + Keyboard.KEY_L + "\r\n" );
				out.write( "XRAY=" + Keyboard.KEY_X + "\r\n" );
				out.write( "FLY=" + Keyboard.KEY_R + "\r\n" );
				out.write( "INVULNERABLE" + Keyboard.KEY_W + "\r\n");
				out.write( "AUTOHEALTH" + Keyboard.KEY_H + "\r\n");
				
				
				
				// Close the output stream
				out.close( );
			} catch ( Exception e ) { // Catch exception if any
				System.err.println( "[Cheat Pack 2.3] Error writing keybinds!: " + e.getMessage( ) );
			}
			readKeysAndBind( );
		} else {
			readKeysAndBind( );
		}
	}
	
	public void readKeysAndBind( ) {
		// Initialize variables
		int binding;
		String str = "";
		String name;
		
		InputStream fis = null;
		BufferedReader br;
		String line;
		
		// Try to create FIS
		try {
			fis = new FileInputStream( keybindsFile );
		} catch ( FileNotFoundException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace( );
		}
		// Create BR
		br = new BufferedReader( new InputStreamReader( fis, Charset.forName( "UTF-8" ) ) );
		
		
		try {
			while ( ( line = br.readLine( ) ) != null ) {
				
				str = line.replaceAll( "[^\\d.]", "" );
				name = line.replaceAll( "[\\d.]", "" );
				binding = 0;
				if ( !str.equals( "" ) ) {
					binding = Integer.parseInt( str );
				}
				name = name.replace( "=", "" );
			    //System.out.println( "binding==" + binding );
				for ( Mod m : CheatBase.instance.mmanager.mods ) {
					//System.out.println( "Attempting to print keybinding: " + m.name );
					//System.out.println( "m.name==" + m.name + "|||" + "name==" + name );
					if ( name.equalsIgnoreCase( m.name ) || m.name.equalsIgnoreCase( name ) ) {
						if ( binding != 0 ) {
							m.keyBind = binding;
							//System.out.println( "Mod " + m.name + " was sucefully bound to key ID " + binding + "!" );
							break;
						} else {
							//System.err.println( "[Cheat Pack 2.3] WARNING: NULL BINDING FOUND! D:" );
						}
					} else {
						//System.err.println( "[Cheat Pack 2.3] Failed to create " + m.name + " as " + name );
					}
				}
			}
		} catch ( IOException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace( );
		}
	}
	
	public void writeNewKeybinds( ) {
		try {
			FileWriter fstream = new FileWriter( keybindsFile );
			BufferedWriter out = new BufferedWriter( fstream );
			
			// OOPish
			for ( Mod m : CheatBase.instance.mmanager.mods ) {
				out.write( m.name + "=" + m.keyBind + "\r\n" );
			}
			
			// Close the output stream
			out.close( );
			if ( CheatBase.instance.minecraft.theWorld != null ) {
				ConsoleHelper.addMessage( ChatColour.RED + "[Cheat Pack 2]" + " " + ChatColour.DARK_GRAY + "Keybinds written!" );
			}
		} catch ( Exception e ) {
			System.err.println( "[Cheat Pack 2.3] Error while writting keybinding D: : " );
			e.printStackTrace( );
		}
	}
}
