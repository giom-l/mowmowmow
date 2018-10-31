package com.mowitnow.components;

import lombok.Getter;

public class Lawn {

    private static final String LAWN_OUTPUT = "The lawn is %d of witdh x %d of height";
    @Getter
    public int width;
    @Getter
    public int height;

    public Lawn(int width, int height){
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString(){
        return String.format(LAWN_OUTPUT, this.width, this.height);
    }


}
