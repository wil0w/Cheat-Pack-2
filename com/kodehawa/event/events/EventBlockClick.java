package com.kodehawa.event.events;

import paulscode.sound.Vector3D;

import com.kodehawa.event.Event;

/**
 * Used for sending AutoTool 'n' stuff
 * 
 * @author godshawk
 * 
 */
public class EventBlockClick extends Event {
    private final Vector3D coords;
    
    public EventBlockClick( Object source, int x, int y, int z ) {
        super( source );
        coords = new Vector3D( x, y, z );
    }
    
    public Vector3D getCoords( ) {
        return this.coords;
    }
}
