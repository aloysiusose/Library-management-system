package dev.aloysius.librarymanagementsystem.Exceptions.CustomExceptions;

public class BookAlreadyExistsException extends Throwable {
    public BookAlreadyExistsException(String message) {
        super(message);
    }
}
