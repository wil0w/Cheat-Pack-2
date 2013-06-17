package com.kodehawa.util;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public final class KeyGen {
	
	private static final SecureRandom random = new SecureRandom( );
	
	public static String nextSessionId( )
	{
		return new BigInteger( 130, random ).toString( 32 );
	}
	
	public static String nextSessionId( int size )
	{
		Random rand = new Random( );
		int myRandomNumber = rand.nextInt( 0x10 ) + ( 0x10 * size * rand.nextInt( 20 ) ); // Generates
		
		String result = Integer.toHexString( myRandomNumber ); 
		return result;
	}
	
}
