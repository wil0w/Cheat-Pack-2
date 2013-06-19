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

import java.util.ArrayList;

import net.minecraft.src.EntityPlayer;

import com.kodehawa.CheatBase;

public class Vars {
	public static boolean tracer, pesp, cesp, xray, doublespeed, rearv;
	public static double speedbonus = 2;
	
	/**
	 * Blocks for xray to show. You can't have List<int>, so you instead do
	 * List<Integer>.
	 */
	public static Integer[ ] xrayBlocks = new Integer[ ] { 8, 9, 10, 11, 14, 15, 16, 21, 56, 73, 129, 88, 112, 153 };
	
	public static ArrayList<String> friends = new ArrayList<String>( );
	public static ArrayList<String> enemies = new ArrayList<String>( );
	
	/**
	 * Who to spy on?
	 */
	public static EntityPlayer spiedOn = CheatBase.instance.minecraft.getMinecraft( ).thePlayer;
}
