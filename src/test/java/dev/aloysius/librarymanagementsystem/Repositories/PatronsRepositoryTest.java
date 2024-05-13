package dev.aloysius.librarymanagementsystem.Repositories;

import dev.aloysius.librarymanagementsystem.Domains.ContactInformation;
import dev.aloysius.librarymanagementsystem.Domains.Patrons;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class PatronsRepositoryTest {
    @Autowired
    private PatronsRepository underTest;
    @Autowired
    private ContactInformationRepository repository;

    @Test
    void findByEmail() {

        ContactInformation c1 = new ContactInformation();
        c1.setId(1L);
        repository.save(c1);
        //given
        Patrons p1 = new Patrons();
        String email = "john.abc";
        p1.setEmail(email);

        underTest.save(p1);
        // when
        Optional<Patrons> byEmail = underTest.findByEmail(email);
        // then
        assertEquals(p1, byEmail.get());
        }

    @Test
    void findByPatronId() {
    }
}