package com.utilities;


import org.apache.commons.lang3.RandomStringUtils;

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
}
