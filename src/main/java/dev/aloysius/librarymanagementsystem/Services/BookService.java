package dev.aloysius.librarymanagementsystem.Services;

import dev.aloysius.librarymanagementsystem.Domains.Books;
import dev.aloysius.librarymanagementsystem.Exceptions.CustomExceptions.BookAlreadyExistsException;
import dev.aloysius.librarymanagementsystem.Exceptions.CustomExceptions.BookNotFoundException;
import dev.aloysius.librarymanagementsystem.Repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Books> retrieveAllBooks() {
        return bookRepository.findAll();
    }

    public Books retrieveBookById(String id) throws BookNotFoundException {
        return getBooks(id);
    }

    private Books getBooks(String id) throws BookNotFoundException {

        return bookRepository.findByBookId(id).
                orElseThrow(()-> new BookNotFoundException("Requested book with Id : %s not found!".formatted(id)));
    }

    public void deleteBook(String id) throws BookNotFoundException {
        Books books = getBooks(id);
        bookRepository.delete(books);
    }

    public void updateBook(Books books) throws BookNotFoundException {
        Books books1 = getBooks(books.getBookId());
        books1.setAvailableCopies(books.getAvailableCopies());
        books1.setBookTitle(books.getBookTitle());
        books1.setAuthors(books.getAuthors());
        books1.setIsbn(books.getIsbn());
        books1.setPublicationYear(books.getPublicationYear());

        bookRepository.save(books1);

    }
    public void addNewBook(Books books) throws BookAlreadyExistsException {
        Optional<Books> books1 = bookRepository.findByBookId(books.getBookId());
        if (books1.isPresent()) {
            throw new BookAlreadyExistsException("This book already exists, consider updating it");
        }
        bookRepository.save(books);
    }
}
