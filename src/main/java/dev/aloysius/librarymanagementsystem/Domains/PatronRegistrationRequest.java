package dev.aloysius.librarymanagementsystem.Domains;

public record PatronRegistrationRequest(String firstName,
                                        String lastName,
                                        String email,
                                        String password,
                                        ContactInformation contactInformation) {
}
