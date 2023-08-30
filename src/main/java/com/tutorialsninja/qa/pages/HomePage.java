package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	
	
	
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement MyaccountDropdown;
	
	@FindBy(linkText="Login")
	private WebElement loginOption;
	
	@FindBy(xpath="(//a[text()='Register'])[1]")
	private WebElement Registeroption;
	
	@FindBy(xpath="//input[@name='search']")
	private WebElement searchboxfield;
	
	@FindBy(xpath="//span[@class='input-group-btn']/button")
	private WebElement searchboxbutton;
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void Click_On_Myaccount_Dropdown() {
		 MyaccountDropdown.click();
		
	}
	
	public LoginPage Click_On_Login_option() {
		loginOption.click();
		return new LoginPage(driver);
	}
	
	public RegisterPage Registeroption() {
		Registeroption.click();
		return new RegisterPage(driver);
	}
	
	public void textintoSearchboxfield(String ProductText) {
		searchboxfield.sendKeys(ProductText);
		
	}
	public SearchPage searchboxbutton() {
		searchboxbutton.click();
		return new SearchPage(driver);
	}

}
