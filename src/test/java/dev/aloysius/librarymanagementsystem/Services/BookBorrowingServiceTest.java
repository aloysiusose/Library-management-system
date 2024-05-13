package dev.aloysius.librarymanagementsystem.Services;

import dev.aloysius.librarymanagementsystem.Domains.Books;
import dev.aloysius.librarymanagementsystem.Domains.Patrons;
import dev.aloysius.librarymanagementsystem.Domains.Roles;
import dev.aloysius.librarymanagementsystem.Exceptions.CustomExceptions.BookNotFoundException;
import dev.aloysius.librarymanagementsystem.Repositories.BookRepository;
import dev.aloysius.librarymanagementsystem.Repositories.BorrowingRepository;
import dev.aloysius.librarymanagementsystem.Repositories.PatronsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class BookBorrowingServiceTest {
    @Autowired
    private BookBorrowingService underTest;
    @Mock
    private  BookRepository bookRepository;
    @Mock
    private  BorrowingRepository borrowingRepository;
    @Mock
    private PatronsRepository patronsRepository;

    @Test
    @Description("This method, takes an id, find the book," +
            " decrease the available books, and creates a record for a user in borrowdedbook repository")
    void borrowBook() throws BookNotFoundException {
        //create patron, create books, create borrowed books

        Patrons patrons = new Patrons();
        patrons.setId(1L);
        patrons.setEmail("john.abc");
        patrons.setRoles(Roles.PATRON);
        patrons.setFirstName("John");
        patrons.setLastName("Leo");

        Books books = new Books();
        books.setBookTitle("");
        books.setAvailableCopies(10);
        books.setIsbn("12324");
        books.setAuthors("");

      // given(underTest.borrowBook(books.getBookId())).willReturn()
    }

    @Test
    @Description("This method, takes an id, find the book," +
            " increase the available books, and updates the book record for a user in borrowedbook repository")
    void returnBooks() {
    }
}