package dev.aloysius.librarymanagementsystem.Exceptions;

import dev.aloysius.librarymanagementsystem.Exceptions.CustomExceptions.BookAlreadyExistsException;
import dev.aloysius.librarymanagementsystem.Exceptions.CustomExceptions.BookNotFoundException;
import dev.aloysius.librarymanagementsystem.Exceptions.CustomExceptions.PatronAlreadyExistsException;
import dev.aloysius.librarymanagementsystem.Exceptions.CustomExceptions.UserAlreadyExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler
    public ResponseEntity<String> handleException(UserAlreadyExistsException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    @ExceptionHandler
    public ResponseEntity<String> handleException(UsernameNotFoundException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    @ExceptionHandler
    public ResponseEntity<String> handleException(BookNotFoundException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    @ExceptionHandler
    public ResponseEntity<String> handleException(BookAlreadyExistsException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    @ExceptionHandler
    public ResponseEntity<?> handleException(PatronAlreadyExistsException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
