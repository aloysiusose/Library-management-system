package dev.aloysius.librarymanagementsystem.Services;

import dev.aloysius.librarymanagementsystem.Domains.*;
import dev.aloysius.librarymanagementsystem.Exceptions.CustomExceptions.PatronAlreadyExistsException;
import dev.aloysius.librarymanagementsystem.Repositories.ContactInformationRepository;
import dev.aloysius.librarymanagementsystem.Repositories.PatronsRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PatronService {
    private final PatronsRepository patronsRepository;
    private final PasswordEncoder passwordEncoder;
    private final ContactInformationRepository contactInformationRepository;

    public PatronService(PatronsRepository patronsRepository, PasswordEncoder passwordEncoder, ContactInformationRepository contactInformationRepository) {
        this.patronsRepository = patronsRepository;
        this.passwordEncoder = passwordEncoder;
        this.contactInformationRepository = contactInformationRepository;
    }

    public List<Patron> retrieveAllBooks() {

        return patronsRepository.findAll().stream()
                .map(Patron::toPatron).collect(Collectors.toList());
    }

    public Patron getById(String id) {
        Optional<Patrons> byPatronId = patronsRepository.findByPatronId(id);
        if(byPatronId.isEmpty()){
            throw new UsernameNotFoundException("Patron with Id %s not found".formatted(id));
        }
        Patrons patrons = byPatronId.get();
        return Patron.toPatron(patrons);
    }

    public void addNewPatron(PatronRegistrationRequest patron) throws PatronAlreadyExistsException {
        Optional<Patrons> byPatronId = patronsRepository.findByEmail(patron.email());
        if(byPatronId.isPresent()){
            throw new PatronAlreadyExistsException("Patron with email %s already".formatted(patron.email()));
        }
        Patrons patrons = new Patrons();
        patrons.setPatronId("ID_"+patron.firstName().substring(0,4).toUpperCase());
        //patrons.setPatronId(UUID.fromString(patron.email()).toString());
        patrons.setFirstName(patron.firstName());
        patrons.setLastName(patron.lastName());
        patrons.setEmail(patron.email());
        patrons.setUserPassword(passwordEncoder.encode(patron.password()));
        patrons.setRoles(Roles.PATRON);
        patrons.setContactInformation(patron.contactInformation());

        patronsRepository.save(patrons);
    }

    public void updatePatron(PatronRegistrationRequest patron) throws PatronAlreadyExistsException {
        Optional<Patrons> byPatronId = patronsRepository.findByEmail(patron.email());
        if(byPatronId.isEmpty()){
            throw new PatronAlreadyExistsException("Patron with Id %s not found".formatted(patron.email()));
        }
        Patrons patrons = byPatronId.get();
        //patrons.setPatronId(UUID.fromString(patron.email()).toString());
        patrons.setFirstName(patron.firstName());
        patrons.setLastName(patron.lastName());
       // patrons.setEmail(patron.email());
        patrons.setUserPassword(passwordEncoder.encode(patron.password()));
        patrons.setRoles(Roles.PATRON);
        patrons.setContactInformation(patron.contactInformation());

        patronsRepository.save(patrons);
    }
    public void updatePatronContactInfo(ContactInformation contactInformation) throws PatronAlreadyExistsException {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Patrons> byPatronId = patronsRepository.findByEmail(email);
        if(byPatronId.isEmpty()){
            throw new PatronAlreadyExistsException("Patron with Id %s not found".formatted(email));
        }
        Patrons patrons = byPatronId.get();
        contactInformationRepository.save(contactInformation);
        patrons.setContactInformation(contactInformation);
        patronsRepository.save(patrons);
    }

    public void removePatron(String id) throws PatronAlreadyExistsException {
        if (patronsRepository.findByPatronId(id).isEmpty()) {
            throw new PatronAlreadyExistsException("Patron with Id %s not found".formatted(id));
        }
        patronsRepository.delete(patronsRepository.findByPatronId(id).get());
    }
}
