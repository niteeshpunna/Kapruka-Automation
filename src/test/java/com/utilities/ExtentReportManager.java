package com.utilities;

import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//import org.apache.commons.mail.ImageHtmlEmail;

public class ExtentReportManager implements ITestListener{

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest etest;

    String reportName;

    public void onStart(ITestContext context)
    {

        /*SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        Date dt = new Date();
        String currentDateStamp = df.format(dt);*/

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        reportName = "Test-Report-" + timeStamp + ".html";

        sparkReporter = new ExtentSparkReporter(".\\reports\\"+reportName);

        sparkReporter.config().setDocumentTitle("Kapruka Automation");
        sparkReporter.config().setReportName("Kapruka Automation Report");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Computer Name", "Local Host");
        extent.setSystemInfo("Environment","QA");
        extent.setSystemInfo("Type","Web Application");
        extent.setSystemInfo("Tester Name",System.getProperty("user.name"));

        String os = context.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System", "os");

        String browser = context.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser",browser);

        List<String> groups = context.getCurrentXmlTest().getIncludedGroups();
        if(!groups.isEmpty())
            extent.setSystemInfo("Groups", groups.toString());

    }

    /*public void onTestStart(ITestResult result)
    {

        etest = extent.createTest(result.getTestClass().getName());
        etest.log(Status.PASS, result.getName()+" got successfully executed");
    }*/

    public void onTestSuccess(ITestResult result)
    {
        etest = extent.createTest(result.getTestClass().getName());
        etest.log(Status.PASS, result.getName()+" got successfully executed");
    }
    public void onTestFailure(ITestResult result)
    {
        etest = extent.createTest(result.getName());
        etest.log(Status.FAIL, result.getName()+" got Failed");
        etest.log(Status.INFO, "Test Case FAILED "+result.getThrowable().getMessage());

        try{
            String imgpath = new Utilities().captureScreenshot(result.getName());
            etest.addScreenCaptureFromPath(imgpath);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onTestSkipped(ITestResult result)
    {
        etest = extent.createTest(result.getTestClass().getName());
        etest.log(Status.SKIP,result.getName()+" got skipped");
        etest.log(Status.INFO,result.getThrowable().getMessage());
    }

    public void onFinish(ITestContext context){
        extent.flush();

        //Open the report automatically in browser
        String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+reportName;
        File extentReport = new File(pathOfExtentReport);

        try{
            Desktop.getDesktop().browse(extentReport.toURI());
        }catch (IOException e){
            e.printStackTrace();
        }

       /* //Send a report to email automatically
        try{
            URL url = new URL("file://"+System.getProperty("user.dir")+"\\reports\\"+reportName);

            ImageHtmlEmail email = new ImageHtmlEmail();
        } catch (Exception e) {
            e.getMessage();
        }*/
    }
}
