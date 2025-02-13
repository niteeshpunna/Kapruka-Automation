package com.basedriver;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
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
    @Parameters({"os","browser"})
    public void launchBrowser(String os, String br) throws URISyntaxException, MalformedURLException {
        rb = ResourceBundle.getBundle("data");
        logger = LogManager.getLogger(this.getClass());



        if(rb.getString("execution_env").equalsIgnoreCase("local")){
            logger.info("Local webdriver initiated");
            switch(br.toLowerCase())
            {
                case "chrome":  driver = new ChromeDriver();
                    System.setProperty("webdriver.chrome.driver","E:\\Selenium\\chromedriver-win64\\chromedriver.exe");break;
                case "edge" : driver = new EdgeDriver(); break;
                case "firefox" : driver = new FirefoxDriver(); break;
                default: System.out.println("Invalid Browser "); return;
            }


        }
        else if(rb.getString("execution_env").equalsIgnoreCase("remote")){

            DesiredCapabilities dc = new DesiredCapabilities();

            //os is decided (Os need to take from the xml/ Parameters
            if(os.equalsIgnoreCase("windows")){
                dc.setPlatform(Platform.WIN10);
            }
            else if(os.equalsIgnoreCase("mac")){
                dc.setPlatform(Platform.MAC);
            }
            else{
                System.out.println("No Matching OS");
                return;
            }

            //browser
            switch (br.toLowerCase())
            {
                case "chrome" : dc.setBrowserName("chrome"); break;
                case "edge" : dc.setBrowserName("MicrosoftEdge"); break;
                case "firefox" : dc.setBrowserName("firefox"); break;
                default: System.out.println("No matching Browser"); return;
            }


            driver = new RemoteWebDriver(new URI("http://192.168.1.2:4444/wd/hub").toURL(), dc);

        }



//        System.setProperty("webdriver.chrome.driver","E:\\Selenium\\chromedriver-win64\\chromedriver.exe");
//        driver = new ChromeDriver();


        driver.get(rb.getString("url"));
        logger.info("Website is launched");
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
