package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	
	
	
	@FindBy(id="input-email")
	private WebElement EmailField;
	
	@FindBy(id="input-password")
	private WebElement PasswordField;
	
	@FindBy(xpath="//input[@value='Login']")
	private WebElement loginoption;
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	private WebElement emaipasswordnotmatchingwarning;
	
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public void EnterEmail(String EmailText) {
		
		EmailField.sendKeys(EmailText);
	}
	public void EnterPassword(String PasswordText) {
		
		PasswordField.sendKeys(PasswordText);
	}
	public AccountPage Click_On_Option() {
		loginoption.click();
		return new AccountPage(driver);
	}
	
	
	public String warning_msg_of_emailandpassword_notmatching() {
		String warningtext = emaipasswordnotmatchingwarning.getText();
		return warningtext;
		
	}

}
