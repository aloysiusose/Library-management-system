package dev.aloysius.librarymanagementsystem.Controllers;

import dev.aloysius.librarymanagementsystem.Exceptions.CustomExceptions.BookNotFoundException;
import dev.aloysius.librarymanagementsystem.Services.BookBorrowingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/borrow")
public class BorrowingController {

    private final BookBorrowingService bookBorrowingService;

    public BorrowingController(BookBorrowingService bookBorrowingService) {
        this.bookBorrowingService = bookBorrowingService;
    }
    @GetMapping("/book/{bookId}")
    public void borrowABook(@PathVariable String bookId) throws BookNotFoundException {
        bookBorrowingService.borrowBook(bookId);
    }
    @GetMapping("/return/{bookId}")
    public void returnABook(@PathVariable String bookId) throws BookNotFoundException {
        bookBorrowingService.returnBooks(bookId);
    }
}
