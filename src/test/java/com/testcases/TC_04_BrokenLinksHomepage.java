package com.testcases;

import com.basedriver.BaseDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TC_04_BrokenLinksHomepage extends BaseDriver {

    @Test
    public void verifyFeatureProductsBrokenLinks() {
        //List of featured links - 3 : (//div[@class='CatalogueV2Design'])[1]

        for(int i=1; i<=3; i++) {
            List<WebElement> elements = driver.findElements(By.xpath("(//div[@class='CatalogueV2Design'])[" + i + "]//a"));
            try {
//                System.out.println(elements.size());
                int numFineLinks =0;
                int numBrokenLinks=0;

                for (WebElement e : elements) {
                    if (e.getAttribute("href") != null) {
                        URL u = new URL(e.getAttribute("href"));

                        HttpURLConnection con = (HttpURLConnection) u.openConnection();

                        con.setConnectTimeout(60000);

                        if (con.getResponseCode() == 200) {
//                            System.out.println(" The Link of " + e.getText() + " is Working fine");
                            numFineLinks++;
                        }
                        else {
                            System.out.println(" The Link of " + e.getText() + " is not Working ");
                            numBrokenLinks++;
                        }

                    }
                }
                System.out.println("The number of working Links: "+ numFineLinks);
                System.out.println("The number of broken Links: "+ numBrokenLinks);
                numFineLinks =0;
                numBrokenLinks=0;


            } catch (Exception e) {
                System.out.println(e.getMessage());
                Assert.fail();
            }


        }

    }
}
