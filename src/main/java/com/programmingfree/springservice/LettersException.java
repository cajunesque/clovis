package com.programmingfree.springservice;

/**
 * Created by markcbordelon on 12/20/14.
 */
public class LettersException extends RuntimeException {
    public LettersException() {}

    public LettersException(String message) {
        super(message);
    }
}