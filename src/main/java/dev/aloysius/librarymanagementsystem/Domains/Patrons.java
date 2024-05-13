package dev.aloysius.librarymanagementsystem.Domains;

import jakarta.persistence.*;

@Entity
public class Patrons {
    @Id
    @SequenceGenerator(name = "patron_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patron_sequence")
    private long id;
    private String patronId;
    private String firstName;
    private String lastName;
    private String email;
    private String userPassword;
    @Enumerated(EnumType.STRING)
    private Roles role;
    @OneToOne
    @JoinColumn(name = "contact_information_id")
    private ContactInformation contactInformation;

    public Patrons() {
    }

    public Patrons(Patrons user) {
        this.id = user.id;
        this.patronId = user.patronId;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.email = user.email;
        this.userPassword = user.userPassword;
        this.role = user.role;
        this.contactInformation = user.contactInformation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPatronId() {
        return patronId;
    }

    public void setPatronId(String patronId) {
        this.patronId = patronId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {this.userPassword = userPassword;
    }

    public Roles getRoles() {
        return role;
    }

    public void setRoles(Roles roles) {
        this.role = roles;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }
}
