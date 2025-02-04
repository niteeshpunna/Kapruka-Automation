package com.testcases;

import com.basedriver.BaseDriver;
import com.pageObjects.HomePage;
import com.pageObjects.MyAccount;
import com.pageObjects.NewAccountPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TC_01_ValidLogin extends BaseDriver {

    @Test
    public void login() throws InterruptedException {

        try {
            //click on accounts button &wait
            HomePage hp = new HomePage(driver);
            hp.clickOnAccount();
            Thread.sleep(1000);

            //Creating an object for MyAccount page
            MyAccount map = new MyAccount(driver);
            //Verify the page
            map.verifyMyAccountPage();

            //Enter Username
            map.enterEmail(rb.getString("validemail"));
            Thread.sleep(1000);

            //Enter Password
            map.enterPassword(rb.getString("validPassword"));
            Thread.sleep(1000);

            //Click on Submit
            map.clickSubmit();
            Thread.sleep(1000);

            //Verify the Login page
            assertTrue(driver.findElement(By.xpath("//div[.='Logout ']")).isDisplayed());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            Assert.fail();
        }
    }
}
