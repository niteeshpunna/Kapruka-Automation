package com.testcases;

import com.basedriver.BaseDriver;
import com.pageObjects.HomePage;
import com.pageObjects.MyAccount;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TC_02_InvalidLogin extends BaseDriver
{
    @Test(priority=2)
    public void invalidPasswordLogin()
    {
        try
        {
            //click on accounts button &wait
            HomePage hp = new HomePage(driver);
            hp.clickOnAccount();
//            Thread.sleep(1000);

            MyAccount map = new MyAccount(driver);
            //Enter Username
            map.verifyMyAccountPage();

            //
            map.enterEmail(rb.getString("validemail"));
            Thread.sleep(1000);

            map.enterPassword(rb.getString("validPassword"));
            Thread.sleep(1000);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test(priority=3)
    public void invalidEmailLogin()
    {
        try
        {
            //click on accounts button &wait
            driver.findElement(By.xpath("//a[@href='https://www.kapruka.com/shops/customerAccounts/accountLogin.jsp']")).click();
            Thread.sleep(1000);

            //Enter invalid email
            driver.findElement(By.id("exampleInputEmail1")).sendKeys(rb.getString("invalidemail"));

            // Enter password
            driver.findElement(By.id("exampleInputPassword1")).sendKeys(rb.getString("validPassword"));

            //click on Login button
            driver.findElement(By.xpath("//input[@type='submit']")).click();
            Thread.sleep(1000);

            //verify login success
            assertEquals(driver.findElement(By.xpath("Sorry, we did not find an account for that e-mail address.")).getText(),"Your password is wrong. Please try again.");
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test(priority=4)
    public void invalidLoginCredentials() {
        try {
            //click on accounts button &wait
            driver.findElement(By.xpath("//a[@href='https://www.kapruka.com/shops/customerAccounts/accountLogin.jsp']")).click();
            Thread.sleep(1000);

            //Enter invalid email
            driver.findElement(By.id("exampleInputEmail1")).sendKeys(rb.getString("invalidemail"));

            // Enter invalid password
            driver.findElement(By.id("exampleInputPassword1")).sendKeys(rb.getString("invalidPassword"));

            //click on Login button
            driver.findElement(By.xpath("//input[@type='submit']")).click();
            Thread.sleep(1000);

            //verify login success
            assertEquals(driver.findElement(By.xpath("Sorry, we did not find an account for that e-mail address.")).getText(), "Your password is wrong. Please try again.");
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
