package com.kodehawa.event.events;

import com.kodehawa.event.Event;

/**
 * Used for sending Alerts
 * 
 * @author godshawk
 * 
 */
public class EventAlert extends Event {
    private String message;
    
    public EventAlert( Object source, String message ) {
        super( source );
        this.message = message;
    }
    
    public String getMessage( ) {
        return this.message;
    }
}
