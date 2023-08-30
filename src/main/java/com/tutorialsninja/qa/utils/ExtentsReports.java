package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentsReports {
	
	
	public static ExtentReports generateExtentReport() {
		
		ExtentReports extentreporter = new ExtentReports();
		File file = new File(System.getProperty("user.dir")+"\\test-output\\Extentreports\\extentrepo.html");
		ExtentSparkReporter sparkreporter = new ExtentSparkReporter(file);
		sparkreporter.config().setTheme(Theme.DARK);
		sparkreporter.config().setReportName("tutorialsninja test automation results");
		sparkreporter.config().setDocumentTitle("tn automation results");
		sparkreporter.config().setTimeStampFormat("dd/mm/yyyy hh:mm:ss");
		
		extentreporter.attachReporter(sparkreporter);
		
		Properties configprop = new Properties();
		File f = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		try {
		FileInputStream fis = new FileInputStream(f);
		configprop.load(fis);
		}catch(Exception e) {
			e.printStackTrace();
		}
		extentreporter.setSystemInfo("Application URL",configprop.getProperty("url"));
		extentreporter.setSystemInfo("Browser Name",configprop.getProperty("browser"));
		extentreporter.setSystemInfo("Email",configprop.getProperty("validemail"));
		extentreporter.setSystemInfo("Password", configprop.getProperty("validpassword"));
		extentreporter.setSystemInfo("Operating System",System.getProperty("os.name"));
		extentreporter.setSystemInfo("username",System.getProperty("user.name"));
		extentreporter.setSystemInfo("java version",System.getProperty("java.version"));
		
		return extentreporter;
		
	}
	

}
