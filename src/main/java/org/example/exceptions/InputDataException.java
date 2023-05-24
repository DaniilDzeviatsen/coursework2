package org.example.exceptions;

public class InputDataException extends RuntimeException {
    public InputDataException() {
        super("Wrong DATA input");
    }
}
