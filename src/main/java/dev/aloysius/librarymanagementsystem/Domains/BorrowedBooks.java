package dev.aloysius.librarymanagementsystem.Domains;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
public class BorrowedBooks {
    @Id
    @SequenceGenerator(name = "borrowed_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "borrowed_sequence")
    private long id;
    @OneToOne
    @JoinColumn(name= "book_id")
    private Books books;
    private String bookName;
    private LocalDateTime dateOfBorrow;
    private LocalDateTime dateOfReturn;
    @OneToOne
    @JoinColumn(name = "patron_id")
    private Patrons patrons;

    public BorrowedBooks() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Books getBooks() {
        return books;
    }

    public void setBooks(Books books) {
        this.books = books;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public LocalDateTime getDateOfBorrow() {
        return dateOfBorrow;
    }

    public void setDateOfBorrow(LocalDateTime dateOfBorrow) {
        this.dateOfBorrow = dateOfBorrow;
    }

    public LocalDateTime getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(LocalDateTime dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    public Patrons getPatrons() {
        return patrons;
    }

    public void setPatrons(Patrons patrons) {
        this.patrons = patrons;
    }
}
