package dev.aloysius.librarymanagementsystem.Domains;

import jakarta.persistence.*;

@Entity
public class ContactInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contact_sequence")
    @SequenceGenerator(name = "contact_sequence", allocationSize = 1)
    private long id;
    private String telephone;
    private String streetName;
    private String province;
    private String Country;
    public ContactInformation(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }
}
