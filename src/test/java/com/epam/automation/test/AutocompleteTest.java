package com.epam.automation.test;

import com.epam.automation.base.BaseTest;
import com.epam.automation.pages.AutocompletePage;
import com.epam.automation.utils.Constants;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AutocompleteTest extends BaseTest {

    private AutocompletePage autocompletePage;

    @BeforeMethod
    public void setUpPage(){
        autocompletePage = new AutocompletePage(driver);
    }

    @Test(description = "Scenario 3: Ten‑step Autocomplete procedure")
    public void autocompleteTest(){

        autocompletePage.clickAutocompleteLink();
        autocompletePage.enterAddress(Constants.ADDRESS);
        autocompletePage.enterStreetAddress(Constants.STREET_ADDRESS);
        autocompletePage.enterStreetAddressDos(Constants.STREET_ADDRESS_DOS);
        autocompletePage.enterCity(Constants.CITY);
        autocompletePage.enterState(Constants.STATE);
        autocompletePage.enterPostalCode(Constants.POSTAL_CODE);
        autocompletePage.enterCountry(Constants.COUNTRY);

        Assert.assertEquals(autocompletePage.getCityValue(), Constants.GET_CITY_VALUE);
        Assert.assertEquals(autocompletePage.getStateValue(), Constants.GET_STATE_VALUE );
        Assert.assertEquals(autocompletePage.getCountryValue(), Constants.GET_COUNTRY_VALUE );
    }
}
