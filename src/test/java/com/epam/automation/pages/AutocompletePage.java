package com.epam.automation.pages;

import com.epam.automation.base.BasePage;
import com.epam.automation.model.Address;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
public class AutocompletePage extends BasePage {

    private static final Logger logger = LogManager.getLogger(AutocompletePage.class);

    @FindBy(css = ".btn.btn-lg")
    private WebElement autocompleteLink;

    @FindBy(id = "autocomplete")
    private WebElement addressField; // Corregido: addressField

    @FindBy(id = "street_number")
    private WebElement streetAddressField;

    @FindBy(id = "route" )
    private WebElement streetAddressDosField;

    @FindBy(id = "locality")
    private WebElement cityField;

    @FindBy(id = "administrative_area_level_1")
    private WebElement stateField;

    @FindBy(id = "postal_code")
    private WebElement postalCodeField;

    @FindBy(id = "country")
    private WebElement countryField;

    public AutocompletePage(WebDriver driver) {
        super(driver);
    }

    public void clickAutocompleteLink() {
        logger.info("Navegando a la sección de Autocomplete");
        click(autocompleteLink);
        waitForElementToBeVisible(addressField);
    }

    public void fillAddressDetails(Address address) {
        refreshPageElements();

        logger.info("Iniciando el proceso de autocompletado para: " + address.getFullAddress());

        // El campo principal
        enterAddress(address.getFullAddress());

        // Campos secundarios
        enterStreetAddress(address.getStreet());
        enterStreetAddressDos(address.getStreet2());
        enterCity(address.getCity());
        enterState(address.getState());
        enterPostalCode(address.getZipCode());
        enterCountry(address.getCountry());

        logger.info("Todos los campos de dirección han sido completados.");
    }

    // --- MÉTODOS ATÓMICOS CON LOGGING (Punto 8) ---

    public void enterAddress(String address) {
        logger.debug("Ingresando dirección principal: " + address);
        scrollToElement(addressField);
        sendText(addressField, address);
    }

    public void enterStreetAddress(String streetAddress) {
        logger.debug("Ingresando número de calle: " + streetAddress);
        sendText(streetAddressField, streetAddress);
    }

    public void enterStreetAddressDos(String streetAddressDos) {
        logger.debug("Ingresando nombre de calle: " + streetAddressDos);
        sendText(streetAddressDosField, streetAddressDos);
    }

    public void enterCity(String city) {
        logger.debug("Ingresando ciudad: " + city);
        sendText(cityField, city);
    }

    public void enterState(String state) {
        logger.debug("Ingresando estado: " + state);
        sendText(stateField, state);
    }

    public void enterPostalCode(String postalCode) {
        logger.debug("Ingresando código postal: " + postalCode);
        sendText(postalCodeField, postalCode);
    }

    public void enterCountry(String country) {
        logger.debug("Ingresando país: " + country);
        sendText(countryField, country);
    }

    // --- MÉTODOS DE RETORNO DE VALORES ---

    public String getCityValue() { return cityField.getAttribute("value"); }
    public String getStateValue() { return stateField.getAttribute("value"); }
    public String getCountryValue() { return countryField.getAttribute("value"); }
}