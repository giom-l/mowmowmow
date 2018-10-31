package com.mowitnow.components;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public class Position {

    @Getter(AccessLevel.PRIVATE)
    @Setter
    int easting;
    @Getter(AccessLevel.PRIVATE)
    @Setter
    int northing;
    @Getter(AccessLevel.PRIVATE)
    @Setter
    Orientation orientation;

    public Position(int easting, int northing, Orientation orientation){
        this.easting = easting;
        this.northing = northing;
        this.orientation = orientation;
    }

    void turnLeft(){ this.orientation = this.orientation.turnLeft(); }

    void turnRight(){ this.orientation = this.orientation.turnRight(); }

    Position goForward(){
        switch(this.orientation){
            case north:
                return new Position(this.easting, this.northing+1, this.orientation);
            case east:
                return new Position(this.easting+1, this.northing, this.orientation);
            case west:
                return new Position(this.easting-1, this.northing, this.orientation);
            case south:
                return new Position(this.easting, this.northing-1, this.orientation);
            default:
                return this;
        }
    }
}
