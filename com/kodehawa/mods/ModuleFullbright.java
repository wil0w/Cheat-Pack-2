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

import net.minecraft.src.Minecraft;

import com.kodehawa.CheatBase;
import com.kodehawa.util.ChatColour;
import com.kodehawa.util.Tickable;

public class ModuleFullbright extends Mod implements Tickable {
	
	public ModuleFullbright( CheatBase rc, Minecraft mc ) {
		super( Mods.FULLBRIGHT );
		cb = rc;
		minecraft = mc;
		oldGamma = minecraft.gameSettings.gammaSetting;
	}
	
	@Override
	public void onEnable( ) {
		cb.getUtils( ).addChatMessage( getActive( ) );
		cb.addToTick( this );
		cb.getUtils( ).addChatMessage( ChatColour.DARK_GRAY + "Now you can see any block in the darkness!");
	}
	
	@Override
	public void onDisable( ) {
		cb.getUtils( ).addChatMessage( getActive( ) );
		//Fixed!
		minecraft.gameSettings.gammaSetting = 0.5F;
		cb.removeFromTick( this );
	}
	
	@Override
	public void tick( ) {
		//minecraft.thePlayer.addPotionEffect( new PotionEffect( 16, 99999999, 255, true ) );
		minecraft.gameSettings.gammaSetting = 782;
	}
	
	private final CheatBase cb;
	private final Minecraft minecraft;
	public float oldGamma;
	
}
