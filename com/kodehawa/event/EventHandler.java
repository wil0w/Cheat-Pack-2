package com.kodehawa.event;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.kodehawa.event.events.EventAlert;
import com.kodehawa.event.events.EventBlockClick;
import com.kodehawa.event.events.EventBlockRender;
import com.kodehawa.event.events.EventChatRecieved;
import com.kodehawa.event.events.EventChatSent;
import com.kodehawa.event.events.EventKey;
import com.kodehawa.event.events.EventMouseClick;
import com.kodehawa.event.events.EventMouseDrag;
import com.kodehawa.event.events.EventRender3D;
import com.kodehawa.event.events.EventSwing;
import com.kodehawa.event.events.EventTick;

public class EventHandler {
    
    private HashMap< Class< ? extends Event >, CopyOnWriteArrayList< Listener >> eventMap;
    
    public EventHandler( ) {
        eventMap = new HashMap< Class< ? extends Event >, CopyOnWriteArrayList< Listener >>( );
        eventMap.put( EventTick.class, new CopyOnWriteArrayList< Listener >( ) );
        eventMap.put( EventAlert.class, new CopyOnWriteArrayList< Listener >( ) );
        eventMap.put( EventKey.class, new CopyOnWriteArrayList< Listener >( ) );
        eventMap.put( EventRender3D.class, new CopyOnWriteArrayList< Listener >( ) );
        eventMap.put( EventBlockClick.class, new CopyOnWriteArrayList< Listener >( ) );
        eventMap.put( EventBlockRender.class, new CopyOnWriteArrayList< Listener >( ) );
        eventMap.put( EventMouseClick.class, new CopyOnWriteArrayList< Listener >( ) );
        eventMap.put( EventSwing.class, new CopyOnWriteArrayList< Listener >( ) );
        eventMap.put( EventChatSent.class, new CopyOnWriteArrayList< Listener >( ) );
        eventMap.put( EventChatRecieved.class, new CopyOnWriteArrayList< Listener >( ) );
        eventMap.put( EventMouseDrag.class, new CopyOnWriteArrayList< Listener >( ) );
    }
    
    public void registerListener( Class< ? extends Event > c, Listener m ) {
        if( eventMap.containsKey( c ) ) {
            List l = eventMap.get( c );
            if( !l.contains( m ) ) {
                l.add( m );
            }
        }
    }
    
    public void unRegisterListener( Class< ? extends Event > c, Listener m ) {
        if( eventMap.containsKey( c ) ) {
            List l = eventMap.get( c );
            if( l.contains( m ) ) {
                l.remove( m );
            }
        }
    }
    
    public Event call( Event e ) {
        if( eventMap.containsKey( e.getClass( ) ) ) {
            List< Listener > l = eventMap.get( e.getClass( ) );
            for( Listener m : l ) {
                try {
                    m.onEvent( e );
                } catch( Exception e1 ) {
                    e1.printStackTrace( );
                }
            }
        } else
            System.out.println( "Event not supported: " + e.toString( ) + ". Add event into eventMap." );
        return e;
    }
}
