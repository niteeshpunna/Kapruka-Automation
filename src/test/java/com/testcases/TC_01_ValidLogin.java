package com.testcases;

import com.basedriver.BaseDriver;
import com.pageObjects.HomePage;
import com.pageObjects.MyAccount;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TC_01_ValidLogin extends BaseDriver {

    @Test(priority=1)
    public void login() throws InterruptedException {

        try {
            //click on accounts button &wait
            HomePage hp = new HomePage(driver);
            hp.clickOnLogin();
//            Thread.sleep(1000);

            MyAccount map = new MyAccount(driver);
            //Enter Username
            map.verifyMyAccountPage();

            //
            map.enterEmail(rb.getString("validemail"));
            Thread.sleep(1000);

            map.enterPassword(rb.getString("validPassword"));
            Thread.sleep(1000);

            map.clickSubmit();

//            map.


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
