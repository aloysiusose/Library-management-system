package dev.aloysius.librarymanagementsystem.Repositories;

import dev.aloysius.librarymanagementsystem.Domains.Books;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Books, Long> {

    Optional<Books> findByBookId(String id);
}
