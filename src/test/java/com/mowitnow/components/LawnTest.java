package com.mowitnow.components;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LawnTest {

    @Test
    public void lawn_initialized_with_width5_should_return_getWidth_5(){
        int expectedWitdh = 5;
        int expectedHeight = 5;
        Lawn l = new Lawn(expectedWitdh,expectedHeight);
        int actualWidth = l.width;
        assertEquals(expectedWitdh,actualWidth);
    }

    @Test
    public void lawn_initialized_with_height8_should_return_getHeight_5(){
        int expectedWitdh = 5;
        int expectedHeight = 8;
        Lawn l = new Lawn(expectedWitdh,expectedHeight);
        int actualHeight = l.height;
        assertEquals(expectedHeight,actualHeight);
    }
}
