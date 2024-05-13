package dev.aloysius.librarymanagementsystem.Repositories;

import dev.aloysius.librarymanagementsystem.Domains.Books;
import dev.aloysius.librarymanagementsystem.Domains.BorrowedBooks;
import dev.aloysius.librarymanagementsystem.Domains.Patrons;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BorrowingRepository extends JpaRepository<BorrowedBooks, Long> {
    @Query("SELECT bb FROM BorrowedBooks bb WHERE bb.books = :books AND bb.patrons = :patrons")
    BorrowedBooks findByBooksAndPatrons(Books books, Patrons patrons);
}
