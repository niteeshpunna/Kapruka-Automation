package com.utilities;

import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager implements ITestListener{

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest etest;

    public void onStart(ITestContext context)
    {
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/myReports.html");
        sparkReporter.config().setDocumentTitle("Kapruka Login Automation Report");
        sparkReporter.config().setReportName("Kapruka Login Report");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Computer Name", "Local Host");
        extent.setSystemInfo("Environment","QA");
        extent.setSystemInfo("Tester Name","Niteesh Kumar");
    }

    public void onTestStart(ITestResult result)
    {

        etest = extent.createTest(result.getName());
        etest.log(Status.PASS, "Test Case PASSED is: "+result.getName());
    }

    public void onTestSuccess(ITestResult result)
    {
        etest = extent.createTest(result.getName());
        etest.log(Status.PASS, "Test Case PASSED is: "+result.getName());
    }
    public void onTestFailure(ITestResult result)
    {
        etest = extent.createTest(result.getName());
        etest.log(Status.FAIL, "Test Case Failed is: "+result.getName());
        etest.log(Status.FAIL, "Test Case FAILED because: "+result.getThrowable());

    }

    public void onTestSkipped(ITestResult result)
    {
        etest = extent.createTest(result.getName());
        etest.log(Status.SKIP,"Test Case Skipped is: "+result.getName());
    }

    public void onFinish(ITestContext context){
        extent.flush();
    }
}
