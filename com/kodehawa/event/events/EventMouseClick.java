package com.kodehawa.event.events;

import com.kodehawa.event.Event;

/**
 * When the mouse is pressed, do shit. Uses Strings; ie "attack" and "place"
 * 
 * @author godshawk
 * 
 */
public class EventMouseClick extends Event {
    private String button;
    
    public EventMouseClick( Object source, String button ) {
        super( source );
        this.button = button;
        // TODO Auto-generated constructor stub
    }
    
    public String getButton( ) {
        return this.button;
    }
}
