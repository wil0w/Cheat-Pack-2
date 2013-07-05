package com.kodehawa.event.events;

import com.kodehawa.event.Event;

public class EventChatSent extends Event {
    private String chat;
    
    public EventChatSent( Object source, String chat ) {
        super( source );
        this.chat = chat;
    }
    
    public String getChat( ) {
        return this.chat;
    }
}
