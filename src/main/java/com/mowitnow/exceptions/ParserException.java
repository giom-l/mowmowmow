package com.mowitnow.exceptions;

public class ParserException extends Exception {
    public ParserException() {}
    public ParserException(String message){ super(message); }
    public ParserException(Throwable t){ super(t); }

}
