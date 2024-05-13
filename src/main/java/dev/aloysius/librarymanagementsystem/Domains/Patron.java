package dev.aloysius.librarymanagementsystem.Domains;

public record Patron (String id, String firstName, String lastName, String email, ContactInformation contactInformation){

    public static Patron toPatron(Patrons patrons){
        return new Patron(patrons.getPatronId(), patrons.getFirstName(), patrons.getLastName(),
                patrons.getEmail(), patrons.getContactInformation());
    }
}
