package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base {

	public WebDriver driver;
	RegisterPage register;
	AccountSuccessPage accountsuccesspage;

	public RegisterTest() {
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
		register = home.Registeroption();

	}

	@Test(priority = 1)
	public void VerifyRegisteringAnAccountWithMandatoryfields() {

		register.firstnamefield(dataProp.getProperty("firstname"));
		register.lastnamefield(dataProp.getProperty("lastname"));
		register.emailfield(Utilities.GenerateEmailWithTimeStamp());
		register.telephonefield(dataProp.getProperty("telephone"));
		register.passwordfield(prop.getProperty("validpassword"));
		register.confirmpassword(prop.getProperty("validpassword"));
		register.checkboxfield();
		accountsuccesspage = register.continuebutton();

		String actualheading = accountsuccesspage.accountsuccessmessage();
		String expectedheading = dataProp.getProperty("accountsucesscreatedmsg");
		Assert.assertEquals(actualheading, expectedheading);

	}

	@Test(priority = 2)
	public void VerifyRegisteringAnAccountByprovidingallfields() {

		register.firstnamefield(dataProp.getProperty("firstname"));
		register.lastnamefield(dataProp.getProperty("lastname"));
		register.emailfield(Utilities.GenerateEmailWithTimeStamp());
		register.telephonefield(dataProp.getProperty("telephone"));
		register.passwordfield(prop.getProperty("validpassword"));
		register.confirmpassword(prop.getProperty("validpassword"));
		register.newsletter();
		register.checkboxfield();
		accountsuccesspage=register.continuebutton();
		String actualheading = accountsuccesspage.accountsuccessmessage();
		String expectedheading = dataProp.getProperty("accountsucesscreatedmsg");
		Assert.assertEquals(actualheading, expectedheading);

	}

	@Test(priority = 3)
	public void VerifyRegisteringAccountWithExistingEmail() {

		register.firstnamefield(dataProp.getProperty("firstname"));
		register.lastnamefield(dataProp.getProperty("lastname"));
		register.emailfield(prop.getProperty("validemail"));
		register.telephonefield(dataProp.getProperty("telephone"));
		register.passwordfield(prop.getProperty("validpassword"));
		register.confirmpassword(prop.getProperty("validpassword"));
		register.newsletter();
		register.checkboxfield();
		register.continuebutton();
		String actualwarning = register.duplicateemailwarning();
		String expectedwarning = dataProp.getProperty("duplicateEmail");
		Assert.assertEquals(actualwarning, expectedwarning);

	}

	@Test(priority = 4)
	public void verifyRegisteringAccountWithoutFillingAnyDetails() {

		register.continuebutton();

		String actualprivacypolicywarning = register.privacypolicywarning();

		String expectedprivacypolicywarning = dataProp.getProperty("privacypolicywarning");
		Assert.assertEquals(actualprivacypolicywarning, expectedprivacypolicywarning);

		String actualfirstnamewarning = register.firstnamewarning();

		String expectedfirstnamewarning = dataProp.getProperty("firstnamewarning");
		Assert.assertEquals(actualfirstnamewarning, expectedfirstnamewarning);

		String actuallastnamewarning = register.lastnamewarning();

		String expectedlastnamewarning = dataProp.getProperty("lastnamewarning");
		Assert.assertEquals(actuallastnamewarning, expectedlastnamewarning);

		String actualemailwarning = register.emailwarning();

		String expectedemailwarning = dataProp.getProperty("emailnotappeardwarning");
		Assert.assertEquals(actualemailwarning, expectedemailwarning);

		String actualtelephone = register.telephonewarning();

		String expectedtelephone = dataProp.getProperty("telephonewarning");
		Assert.assertEquals(actualtelephone, expectedtelephone);

		String actualpassword = register.passwordwarning();

		String expectedpassword = dataProp.getProperty("passwordwarning");
		Assert.assertEquals(actualpassword, expectedpassword);

	}

}
