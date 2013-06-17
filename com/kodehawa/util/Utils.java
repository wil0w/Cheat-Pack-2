package com.kodehawa.util;

import net.minecraft.client.Minecraft;
import net.minecraft.src.Entity;

public class Utils {
	
	public Utils( Minecraft mc ) {
		minecraft = mc;
	}
	
	
	public void addChatMessage( String message ) {
		String toSend = ChatColour.DARK_RED + "[Cheat Pack 2] " + message;
		minecraft.thePlayer.addChatMessage( toSend );
	}
	
	private Minecraft minecraft;
	
}
