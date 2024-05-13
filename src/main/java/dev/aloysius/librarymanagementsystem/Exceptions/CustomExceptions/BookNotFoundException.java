package dev.aloysius.librarymanagementsystem.Exceptions.CustomExceptions;

public class BookNotFoundException extends Exception {
    public BookNotFoundException(String message) {
        super(message);
    }
}
