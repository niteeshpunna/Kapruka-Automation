package com.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.testng.Assert.assertEquals;

public class HomePage extends BasePage
{
    public HomePage(WebDriver driver)
    {
        super(driver);
    }

    //Click on Account
    @FindBy(xpath="//div[@class='myaccountblock']")
    WebElement clickLogin;
    public void clickOnAccount()
    {
        clickLogin.click();
    }

    public void verifyHomePage()
    {
        try
        {
            assertEquals(driver.getTitle(), "Kapruka.com | Sri Lanka Online Shopping Site | Send Gifts to Sri Lanka");
        } catch (Exception e) {
            System.out.println("The Error is "+ e.getMessage());
        }
    }
}
