package com.pedro.accountsservice.dto;

public class AdressDTO {

    private String street;

    private String city;

    private String state;

    private String zip;

    private String country;

    public AdressDTO(String street, String state, String city, String zip, String country) {
        this.street = street;
        this.state = state;
        this.city = city;
        this.zip = zip;
        this.country = country;
    }

    public AdressDTO() {
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
