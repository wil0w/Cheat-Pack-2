package com.kodehawa.event.events;

import net.minecraft.src.Block;
import paulscode.sound.Vector3D;

import com.kodehawa.CheatBase;
import com.kodehawa.event.EventCancellable;

public class EventBlockRender extends EventCancellable {
    private EventType type;
    private Block block;
    private Vector3D blockCoordinates;
    
    public enum EventType {
        RENDER_XRAY;
    }
    
    public EventBlockRender( Object source, EventType type, int x, int y, int z ) {
        super( source );
        this.type = type;
        blockCoordinates = new Vector3D( x, y, z );
        
        this.block = Block.blocksList[ CheatBase.getInstance( ).getWrapper.getMinecraft( ).theWorld
                .getBlockId( x, y, z ) ];
    }
    
    public EventBlockRender( Object source, EventType type, int id ) {
        super( source );
        this.type = type;
        blockCoordinates = null;
        this.block = Block.blocksList[ id ];
    }
    
    public EventType getType( ) {
        return type;
    }
    
    public Block getBlock( ) {
        return block;
    }
    
    public Vector3D getBlockCoordinates( ) {
        return blockCoordinates;
    }
    
}
