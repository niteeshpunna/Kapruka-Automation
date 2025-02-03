package com.pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class NewAccountPage extends BasePage{

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    JavascriptExecutor js = (JavascriptExecutor)driver;

    public NewAccountPage(WebDriver driver)
    {
        super(driver);
    }

    //Click on Create Account
    @FindBy(xpath = "//button[.='Create Account']")
    WebElement createaccount;
    public void clickCreateAccount()
    {
        js.executeScript("arguments[0].scrollIntoView(true);",createaccount);
        wait.until(ExpectedConditions.elementToBeClickable(createaccount));
        createaccount.click();
    }

    //Verfiy the Account creation Page
    public void verifyAccountCreationPage()
    {
        assertEquals(driver.getTitle(),"Kapruka New Account Creation");
    }

    //Enter First Name
    @FindBy(xpath = "//input[@name='firstName']")
    WebElement firstname;
    public void enterFirstName(String str)
    {
        firstname.sendKeys(str);
    }
    //Enter Last Name
    @FindBy(xpath = "//input[@name='lastName']")
    WebElement lastname;
    public void enterLastName(String str)
    {
        lastname.sendKeys(str);
    }

    //Enter valid Email
    @FindBy(xpath = "//input[@name='email']")
    WebElement email;
    public void enterValidEamil(String str)
    {
        email.sendKeys(str);
    }

    //Create password
    @FindBy(xpath = "//input[@name='password']")
    WebElement password;
    public void enterPassword(String str)
    {
        password.sendKeys(str);
    }

    //Confirm Password
    @FindBy(xpath = "//input[@name='passwordReConfirm']")
    WebElement confirmpassword;
    public void re_enterPassword(String str)
    {
        confirmpassword.sendKeys(str);
    }


    // Verify account is created
    @FindBy(xpath = "//h3[.='Congratulations! Your account has been created.']")
    WebElement welcomemessage;
    public void verifySuccessfulAccountCreation()
    {
        assertEquals(welcomemessage.getText(),"Congratulations! Your account has been created.");

    }

}
