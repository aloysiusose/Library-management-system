package dev.aloysius.librarymanagementsystem.Controllers;

import dev.aloysius.librarymanagementsystem.Domains.Books;
import dev.aloysius.librarymanagementsystem.Domains.Patron;
import dev.aloysius.librarymanagementsystem.Domains.PatronRegistrationRequest;
import dev.aloysius.librarymanagementsystem.Domains.Patrons;
import dev.aloysius.librarymanagementsystem.Exceptions.CustomExceptions.PatronAlreadyExistsException;
import dev.aloysius.librarymanagementsystem.Services.PatronService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patron")
public class PatronController {

    private final PatronService patronService;

    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }

    @GetMapping("/")
    public List<Patron> retrieveAllBooks(){
        return patronService.retrieveAllBooks();
    }
    @GetMapping("/{id}")
    public Patron retrieveBookById(@PathVariable String id){
        return patronService.getById(id);
    }

    @PostMapping("/register")
    public void addNewPatron(@RequestBody PatronRegistrationRequest patron) throws PatronAlreadyExistsException {
        System.out.println(patron);
        patronService.addNewPatron(patron);
    }
    @PutMapping("/")
    public void updatePatronInfo(@RequestBody PatronRegistrationRequest patron) throws PatronAlreadyExistsException {
        patronService.updatePatron(patron);
    }
    @DeleteMapping("/{id}")
    public void removePatron(@PathVariable String id) throws PatronAlreadyExistsException {
        patronService.removePatron(id);
    }
}
