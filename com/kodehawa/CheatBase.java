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


package com.kodehawa;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.src.Gui;
import net.minecraft.src.ILogAgent;

import org.lwjgl.input.Keyboard;

import com.kodehawa.core.CheckKey;
import com.kodehawa.core.KeyManager;
import com.kodehawa.core.TranslationWritter;
import com.kodehawa.gui.CGuiIngame;
import com.kodehawa.gui.api.components.Frame;
import com.kodehawa.gui.api.components.TestGui;
import com.kodehawa.gui.api.font.CustomFont;
import com.kodehawa.gui.api.testing.AlertHandler;
import com.kodehawa.mods.Mod;
import com.kodehawa.mods.ModManager;
import com.kodehawa.newgui.GuiItemSelection;
import com.kodehawa.newgui.GuiXraySelectedBlock;
import com.kodehawa.players.FrenemyManager;
import com.kodehawa.util.Console;
import com.kodehawa.util.Tickable;
import com.kodehawa.util.Utils;
import com.kodehawa.util.WaypointManager;

public class CheatBase {
	
	public static CheatBase instance;
	
	
	public static CustomFont guiFont;
	public static boolean truelyinstalled = true;
	
	long now;
	long then;
	
	public String modName = "Cheat Pack 2";
	public String mcversion = "Minecraft 1.5.2";
	public String modversion = "Cheat Pack 2.5";
	
	
	public CheatBase( Minecraft mc ) {
		instance = this;
		minecraft = mc;
		ck = new CheckKey( mc );
		init( );
	}
	
	public static ArrayList<String> enabledMods = new ArrayList<String>( );
	
	public void init( ) {
		utils = new Utils( minecraft );
		
		now = System.currentTimeMillis( );
		then = now + 250;
		
		keyShit = new HashMap<Mod, Integer>( );
		mmanager = new ModManager( this );
		modgui = new TestGui( );
		kmanager = new KeyManager( );
		translations = new TranslationWritter( );
		femanager = new FrenemyManager( );
		console = new Console( );
		LogAgent.logInfo("Initialization Complete");
		LogAgent.logInfo("SSP/SMP mode enabled.");
		guiFont = new CustomFont( minecraft, "Bauhaus", 20 );
		for ( Mod m : mmanager.mods ) {
			keyShit.put( m, m.keyBind );
		}
		LogAgent.logInfo(" + Modloader compatibility enabled");
		LogAgent.logInfo(modversion + " - " + "Build 6 - 24.06.2013");
        
	}
	
	

	protected static void checkEnvironment()
    {
        hasModLoader = cb.classExists("ModLoader");

        if (hasModLoader)
        {
        	LogAgent.logInfo("CP2: ModLoader compatibility activated");
        }
    }
	

	public void reload( ) {
		for ( Mod m : mmanager.mods ) {
			m.turnOff( );
		}
		init( );
	}
	
	public void tick( ) {
		for ( Tickable tick : tickables ) { 
			tick.tick( );
		}
		
		/**
		 * Swaps out the GuiIngame
		 */
		if ( this.minecraft.ingameGUI.getClass( ) != CGuiIngame.class ) {
			this.minecraft.ingameGUI = new CGuiIngame( this.minecraft );
		}
		
		updateArray( );
		checkForKeyPress( );
		updatePinnedFrames( );
	}
	
	
	public Utils getUtils( ) {
		return utils;
	}
	
	public void updatePinnedFrames( ) {
		if ( ( minecraft.currentScreen == null ) || ( minecraft.currentScreen == (Gui) minecraft.ingameGUI ) ) {
			for ( Frame frame : modgui.frames ) {
				if ( frame.pinned && frame.pinnable ) {
					frame.update( );
				}
			}
		}
	}
	
	public void addToTick( Tickable tickable ) {
		if ( !tickables.contains( tickable ) ) {
			tickables.add( tickable );
		}
	}
	
	public void removeFromTick( Tickable tickable ) {
		if ( tickables.contains( tickable ) ) {
			tickables.remove( tickable );
		}
	}
	

	
	public void checkForKeyPress( ) {
		
		if ( ck.checkKey( Keyboard.KEY_G ) ) {
			minecraft.displayGuiScreen( modgui );
			
			if ( ck.checkKey( Keyboard.KEY_P ) ) {
				minecraft.displayGuiScreen( guicheat );
			
			if ( ck.checkKey( Keyboard.KEY_GRAVE ) ) {
				// TODO Console
			minecraft.displayGuiScreen( new Console( ) );
			if ( ck.checkKey( Keyboard.KEY_J ) ) {
				minecraft.displayGuiScreen( new GuiXraySelectedBlock( ) );
				

		for ( Map.Entry<Mod, Integer> e : keyShit.entrySet( ) ) {
			if ( ck.checkKey( e.getKey( ).keyBind ) ) {
				e.getKey( ).toggle( );
			}
		}
		}
		}
		}
		}
		}
	
	
		
	
	
	public void updateArray( ) {
		for ( int i = 0; i < mmanager.mods.size( ); i++ ) {
			if ( mmanager.mods.get( i ).isActive( ) && !enabledMods.contains( mmanager.mods.get( i ).name ) ) {
				enabledMods.add( mmanager.mods.get( i ).name );
			} else if ( !mmanager.mods.get( i ).isActive( ) ) {
				enabledMods.remove( mmanager.mods.get( i ).name );
			}
		}
	}
	
	
	   public boolean classExists(String className)
	    {
	      try
	      {
	        Class.forName(className, false, ClassLoader.getSystemClassLoader());
	        return true;
	      } catch (Throwable t) {
	      }
	      return false;
	    }
	 

	public final static ILogAgent LogAgent = new net.minecraft.src.LogAgent("Cheat Pack 2", " [Cheat Pack 2] [CB]", (new File(Minecraft.getMinecraftDir(), "output-cient.log")).getAbsolutePath());
	public CheckKey ck; 
	public GuiXraySelectedBlock xraygui;
	public Utils utils;
	public static Minecraft minecraft;
	public ArrayList<Tickable> tickables = new ArrayList<Tickable>( );
	public ArrayList<Mod> mods = new ArrayList<Mod>( );
	public HashMap<Mod, Integer> keyShit;
	public static ModManager mmanager;
	public KeyManager kmanager;
	public TranslationWritter translations;
	public FrenemyManager femanager;
	public TestGui modgui;
	public static boolean hasModLoader;
	public AlertHandler amanager;
	public WaypointManager wmanager;
	public Console console;
	public CheatBase cheatbase;
	public static CheatBase cb;
	public GuiItemSelection guicheat;
    
	
	
}
