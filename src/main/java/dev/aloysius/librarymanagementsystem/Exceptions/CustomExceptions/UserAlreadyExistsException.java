package dev.aloysius.librarymanagementsystem.Exceptions.CustomExceptions;

public class UserAlreadyExistsException extends Exception{
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
