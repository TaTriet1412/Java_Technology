package org.example.MyException.InvalidOperationException.java;

public class InvalidOperationException extends Throwable {
    @Override
    public String getMessage() {
        return "Error invalid operation!";
    }
}
