package com.testcases;

import com.basedriver.BaseDriver;
import com.pageObjects.HomePage;
import com.pageObjects.MyAccount;
import com.pageObjects.NewAccountPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.utilities.Utilities.randomAlphaNumeric;
import static com.utilities.Utilities.randomString;

public class TC_03_ValidAccountCreation extends BaseDriver {

    @Test
    public void testValidAccountCreation()
    {
        try
        {
            logger.info("****** Starting TC_03_ValidAccountCreation ********* ");
            // Click on Account
            HomePage hp = new HomePage(driver);
            hp.clickOnAccount();
            logger.info("Clicked on Account link");
            Thread.sleep(1000);

            MyAccount ma = new MyAccount(driver);
            //Verfiy the Account creation Page
            ma.verifyMyAccountPage();
            logger.info(" Account page is verified");

            //Click on Create Account button
            ma.clickCreateAccountonHomePage();
            logger.info("Clicked on Create Account ");

            NewAccountPage nap = new NewAccountPage(driver);

            String firstname = randomString().toUpperCase();
            //Enter First Name
            nap.enterFirstName(firstname);

            String lastname = randomString().toUpperCase();
            //Enter Last Name
            nap.enterLastName(lastname);

            String email = firstname.toLowerCase()+lastname.toLowerCase()+"@yopmail.com";
            //Enter Invalid Email
            nap.enterValidEamil(email);

            String password = randomAlphaNumeric();
            //Create password
            nap.enterPassword(password);

            //Confirm Password
            nap.re_enterPassword(password);
            logger.info("Provided the details required for account creation");

            //Click on Create account
            nap.clickCreateAccount();
            Thread.sleep(1000);
            logger.info("Successfully New account cretaed for "+firstname+lastname);


            // Verify account is created
            nap.verifySuccessfulAccountCreation();
            logger.info("Validated Successful Account creation");
            Thread.sleep(2000);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            logger.error("Test Failed...");
            logger.debug("Debug logs..");
            Assert.fail();
        }

        logger.info("********** END of TC_03_ValidAccountCreation *********");
    }
}
