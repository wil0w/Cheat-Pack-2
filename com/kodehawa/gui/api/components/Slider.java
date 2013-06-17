package com.kodehawa.gui.api.components;

import java.text.DecimalFormat;

import com.kodehawa.CheatBase;
import com.kodehawa.gui.api.font.TTFRenderer;
import com.kodehawa.gui.api.render.ModGuiUtils;

public class Slider extends Item {
	private final Value sliderValue;
	private final int xPos;
	private final int yPos;
	private final float maxValue;
	private final float minValue;
	
	private boolean shouldRound;
	
	public boolean dragging;
	public float dragX, lastDragX;
	
	private final int drawSliderWidth;
	private final int sliderWidth;
	
	public void dragSlider( int x ) {
		dragX = x - lastDragX;
	}
	
	public Slider( Value value, int x, int y ) {
		this.sliderValue = value;
		this.xPos = x;
		this.yPos = y;
		this.maxValue = 10.0F;
		this.minValue = 0.0F;
		this.drawSliderWidth = 85;
		this.sliderWidth = this.drawSliderWidth - 5;
	}
	
	public Slider( Value value, int x, int y, float maxValue ) {
		this.sliderValue = value;
		this.xPos = x;
		this.yPos = y;
		this.maxValue = maxValue;
		this.minValue = 0.0F;
		this.drawSliderWidth = 85;
		this.sliderWidth = this.drawSliderWidth - 5;
	}
	
	public Slider( Value value, int x, int y, float minValue, float maxValue, boolean shouldRound ) {
		this.sliderValue = value;
		this.xPos = x;
		this.yPos = y;
		this.maxValue = maxValue;
		this.minValue = minValue;
		this.drawSliderWidth = 85;
		this.shouldRound = shouldRound;
		this.sliderWidth = this.drawSliderWidth - 5;
	}
	
	public void draw( int x ) {
		if ( dragging ) {
			dragSlider( x );
		}
		if ( dragX < 0 ) {
			dragX = 0;
		}
		if ( dragX > this.sliderWidth ) {
			dragX = this.sliderWidth;
		}
		
		DecimalFormat format = new DecimalFormat( shouldRound ? "0" : "0.0" );
		
		TTFRenderer.drawTTFStringWithShadow( CheatBase.instance.guiFont, sliderValue.getName( ) + ": " + format.format( sliderValue.getValue( ) ), xPos + parent.x, ( yPos - 3 ) + parent.y, 0xFFFFFF );
		ModGuiUtils.drawHLine( xPos + parent.x, xPos + this.drawSliderWidth + parent.x, yPos + 12 + parent.y, 0xFFAAAAAA );
		ModGuiUtils.drawHLine( xPos + parent.x, xPos + dragX + parent.x, yPos + 12 + parent.y, 0xFFDDDDDD );
		ModGuiUtils.drawGBRect( xPos + parent.x + (int) dragX, yPos + 9 + parent.y, xPos + 6 + parent.x + (int) dragX, yPos + 15.5F + parent.y, 0.5F, 0xFF555555, 0xFF777777, 0xFF555555 );
		
		float fraction = this.sliderWidth / ( maxValue - minValue );
		
		sliderValue.setValue( sliderValue.getName( ).equals( "Step" ) ? ( ( dragX / fraction ) + minValue ) + 0.1F : shouldRound ? (int) ( dragX / fraction ) + minValue : ( dragX / fraction ) + minValue );
	}
	
	public float getValue( ) {
		return Float.parseFloat( ( new DecimalFormat( "0.0" ) ).format( this.sliderValue.getValue( ) ) );
	}
	
	public void setValue( float value ) {
		value -= minValue;
		
		float fraction = 80 / ( maxValue - minValue );
		sliderValue.setValue( value );
		dragX = fraction * value;
	}
	
	public void mouseClicked( int x, int y, int button ) {
		if ( button == 0 ) {
			if ( ( x >= ( xPos + parent.x + dragX ) ) && ( y >= ( yPos + 9 + parent.y ) ) && ( x <= ( xPos + 6 + parent.x + dragX ) ) && ( y <= ( yPos + 15.5F + parent.y ) ) ) {
				lastDragX = x - dragX;
				dragging = true;
			}
		}
	}
	
	public void mouseMovedOrUp( int x, int y, int b ) {
		if ( b == 0 ) {
			dragging = false;
		}
	}
	
	@Override
	public void draw( ) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void drag( int x, int y ) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onClick( int x, int y ) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean mouseOver( int x, int y ) {
		// TODO Auto-generated method stub
		return false;
	}
}
