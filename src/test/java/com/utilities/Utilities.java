package com.utilities;


import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.basedriver.BaseDriver.driver;

public class Utilities {

    public static String randomString()
    {
        return RandomStringUtils.randomAlphabetic(5);
    }

    public static String randomPhoneNumber()
    {
        return RandomStringUtils.randomNumeric(10);
    }

    public static String randomAlphaNumeric()
    {
        String num = RandomStringUtils.randomNumeric(3);
        String str = RandomStringUtils.randomAlphabetic(7);
        return str+"@"+num;
    }

    public String captureScreenshot(String tname) throws IOException
    {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);

        String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeStamp+".png";
        File targetFile = new File(targetFilePath);

        FileUtils.copyFile(sourceFile, targetFile);
        return targetFilePath;
    }
}
