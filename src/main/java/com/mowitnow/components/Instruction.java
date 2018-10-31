package com.mowitnow.components;

public enum Instruction {
    forward, left, right;

    public static Instruction getInstructionFromString(String actual) throws IllegalArgumentException{
        switch(actual){
            case "A":
                return forward;
            case "D":
                return right;
            case "G":
                return left;
            default:
                throw new IllegalArgumentException();
        }
    }
}
