package com.epam.automation.model;

public class Address {
    private String fullAddress;
    private String street;
    private String street2;
    private String city;
    private String state;
    private String zipCode;
    private String country;


    private Address(AddressBuilder builder) {

        this.fullAddress = builder.fullAddress;
        this.street = builder.street;
        this.street2 = builder.street2;
        this.city = builder.city;
        this.state = builder.state;
        this.zipCode = builder.zipCode;
        this.country = builder.country;
    }

    public static class AddressBuilder {

        private String fullAddress;
        private String street;
        private String street2;
        private String city;
        private String state;
        private String zipCode;
        private String country;

        public AddressBuilder  fullAddress(String fullAddress) {
            this.fullAddress = fullAddress;
            return this;
        }

        public AddressBuilder street(String street) {
            this.street = street;
            return this;
        }

        public AddressBuilder street2(String street2) {
            this.street2 = street2;
            return this;
        }
        public AddressBuilder city(String city) {
            this.city =city;
            return this;
        }
        public AddressBuilder state(String state) {
            this.state = state;
            return this;
        }
        public AddressBuilder zipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public AddressBuilder country(String country) {
            this.country = country;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }

    public static AddressBuilder builder() {
        return new AddressBuilder();
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
