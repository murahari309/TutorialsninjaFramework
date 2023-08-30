package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	WebDriver driver;
	
	
	
	@FindBy(id="input-firstname")
	private WebElement firstnamefield;
	
	@FindBy(id="input-lastname")
	private WebElement lastnamefield;
	
	@FindBy(id="input-email")
	private WebElement emailfield;
	
	@FindBy(id="input-telephone")
	private WebElement telephonefield;
	
	@FindBy(id="input-password")
	private WebElement passwordfiled;
	
	@FindBy(id="input-confirm")
	private WebElement confirmpsswordfield;
	
	@FindBy(xpath="//input[@type='checkbox']")
	private WebElement checkboxfield;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueButton;
	
	
	
	@FindBy(xpath="//input[@value='1'][@name='newsletter']")
	private WebElement newsletteroption;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement duplicateEmail;
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	private WebElement privacy_policy_warning;
	
	@FindBy(xpath="//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstnamewarning;
	
	@FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastnamewarning;
	
	@FindBy(xpath="//input[@id='input-email']/following-sibling::div")
	private WebElement email_warning;
	
	@FindBy(xpath="//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephone_warning;
	
	@FindBy(xpath="//input[@id='input-password']/following-sibling::div")
	private WebElement password_warning;
	
public RegisterPage(WebDriver driver) {
		
		this.driver=driver;
		
		PageFactory.initElements(driver, this);
	}
	
	public void firstnamefield(String firstname) {
		firstnamefield.sendKeys(firstname);
	}
	public void lastnamefield(String lastname) {
		lastnamefield.sendKeys(lastname);
	}
	public void emailfield(String email) {
		emailfield.sendKeys(email);
	}
	public void telephonefield(String telephone) {
		telephonefield.sendKeys(telephone);
	}
	public void passwordfield(String password) {
		passwordfiled.sendKeys(password);
	}
	public void confirmpassword(String confirmPassword) {
		confirmpsswordfield.sendKeys(confirmPassword);
	}
	public void checkboxfield() {
		checkboxfield.click();
	}
	public AccountSuccessPage continuebutton() {
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public void newsletter() {
		newsletteroption.click();
	}
	
	public String duplicateemailwarning() {
		String EmailDuplicate = duplicateEmail.getText();
		return EmailDuplicate;
	}
	
	public String privacypolicywarning() {
		String privacywarningtext = privacy_policy_warning.getText();
		return privacywarningtext;
	}
	public String firstnamewarning() {
		String firstnamewarningtext = firstnamewarning.getText();
		return firstnamewarningtext;
	}
	public String lastnamewarning() {
		String lastnamewarningtext = lastnamewarning.getText();
		return lastnamewarningtext;
	}
	public String emailwarning() {
		String emailwarningtext = email_warning.getText();
		return emailwarningtext;
	}
	
	public String telephonewarning() {
		String telephonewarningtext = telephone_warning.getText();
		return telephonewarningtext;
	}
	public String passwordwarning() {
		String passwordwarningtext = password_warning.getText();
		return passwordwarningtext;
	}

}
