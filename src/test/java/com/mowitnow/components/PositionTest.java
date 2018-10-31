package com.mowitnow.components;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PositionTest {

    private static final Orientation WEST = Orientation.west;
    private static final Orientation EAST = Orientation.east;
    private static final Orientation NORTH = Orientation.north;
    private static final Orientation SOUTH = Orientation.south;
    private static int easting;
    private static int northing;
    private static Orientation o;


    @Before
    public void init(){
        easting = 3;
        northing = 2;
        o = Orientation.north;
    }

    @Test
    public void getEasting_should_return_easting_value(){
        Position p = new Position(easting, northing, o);
        int actualEasting = p.easting;
        assertEquals(easting,actualEasting);
    }

    @Test
    public void getNorthing_should_return_northing_value(){
        Position p = new Position(easting, northing, o);
        int actualNorthing= p.northing;
        assertEquals(northing,actualNorthing);
    }

    @Test
    public void turnLeft_from_north_should_give_west(){
        Position p = new Position(easting, northing, o);
        p.turnLeft();
        Orientation actualOrientation = p.orientation;
        assertEquals(WEST,actualOrientation);
    }

    @Test
    public void turnLeft_from_east_should_give_north(){
        Position p = new Position(easting, northing, o);
        p.orientation = EAST;
        p.turnLeft();
        Orientation actualOrientation = p.orientation;
        assertEquals(NORTH,actualOrientation);
    }

    @Test
    public void turnLeft_from_south_should_give_east(){
        Position p = new Position(easting, northing, o);
        p.orientation = SOUTH;
        p.turnLeft();
        Orientation actualOrientation = p.orientation;
        assertEquals(EAST,actualOrientation);
    }

    @Test
    public void turnLeft_from_west_should_give_south(){
        Position p = new Position(easting, northing, o);
        p.orientation = WEST;
        p.turnLeft();
        Orientation actualOrientation = p.orientation;
        assertEquals(SOUTH,actualOrientation);
    }

    @Test
    public void turnRight_from_north_should_give_east(){
        Position p = new Position(easting, northing, o);
        p.turnRight();
        Orientation actualOrientation = p.orientation;
        assertEquals(EAST,actualOrientation);
    }

    @Test
    public void turnRight_from_east_should_give_south(){
        Position p = new Position(easting, northing, o);
        p.orientation = EAST;
        p.turnRight();
        Orientation actualOrientation = p.orientation;
        assertEquals(SOUTH,actualOrientation);
    }

    @Test
    public void turnRight_from_south_should_give_west(){
        Position p = new Position(easting, northing, o);
        p.orientation = SOUTH;
        p.turnRight();
        Orientation actualOrientation = p.orientation;
        assertEquals(WEST,actualOrientation);
    }

    @Test
    public void turnRight_from_west_should_give_north(){
        Position p = new Position(easting, northing, o);
        p.orientation = WEST;
        p.turnRight();
        Orientation actualOrientation = p.orientation;
        assertEquals(NORTH,actualOrientation);
    }

    @Test
    public void goForward_with_north_orientation_should_increase_northing(){
        Position p = new Position(easting, northing, o);
        Position newP = p.goForward();
        int expectedNorthing = p.northing + 1;
        assertEquals(expectedNorthing, newP.northing);
    }

    @Test
    public void goForward_with_south_orientation_should_decrease_northing(){
        Position p = new Position(easting, northing, o);
        p.orientation = SOUTH;
        Position newP = p.goForward();
        int expectedNorthing = p.northing - 1;
        assertEquals(expectedNorthing, newP.northing);
    }

    @Test
    public void goForward_with_east_orientation_should_decrease_easting(){
        Position p = new Position(easting, northing, o);
        p.orientation = EAST;
        Position newP = p.goForward();
        int expectedEasting= p.easting + 1;
        assertEquals(expectedEasting, newP.easting);
    }

    @Test
    public void goForward_with_west_orientation_should_increase_easting(){
        Position p = new Position(easting, northing, o);
        p.orientation = WEST;
        Position newP = p.goForward();
        int expectedEasting= p.easting - 1;
        assertEquals(expectedEasting, newP.easting);
    }
}
