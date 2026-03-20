package com.epam.automation.test;

import com.epam.automation.base.BaseTest;
import com.epam.automation.pages.SwitchWindowPage;
import com.epam.automation.utils.Constants;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SwitchWindowTest extends BaseTest {

    private SwitchWindowPage switchWindowPage;

    @BeforeMethod
    public void setUpPage() {
        switchWindowPage = new SwitchWindowPage(driver);
    }

    @Test(description = "Scenario 2: Window Navigation and Handling")
    public void NavigationAndWindowHandlingTest() {

        switchWindowPage.clickLogo();
        switchWindowPage.scrollToAndClickSwitchWindow();
        switchWindowPage.clickOpenNewTab();
        switchWindowPage.switchToNewTab();
        switchWindowPage.switchToMainTab();
        switchWindowPage.clickOpenAlert();
        switchWindowPage.switchAlert();
        switchWindowPage.clickLogoFormy();

        Assert.assertEquals(switchWindowPage.getMainTitleText(), Constants.MAIN_TITLE_TEXT);
    }
}
