package com.kodehawa.gui.api.testing;

import java.util.ArrayList;

public class AlertHandler {
	public ArrayList<Alert> alerts;
	
	public AlertHandler( ) {
		alerts = new ArrayList<Alert>( );
	}
	
	public void addAlert( Alert e ) {
		alerts.add( e );
	}
	
	public void removeAlert( ) {
		removeAlert( alerts.get( 0 ) );
	}
	
	public void removeAlert( Alert e ) {
		alerts.remove( e );
	}
}
