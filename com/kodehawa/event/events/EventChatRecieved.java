package com.kodehawa.event.events;

import com.kodehawa.event.Event;

public class EventChatRecieved extends Event {
    private String chat;
    
    public EventChatRecieved( Object source, String chat ) {
        super( source );
        this.chat = chat;
    }
    
    public String getChat( ) {
        return this.chat;
    }
}
