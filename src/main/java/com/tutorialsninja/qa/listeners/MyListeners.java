package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentsReports;
import com.tutorialsninja.qa.utils.Utilities;

public class MyListeners implements ITestListener {
	ExtentReports extent;
	ExtentTest etest ;
	String testname;

	@Override
	public void onTestStart(ITestResult result) {
		 testname = result.getName();
		 etest = extent.createTest(testname);
		etest.log(Status.INFO, testname+" Execution started");
		
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		
		etest.log(Status.PASS, testname +" got successfully executed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		
		WebDriver driver=null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			
			e.printStackTrace();
		}
		
		String destinationScreenshot = Utilities.caputureScreeshot(driver, testname);
		
		etest.addScreenCaptureFromPath(destinationScreenshot);
		etest.log(Status.INFO,result.getThrowable());
		etest.log(Status.FAIL,testname + " got failed" );
		
				
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		
		etest.log(Status.INFO,result.getThrowable()) ;
		etest.log(Status.SKIP,testname + " got skipped");
		
		
	}

	@Override
	public void onStart(ITestContext context) {
		
	    extent = ExtentsReports.generateExtentReport();
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
		extent.flush();
		String pathofextentreport=System.getProperty("user.dir")+"\\test-output\\Extentreports\\extentrepo.html";
		File extentpathfile =new File(pathofextentreport);
		try {
			Desktop.getDesktop().browse(extentpathfile.toURI());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
