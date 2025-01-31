package com.testcases;

import com.basedriver.BaseDriver;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TC_01_ValidLogin extends BaseDriver {

    @Test(priority=1)
    public void login() throws InterruptedException {

        try {
            //click on accounts button &wait
            driver.findElement(By.xpath("//a[@href='https://www.kapruka.com/shops/customerAccounts/accountLogin.jsp']")).click();
            Thread.sleep(1000);

            //Enter email
            driver.findElement(By.id("exampleInputEmail1")).sendKeys(rb.getString("validemail"));

            // Enter password
            driver.findElement(By.id("exampleInputPassword1")).sendKeys(rb.getString("validPassword"));

            //click on Login button
            driver.findElement(By.xpath("//input[@type='submit']")).click();
            Thread.sleep(1000);

            //verify login success
            assertEquals(driver.getTitle(),"Your Account");
            Thread.sleep(1000);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
