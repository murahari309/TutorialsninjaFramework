package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	WebDriver driver;
	
	

	@FindBy(linkText="HP LP3065")
	private WebElement validproduct;
	
	
	@FindBy(xpath="//input[@id='button-search']/following-sibling::p")
	private WebElement noproductwarning;
	
	public SearchPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean validproducttext() {
		boolean displayvalidproduct = validproduct.isDisplayed();
		return displayvalidproduct;
	}
	
	public String noproductwarning() {
		String NoProduct = noproductwarning.getText();
		return NoProduct;
	}
}
