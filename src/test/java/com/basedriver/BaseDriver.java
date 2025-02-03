package com.basedriver;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ResourceBundle;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class BaseDriver {
    public static WebDriver driver;
    public ResourceBundle rb;
    public Logger logger;

//    @Parameters({"os","browser"})
    @BeforeMethod
    @Parameters("browser")
    public void launchBrowser(String br)
    {
        rb = ResourceBundle.getBundle("data");
        logger = LogManager.getLogger(this.getClass());


        switch(br.toLowerCase())
        {
            case "chrome":  driver = new ChromeDriver();
                            System.setProperty("webdriver.chrome.driver","E:\\Selenium\\chromedriver-win64\\chromedriver.exe");break;
            case "edge" : driver = new EdgeDriver(); break;
            case "firefox" : driver = new FirefoxDriver(); break;
            default: System.out.println("Invalid Browser "); return;
        }

//        System.setProperty("webdriver.chrome.driver","E:\\Selenium\\chromedriver-win64\\chromedriver.exe");
//        driver = new ChromeDriver();

        driver.get(rb.getString("url"));

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

        try
        {
            assertEquals(driver.getTitle(), "Kapruka.com | Sri Lanka Online Shopping Site | Send Gifts to Sri Lanka");
        } catch (Exception e) {
            System.out.println("The Error is "+ e.getMessage());
        }

    }

    @AfterMethod
    public void closeBrowser()
    {
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
