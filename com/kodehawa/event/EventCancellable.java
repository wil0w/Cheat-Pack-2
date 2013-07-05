package com.kodehawa.event;

public class EventCancellable extends Event {
    
    private boolean cancelled = false;
    
    public EventCancellable( Object source ) {
        super( source );
    }
    
    public boolean isCancelled( ) {
        return cancelled;
    }
    
    public void setCancelled( boolean cancelled ) {
        this.cancelled = cancelled;
    }
}
