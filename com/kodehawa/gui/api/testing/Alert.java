package com.kodehawa.gui.api.testing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import com.kodehawa.gui.api.render.ModGuiUtils;

public class Alert implements ActionListener {
	Timer tm = new Timer( 5, this ); // Timer(DelayInMilliseconds,
									 // ActionListener);
	int y = 0, vely = 1;
	int height = ModGuiUtils.getHeight( );
	
	public void tick( ) {
		tm.start( );
		ModGuiUtils.drawRect( 0, y, 20, 20, 0x77770077 );
	}
	
	@Override
	public void actionPerformed( ActionEvent e ) {
		if ( ( y < 0 ) || ( y > ( height - 20 ) ) )
		{
			vely = -vely;
		}
		y = y + vely;
		
	}
}
