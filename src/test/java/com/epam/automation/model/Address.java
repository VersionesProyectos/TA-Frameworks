package com.epam.automation.model;

public class Address {
    private String fullAddress;
    private String street;
    private String street2;
    private String city;
    private String state;
    private String zipCode;
    private String country;

    public Address(String fullAddress, String street, String street2, String city, String state, String zipCode, String country) {
        this.fullAddress = fullAddress;
        this.street = street;
        this.street2 = street2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public String getStreet() {
        return street;
    }

    public String getStreet2() {
        return street2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCountry() {
        return country;
    }
}
