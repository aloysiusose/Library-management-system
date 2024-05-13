package dev.aloysius.librarymanagementsystem.Repositories;

import dev.aloysius.librarymanagementsystem.Domains.ContactInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactInformationRepository extends JpaRepository<ContactInformation, Long> {
}
