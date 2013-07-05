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

import net.minecraft.src.Entity;
import net.minecraft.src.EntityLivingBase;
import net.minecraft.src.Minecraft;

import com.kodehawa.CheatBase;
import com.kodehawa.util.Tickable;

public class ModuleInvulnerable extends Mod implements Tickable {

	private Entity entity;
	private EntityLivingBase entitylivingb;
	
	public ModuleInvulnerable( CheatBase c, Minecraft m ) {
		super( Mods.Invulnerable );
		cheatbase = c;
		mc = m;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * How many "9"'s I can put? A LOT
	 */

	@Override
	public void tick() {
		mc.thePlayer.setHealth(999999999999999.0F);
	}

	@Override
	public void onEnable() {
		cheatbase.addToTick( this );
		cheatbase.getUtils( ).addChatMessage( getActive( ) );
	}

	@Override
	public void onDisable() {
		cheatbase.removeFromTick( this );
		mc.thePlayer.setHealth(20.0F);
		cheatbase.getUtils( ).addChatMessage( getActive( ) );
		
	}
	
	
    public CheatBase cheatbase;
    public Minecraft mc;
    
}
