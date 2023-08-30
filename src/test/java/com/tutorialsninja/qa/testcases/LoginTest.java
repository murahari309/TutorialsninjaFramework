package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginTest extends Base {
	public WebDriver driver;
	LoginPage login;
	AccountPage accountpage;

	public LoginTest() {
		super();
	}

	@AfterMethod
	public void TearDown() {

		driver.quit();
	}

	@BeforeMethod
	public void SetUp() {

		driver = IntializeBrowserAndOpenUrl(prop.getProperty("browser"));
		HomePage home = new HomePage(driver);
		home.Click_On_Myaccount_Dropdown();
		login = home.Click_On_Login_option();

	}

	@Test(priority = 1, dataProvider = "SupplyTestData")
	public void VerifyLoginWithValidCredentials(String Email, String Password) {

		login.EnterEmail(Email);
		login.EnterPassword(Password);
		accountpage = login.Click_On_Option();

		Assert.assertTrue(accountpage.accountInformationOption());

	}

	@DataProvider
	public Object[][] SupplyTestData() {

		Object[][] data = Utilities.GetTestDataFromExcel("Login");
		return data;
	}

	@Test(priority = 2)
	public void VerifyLoginWithInvalidCredentials() {

		login.EnterEmail(Utilities.GenerateEmailWithTimeStamp());
		login.EnterPassword(dataProp.getProperty("invalidpassword"));
		login.Click_On_Option();
		String actualre = login.warning_msg_of_emailandpassword_notmatching();
		String expectedre = dataProp.getProperty("emailpasswordnomatchwarning");
		Assert.assertEquals(actualre, expectedre);

	}

	@Test(priority = 3)
	public void VerifyLoginWithInvalidEmailAndValidPassword() {

		login.EnterEmail(Utilities.GenerateEmailWithTimeStamp());
		login.EnterPassword(prop.getProperty("validpassword"));
		login.Click_On_Option();
		String actualre = login.warning_msg_of_emailandpassword_notmatching();
		String expectedre = dataProp.getProperty("emailpasswordnomatchwarning");
		Assert.assertEquals(actualre, expectedre);

	}

	@Test(priority = 4)
	public void VerifyLoginWithValidEmailAndInvalidPassword() {

		login.EnterEmail(prop.getProperty("validemail"));
		login.EnterPassword(dataProp.getProperty("invalidpassword"));
		login.Click_On_Option();
		String actualre = login.warning_msg_of_emailandpassword_notmatching();
		String expectedre = dataProp.getProperty("emailpasswordnomatchwarning");
		Assert.assertEquals(actualre, expectedre);

	}

	@Test(priority = 5)
	public void VerifyLoginWithoutProvidingCredentials() {

		login.Click_On_Option();
		String actualre = login.warning_msg_of_emailandpassword_notmatching();
		String expectedre = dataProp.getProperty("emailpasswordnomatchwarning");
		Assert.assertEquals(actualre, expectedre);

	}

}
