package com.mowitnow.components;

import com.mowitnow.exceptions.ParserException;

public enum Orientation {
    north, east, west, south;

    public Orientation turnRight(){
        switch(this){
            case north:
                return east;
            case east:
                return south;
            case west:
                return north;
            case south:
                return west;
            default:
                return null;
        }
    }

    public Orientation turnLeft(){
        switch(this){
            case north:
                return west;
            case east:
                return north;
            case west:
                return south;
            case south:
                return east;
            default:
                return null;
        }
    }

    @Override
    public String toString(){
        switch(this){
            case north:
                return String.format("%s", "N");
            case east:
                return String.format("%s", "E");
            case west:
                return String.format("%s", "W");
            case south:
                return String.format("%s", "S");
            default:
                return null;
        }
    }

    public static Orientation getOrientationFromString(String orient) throws ParserException{
        switch(orient){
            case "N":
                return north;
            case "E":
                return east;
            case "W":
                return west;
            case "S":
                return south;
            default:
                throw new ParserException();
        }
    }
}
