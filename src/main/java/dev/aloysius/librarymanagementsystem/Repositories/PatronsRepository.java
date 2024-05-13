package dev.aloysius.librarymanagementsystem.Repositories;

import dev.aloysius.librarymanagementsystem.Domains.Patrons;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatronsRepository extends JpaRepository<Patrons, Long> {
    Optional<Patrons> findByEmail(String email);
    Optional<Patrons> findByPatronId(String id);
}
