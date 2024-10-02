package com.pedro.accountsservice.model;

import com.pedro.accountsservice.dto.AdressDTO;
import jakarta.persistence.*;
import org.hibernate.envers.Audited;

@Audited
@Entity
@Table
public class Adress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String street;

    private String city;

    private String state;

    private String zip;

    private String country;

    public Adress(AdressDTO adrsDto) {
        this.street = adrsDto.getStreet();
        this.city = adrsDto.getCity();
        this.zip = adrsDto.getZip();
        this.state = adrsDto.getState();
        this.country = adrsDto.getCountry();
    }

    public Adress() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
