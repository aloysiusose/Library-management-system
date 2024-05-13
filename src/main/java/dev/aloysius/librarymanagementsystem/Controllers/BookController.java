package dev.aloysius.librarymanagementsystem.Controllers;

import dev.aloysius.librarymanagementsystem.Domains.Books;
import dev.aloysius.librarymanagementsystem.Exceptions.CustomExceptions.BookAlreadyExistsException;
import dev.aloysius.librarymanagementsystem.Exceptions.CustomExceptions.BookNotFoundException;
import dev.aloysius.librarymanagementsystem.Services.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookService  bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public List<Books> retrieveAllBooks(){
        return bookService.retrieveAllBooks();
    }
    @GetMapping("/{id}")
    public Books retrieveBookById(@PathVariable String id) throws BookNotFoundException {
        return bookService.retrieveBookById(id);
    }

    @PostMapping("/")
    public void addNewBook(@RequestBody Books books) throws BookAlreadyExistsException {
        bookService.addNewBook(books);
    }
    @PutMapping("/")
    public void updateBook(@RequestBody Books books) throws BookNotFoundException {
        bookService.updateBook(books);

    }
    @DeleteMapping("/")
    public void deleteBook(@PathVariable String id) throws BookNotFoundException {
        bookService.deleteBook(id);
    }


}
