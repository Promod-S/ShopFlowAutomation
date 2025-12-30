package Promod_Framework.TestComponents;

import Promod_Framework.resources.ExtentReporterNG;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {
    ExtentTest test;
    ExtentReports extent= ExtentReporterNG.getReportObject();
    ThreadLocal<ExtentTest> extentTest= new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
//        ITestListener.super.onTestStart(result);
        test =extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);//unique thread id(ErrorValodationTest)->test
    }

    @Override
    public void onTestSuccess(ITestResult result) {
//        ITestListener.super.onTestSuccess(result);
        extentTest.get().log(Status.PASS,"Test Passes");
    }

    @Override
    public void onTestFailure(ITestResult result) {
//        ITestListener.super.onTestFailure(result);
        extentTest.get().fail(result.getThrowable()); //It shows error
        //also need to provide life to driver here
        try {
            driver = (WebDriver)result.getTestClass().getRealClass()
                    .getField("driver").get(result.getInstance());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //screenshot
        String filePath= null;
        try {
            filePath = getScreenshot(result.getMethod().getMethodName(),driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        extentTest.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());


    }

    @Override
    public void onTestSkipped(ITestResult result) {
//        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onFinish(ITestContext context) {
//        ITestListener.super.onFinish(context);
    extent.flush();
    }
}
