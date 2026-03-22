package com.epam.automation.test;

import com.epam.automation.base.BaseTest;
import com.epam.automation.model.Address;
import com.epam.automation.pages.AutocompletePage;
import com.epam.automation.utils.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AutocompleteTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(AutocompleteTest.class);
    private AutocompletePage autocompletePage;

    @BeforeMethod
    public void setUpPage(){
        autocompletePage = new AutocompletePage(driver);
    }

    @Test(description = "Scenario 3: Ten‑step Autocomplete procedure")
    public void autocompleteTest(){

        logger.info("Ejecutando Test de Autocompletado de Dirección");

        Address testAddress = new Address(
                "1600 Amphitheatre Parkway, Mountain View, CA",
                "1600",
                "Amphitheatre Parkway",
                "Mountain View",
                "California",
                "94043",
                "United States"
        );

        autocompletePage.clickAutocompleteLink();

        // Punto 3 & 8: Acción de alto nivel con logging interno
        autocompletePage.fillAddressDetails(testAddress);

        // Validaciones usando el objeto para comparar
        Assert.assertEquals(autocompletePage.getCityValue(), testAddress.getCity());
        Assert.assertEquals(autocompletePage.getStateValue(), testAddress.getState());
        Assert.assertEquals(autocompletePage.getCountryValue(), testAddress.getCountry());

        logger.info("Validación de dirección exitosa.");
    }
}
