/**
 * 
 */
package com.kodehawa.mods;

import java.awt.AWTException;
import java.awt.Robot;

import net.minecraft.client.Minecraft;

import com.kodehawa.CheatBase;
import com.kodehawa.util.ChatColour;
import com.kodehawa.util.Tickable;

/**
 * @author flanagan
 * 
 */
public class MiningBot extends Mod implements Tickable {
	
	private final CheatBase cb;
	private final Minecraft mc;
	private Robot orebot;
	
	public MiningBot( CheatBase c, Minecraft m ) {
		super( Mods.MININGBOT );
		cb = c;
		mc = m;
		try {
			orebot = new Robot( );
		} catch ( AWTException e ) {
			// TODO Auto-generated catch block
			mc.thePlayer.addChatMessage( ChatColour.DARK_RED + "WARNING!" + ChatColour.DARK_AQUA + "MineBot could not be created!" );
			mc.thePlayer.addChatMessage( ChatColour.DARK_AQUA + "Please report the following error to Kodehawa:" );
			mc.thePlayer.addChatMessage( ChatColour.RED + e.getStackTrace( ) );
			this.turnOff( );
		}
	}
	
	
	@Override
	public void tick( ) {
		// TODO Auto-generated method stub
		if ( this.mc.currentScreen == null ) {
			orebot.mousePress( 16 );
		}
	}
	

	@Override
	public void onEnable( ) {
		// TODO Auto-generated method stub
		cb.addToTick( this );
		cb.getUtils( ).addChatMessage( getActive( ) );
	}
	

	@Override
	public void onDisable( ) {
		// TODO Auto-generated method stub
		cb.removeFromTick( this );
		cb.getUtils( ).addChatMessage( getActive( ) );
	}
	
}
