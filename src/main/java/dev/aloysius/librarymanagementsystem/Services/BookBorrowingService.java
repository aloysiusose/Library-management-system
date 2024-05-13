package dev.aloysius.librarymanagementsystem.Services;

import dev.aloysius.librarymanagementsystem.Domains.Books;
import dev.aloysius.librarymanagementsystem.Domains.BorrowedBooks;
import dev.aloysius.librarymanagementsystem.Domains.Patrons;
import dev.aloysius.librarymanagementsystem.Exceptions.CustomExceptions.BookNotFoundException;
import dev.aloysius.librarymanagementsystem.Repositories.BookRepository;
import dev.aloysius.librarymanagementsystem.Repositories.BorrowingRepository;
import dev.aloysius.librarymanagementsystem.Repositories.PatronsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookBorrowingService {
    private final BookRepository bookRepository;
    private final BorrowingRepository borrowingRepository;
    private final PatronsRepository patronsRepository;
    private static Logger logger = LoggerFactory.getLogger(BookBorrowingService.class);
    public BookBorrowingService(BookRepository bookRepository, BorrowingRepository borrowingRepository, PatronsRepository patronsRepository) {
        this.bookRepository = bookRepository;
        this.borrowingRepository = borrowingRepository;
        this.patronsRepository = patronsRepository;
    }

    public void borrowBook(String bookId) throws BookNotFoundException {
        Optional<Books> books = bookRepository.findByBookId(bookId);
        if(books.isEmpty()){
            throw new BookNotFoundException("");
        }
        if (!books.get().isAvailable()) {
            throw  new IllegalStateException("Books out of stockT");
        }
        Books books1 = books.get();
        books1.setAvailableCopies(decrementBooksQuantity(books1));
        bookRepository.save(books1);
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Patrons patrons = patronsRepository.findByEmail(name).get();
        BorrowedBooks borrowedBooks = new BorrowedBooks();
        borrowedBooks.setBooks(books1);
        borrowedBooks.setBookName(books1.getBookTitle());
        borrowedBooks.setDateOfBorrow(LocalDateTime.now());
        borrowedBooks.setPatrons(patrons);
        borrowingRepository.save(borrowedBooks);
        logger.info("%s has been borrowed by %s at %s".formatted(books1.getBookTitle(), name, LocalDateTime.now()));

    }

    private synchronized static int decrementBooksQuantity(Books books1) {
        return books1.getAvailableCopies() - 1;
    }

    public void returnBooks(String bookId) throws BookNotFoundException {
        Optional<Books> books = bookRepository.findByBookId(bookId);
        if(books.isEmpty()){
            throw new BookNotFoundException("");
        }
 
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Patrons patrons = patronsRepository.findByEmail(name).get();
        Books books1 = books.get();
        BorrowedBooks borrowedBooks = borrowingRepository.findByBooksAndPatrons(books1, patrons);
        if(!Objects.equals(borrowedBooks.getDateOfReturn(), null)){
           throw new BookNotFoundException("This book has been returned");
        }
        borrowedBooks.setDateOfReturn(LocalDateTime.now());
        borrowingRepository.save(borrowedBooks);
        books1.setAvailableCopies(incrementBookQuantity(books1));
        bookRepository.save(books1);
        logger.info("%s have been returned by %s at %s".formatted(books1.getBookTitle(), name, LocalDateTime.now()));
    }

    private synchronized static int incrementBookQuantity(Books books1) {
        return books1.getAvailableCopies() + 1;
    }
}
