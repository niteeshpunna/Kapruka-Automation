package com.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class MyAccount extends BasePage{
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    JavascriptExecutor js = (JavascriptExecutor)driver;

        public MyAccount(WebDriver driver) {
        super(driver);
    }
    //Verify MyAccount Page
    public void verifyMyAccountPage()
    {
        assertEquals(driver.getTitle(),"Account Login / New Account Creation");
    }

    //Enter Username
    @FindBy(id="exampleInputEmail1")
    WebElement email;
        public void enterEmail(String str)
        {
            email.sendKeys(str);
        }


    //Enter Password
    @FindBy(id="exampleInputPassword1")
    WebElement password;
    public void enterPassword(String str)
    {
        password.sendKeys(str);
    }

    // Click on Submit button
    @FindBy(xpath = "//input[@name='Login']")
    WebElement login;
    public void clickSubmit()
    {
        login.click();
    }

    //Click on Forgot Password
    public void clickForgotPassword()
    {
        driver.findElement(By.linkText("Forgot your password")).click();
    }

    //Click on Create Account
    @FindBy(xpath = "//button[text()='Create Account']")
    WebElement createaccount1;
    public void clickCreateAccountonHomePage()
    {
        js.executeScript("arguments[0].scrollIntoView(true);", createaccount1);
        WebElement w = wait.until(ExpectedConditions.elementToBeClickable(createaccount1));
        w.click();
    }

    //verify broken Links

}
