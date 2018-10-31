package com.mowitnow.components;

import com.mowitnow.exceptions.OuterLimitException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MowerTest {
    private static final Orientation NORTH = Orientation.north;
    private static final int expectedEasting = 1;
    private static final int expectedNorthing = 3;
    private static final Position position = new Position(expectedEasting, expectedNorthing, NORTH);
    private static final String expectedOrientation = "N";
    private static Mower mower;
    private static Lawn lawn;


    @Before
    public void init(){
        lawn = new Lawn(5,5);
        mower = new Mower(position);
        mower.lawn = lawn;
    }

    @Test
    public void mower_should_display_its_position(){
        String expectedPosition = String.format("%d %d %s", expectedEasting, expectedNorthing, expectedOrientation);
        assertEquals(expectedPosition,mower.toString());
    }

    @Test
    public void mower_should_goForward_if_lawn_limits_are_not_reached() throws OuterLimitException{
        String expectedPosition = String.format("%d %d %s", expectedEasting, expectedNorthing+1, expectedOrientation);
        mower.goForward();
        assertEquals(expectedPosition, mower.toString());
    }

    @Test(expected = OuterLimitException.class)
    public void mower_should_throw_OuterLimitException_is_lawn_limit_is_reached() throws OuterLimitException{
        mower.goForward(); //1 4 N
        mower.goForward(); //1 5 N
        mower.goForward(); //1 6 N : limit reached
    }
}
