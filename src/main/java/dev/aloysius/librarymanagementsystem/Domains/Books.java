package dev.aloysius.librarymanagementsystem.Domains;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
public class Books {
    @Id
    @SequenceGenerator(name = "book_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_sequence")
    private long id;
    private String bookId;
    private String isbn;
    private String bookTitle;
    private String authors;
    private LocalDate publicationDate;
    private int availableCopies;

    public boolean isAvailable(){
        return this.availableCopies >0;
    }

    public Books() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public LocalDate getPublicationYear() {
        return publicationDate;
    }

    public void setPublicationYear(LocalDate publicationYear) {
        this.publicationDate = publicationYear;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }
}
