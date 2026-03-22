package com.epam.automation.model;

public class User {
    private String firstName;
    private String lastName;
    private String jobTitle;
    private String date;

    public User(String firstName, String lastName, String jobTitle, String date) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobTitle = jobTitle;
        this.date = date;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getDate() {
        return date;
    }


}
