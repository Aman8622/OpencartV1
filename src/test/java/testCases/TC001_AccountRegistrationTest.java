package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	@Test(groups={"Regression", "Master"})
	public void verify_account_registration() {
		
		logger.info("******** Starting TC001_AccountRegistrationTest ********");
		
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info(" Clicked on MyAccount Link ");
		hp.clickregister();
		logger.info(" Clicked on Register Link ");
		
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		
		logger.info(" Providing Customer Details ");
		regpage.enterFirstName(randomString());
		regpage.enterLastName(randomString());
		regpage.enterEmail(randomString()+"@gmail.com");
		regpage.enterPassword(randomLongString());
		regpage.acceptPolicy();
		regpage.clickContinue();
		
		logger.info(" Validating expected message.. ");
		String message = regpage.getConfirmationMsg();
		// Validation
		Assert.assertEquals(message, "Your Account Has Been Created!");
		} catch (Exception e) {
			logger.error("Test failed..");
			logger.debug("Debug logs..");
			Assert.fail();
		}
		
		logger.info("******** Finished TC001_AccountRegistrationTest ********");
	}
	
}
