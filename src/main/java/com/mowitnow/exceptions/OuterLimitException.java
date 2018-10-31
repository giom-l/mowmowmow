package com.mowitnow.exceptions;

public class OuterLimitException extends Exception {
    public OuterLimitException(){}
    public OuterLimitException(String message){ super(message); }
    public OuterLimitException(Throwable t){ super(t); }
}
