package com.programmingfree.springservice;

/**
 * Created by markcbordelon on 12/20/14.
 */
public class SyllableException extends RuntimeException {
    public SyllableException() {}

    public SyllableException(String message) {
        super(message);
    }
}