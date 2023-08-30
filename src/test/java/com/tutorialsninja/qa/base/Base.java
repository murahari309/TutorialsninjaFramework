package com.tutorialsninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialsninja.qa.utils.Utilities;

public class Base {
	WebDriver driver;
	public Properties prop;
	public Properties dataProp;

	public  Base() {
		prop = new Properties();
		File propfile = new File(System.getProperty("user.dir")+ "\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");			
		try {
			FileInputStream fos = new FileInputStream(propfile);
			prop.load(fos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		 dataProp = new Properties();
		  File datapropfile= new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\testdata.properties");
		  try {
		  FileInputStream fis = new FileInputStream(datapropfile);
			dataProp.load(fis);
		  }catch(Exception e) {
			  e.printStackTrace();
		  }

	}

	public WebDriver IntializeBrowserAndOpenUrl(String browsername) {

		if (browsername.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browsername.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browsername.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		return driver;
	}

}
