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

package com.kodehawa.util;

import java.io.IOException;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityFishHook;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.IntegratedServer;
import net.minecraft.src.NetClientHandler;
import net.minecraft.src.Packet15Place;
import net.minecraft.src.Packet28EntityVelocity;

import com.kodehawa.CheatBase;

public class CNetClientHandler extends NetClientHandler
{
	
	public Minecraft mc;
	
	/** RNG. */
	Random rand = new Random( );
	
	public CNetClientHandler( Minecraft par1Minecraft, String par2Str, int par3 ) throws IOException
	{
		super( par1Minecraft, par2Str, par3 );
	}
	
	public CNetClientHandler( Minecraft par1Minecraft, String par2Str, int par3, GuiScreen par4GuiScreen ) throws IOException
	{
		super( par1Minecraft, par2Str, par3, par4GuiScreen );
		
	}
	
	public CNetClientHandler( Minecraft par1Minecraft, IntegratedServer par2IntegratedServer ) throws IOException
	{
		super( par1Minecraft, par2IntegratedServer );
	}
}