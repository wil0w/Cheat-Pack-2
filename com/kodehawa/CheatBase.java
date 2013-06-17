package com.kodehawa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import net.minecraft.client.Minecraft;
import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.Gui;

import org.lwjgl.input.Keyboard;

import com.kodehawa.core.CheckKey;
import com.kodehawa.core.KeyManager;
import com.kodehawa.gui.CGuiIngame;
import com.kodehawa.gui.api.components.Frame;
import com.kodehawa.gui.api.components.TestGui;
import com.kodehawa.gui.api.font.CustomFont;
import com.kodehawa.gui.api.testing.AlertHandler;
import com.kodehawa.mods.AutoFish;
import com.kodehawa.mods.Fly;
import com.kodehawa.mods.Fullbright;
import com.kodehawa.mods.KillAura;
import com.kodehawa.mods.Mod;
import com.kodehawa.mods.ModManager;
import com.kodehawa.mods.NoFall;
import com.kodehawa.mods.Sprint;
import com.kodehawa.mods.Waterwalk;
import com.kodehawa.mods.Xray;
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
	
	public String version = "Minecraft 1.5.2";
	public String copyright = "(C) Kodehawa's Mods Team 2012-2013";
	
	
	//Translations
	public final Properties transTable = new Properties(); public final Properties cfgTable = new Properties();
	
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
		femanager = new FrenemyManager( );
		console = new Console( );
		
		
		guiFont = new CustomFont( minecraft, "Bauhaus", 20 );
		for ( Mod m : mmanager.mods ) {
			keyShit.put( m, m.keyBind );
		}
	}
	
	//Load mod settings.... (Dropped from Cheat Pack, credits to Binkan :3)
	

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
			if ( ck.checkKey( Keyboard.KEY_GRAVE ) ) {
				// TODO Console
			minecraft.displayGuiScreen( new Console( ) );

		for ( Map.Entry<Mod, Integer> e : keyShit.entrySet( ) ) {
			if ( ck.checkKey( e.getKey( ).keyBind ) ) {
				e.getKey( ).toggle( );
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
	   
	   public static String translate(String s)
	   {
	     return cb.transTable.getProperty(s, s);
	   }
	   
	
	public static KillAura killaura;
	public static Fullbright fullbright;
	public static Fly fly;
	public static NoFall nof;
	public static Sprint spr;
	public static Xray xray;
	public static Waterwalk ww;
	public static AutoFish af;
	public CheckKey ck;
	public Utils utils;
	public static Minecraft minecraft;
	public ArrayList<Tickable> tickables = new ArrayList<Tickable>( );
	public ArrayList<Mod> mods = new ArrayList<Mod>( );
	public HashMap<Mod, Integer> keyShit;
	public static ModManager mmanager;
	public KeyManager kmanager;
	public FrenemyManager femanager;
	public TestGui modgui;
	public static boolean hasZMod;
	public static boolean hasModLoader;
	public AlertHandler amanager;
	public WaypointManager wmanager;
	public Console console;
	public static CheatBase cb;
    public static boolean pausegame;
	public static boolean disablefov;
	public static boolean instantdrop;
	public static boolean instantxp;
	public static boolean flying;
	public static boolean invulnerable;
	public static boolean showgui;
	public static boolean killersight;
	public static boolean creative;
	public static int prevKey = 203;
	public static int nextKey = 205;
	public static int escapeKey = 1;
	public static int setKey = 28;
	public static int unsetKey = 211;

	
	
}
