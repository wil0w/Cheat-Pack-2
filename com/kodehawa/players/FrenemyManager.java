package com.kodehawa.players;

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

import com.kodehawa.CheatBase;
import com.kodehawa.console.ConsoleHelper;
import com.kodehawa.mods.Vars;
import com.kodehawa.util.ChatColour;

public class FrenemyManager {
	
	public File friendsFile;
	public File enemyFile;
	
	public FrenemyManager( ) {
		friendsFile = new File( CheatBase.instance.minecraft.mcDataDir, "/CP2/friendlist.cheatpack" );
		enemyFile = new File( CheatBase.instance.minecraft.mcDataDir, "/CP2/enemielist.cheatpack" );
		
		if ( !friendsFile.exists( ) ) {
			try {
				// Create file
				friendsFile.getParentFile( ).mkdirs( );
				friendsFile.createNewFile( );
				FileWriter fstream = new FileWriter( friendsFile );
				BufferedWriter out = new BufferedWriter( fstream );
				
				// This will, ideally, only be used once.
				out.write( "Kodehawa\r\n" );
				out.write( "Ubu13\r\n" );
				out.write( "Notch\r\n" );
				out.write( "jeb_\r\n" );
				out.write( "Kraiback\r\n" );
				
				
				// Close the output stream
				out.close( );
			} catch ( Exception e ) { // Catch exception if any
				System.err.println( "[Cheat Pack 2.3] Error writing friends!: " + e.getMessage( ) );
			}
			readAndBind( );
		} else {
			readAndBind( );
		}
		
		if ( !enemyFile.exists( ) ) {
			try {
				// Create file
				enemyFile.getParentFile( ).mkdirs( );
				enemyFile.createNewFile( );
				FileWriter fstream = new FileWriter( enemyFile );
				BufferedWriter out = new BufferedWriter( fstream );
				// Hardcoded: Just to be sure we get the initial stuff good.
				// This will, ideally, only be used once.
				out.write( "PvpTroll\r\n" );
				
				// Close the output stream
				out.close( );
			} catch ( Exception e ) { // Catch exception if any
				System.err.println( "[Cheat Pack 2.3] Error writing enemies!: " + e.getMessage( ) );
			}
			readAndBind( );
		} else {
			readAndBind( );
		}
	}
	
	public void readAndBind( ) {
		String str = "";
		InputStream fis = null;
		BufferedReader br;
		String line;
		
		if ( friendsFile.exists( ) ) {
			// Try to create FIS
			try {
				fis = new FileInputStream( friendsFile );
				
				// Create BR
				br = new BufferedReader( new InputStreamReader( fis, Charset.forName( "UTF-8" ) ) );
				
				// Don't forget to catch exceptions!
				try {
					while ( ( line = br.readLine( ) ) != null ) {
						// Deal with the line
						// Get the name and keybind number
						str = line;
						Vars.friends.add( str );
					}
				} catch ( IOException e ) {
					// TODO Auto-generated catch block
					e.printStackTrace( );
				}
			} catch ( FileNotFoundException e ) {
				// TODO Auto-generated catch block
				e.printStackTrace( );
			}
		}
		
		if ( enemyFile.exists( ) ) {
			try {
				fis = new FileInputStream( enemyFile );
				
				// Create BR
				br = new BufferedReader( new InputStreamReader( fis, Charset.forName( "UTF-8" ) ) );
				
				// Don't forget to catch exceptions!
				try {
					while ( ( line = br.readLine( ) ) != null ) {
						// Deal with the line
						// Get the name and keybind number
						str = line;
						Vars.enemies.add( str );
					}
				} catch ( IOException e ) {
					// TODO Auto-generated catch block
					e.printStackTrace( );
				}
			} catch ( FileNotFoundException e ) {
				// TODO Auto-generated catch block
				e.printStackTrace( );
			}
		}
	}
	
	public void writeFriends( ) {
		try {
			FileWriter fstream = new FileWriter( friendsFile );
			BufferedWriter out = new BufferedWriter( fstream );
			
			// OOPish
			for ( String s : Vars.friends ) {
				out.write( s + "\r\n" );
			}
			
			// Close the output stream
			out.close( );
			if ( CheatBase.instance.minecraft.theWorld != null ) {
				ConsoleHelper.addMessage( ChatColour.BLUE + "Friends written!" );
			}
		} catch ( Exception e ) {
			System.err.println( "ERROR WHILE WRITING FRIENDS: " );
			e.printStackTrace( );
		}
	}
	
	public void writeEnemies( ) {
		try {
			FileWriter fstream = new FileWriter( enemyFile );
			BufferedWriter out = new BufferedWriter( fstream );
			// OOPish
			for ( String s : Vars.enemies ) {
				out.write( s + "\r\n" );
			}
			
			// Close the output stream
			out.close( );
			if ( CheatBase.instance.minecraft.theWorld != null ) {
			}
		} catch ( Exception e ) {
			System.err.println( "ERROR WHILE WRITING ENEMIES: " );
			e.printStackTrace( );
		}
	}
}
