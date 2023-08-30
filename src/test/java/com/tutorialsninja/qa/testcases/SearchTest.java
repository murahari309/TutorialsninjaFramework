package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class SearchTest extends Base {
	public WebDriver driver;
	SearchPage search;
	
	public SearchTest() {
		super();
	}
	
	@BeforeMethod
	public void Setup() {
		
		driver=IntializeBrowserAndOpenUrl(prop.getProperty("browser"));
		
	}
	@AfterMethod
	public void TearDown() {
		driver.quit();
	}
	
	@Test(priority=1)
	public void VerifySearchWithValidProduct() {
		HomePage home= new HomePage(driver);
		home.textintoSearchboxfield(dataProp.getProperty("validproduct"));
		 search = home.searchboxbutton();
		
			
		Assert.assertTrue(search.validproducttext());			
	}
	@Test(priority=2)
	public void VerifySearchWithInvalidProduct() {
		HomePage home= new HomePage(driver);
		home.textintoSearchboxfield(dataProp.getProperty("invalidproduct"));
		search=home.searchboxbutton();
		
		
		
		
		String actualmsg = search.noproductwarning();
		String expectedmsg=dataProp.getProperty("no_product_text_in_search_results");
		Assert.assertEquals(actualmsg, expectedmsg);	
	}
	@Test(priority=3,dependsOnMethods= {"VerifySearchWithValidProduct","VerifySearchWithInvalidProduct"})
	public void VerifySearchWithoutAnyProduct() {
		HomePage home= new HomePage(driver);
		search=home.searchboxbutton();
		
		
		
		String actualmsg = search.noproductwarning();
		String expectedmsg=dataProp.getProperty("no_product_text_in_search_results");
		Assert.assertEquals(actualmsg, expectedmsg);
	}
	
	

}
