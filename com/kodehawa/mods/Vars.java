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
