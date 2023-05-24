package org.example.exceptions;

public class RepositoryException extends RuntimeException{
    public RepositoryException(Throwable cause){
        super (cause);
    }
}
