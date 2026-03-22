package com.epam.automation.model;

public class PageState {
    private String expectedTitle;

    public PageState(String expectedTitle) {
        this.expectedTitle = expectedTitle;
    }

    public String getExpectedTitle() {
        return expectedTitle;
    }
}
