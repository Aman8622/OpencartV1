package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_AccountLoginTest extends BaseClass {
	
	@Test(groups={"Sanity", "Master"})
	public void verify_account_login() {
		
		logger.info("*********** Starting TC002_AccountLoginTest ***********");
		
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount Link");
		hp.clicklogin();
		logger.info("Clicked on Login Link");
		
		logger.info("Entering Login Credentials");
		LoginPage lp = new LoginPage(driver);
		lp.enterEmail(p.getProperty("email"));
		logger.info("Entered User Email");
		lp.enterPassword(p.getProperty("password"));
		logger.info("Entered User Password");
		lp.clickLogin();
		logger.info("Clicked Login Button");
		
		logger.info("Checking if Account Exists");
		MyAccountPage ap = new MyAccountPage(driver);
//		Validation
		Assert.assertEquals(ap.ifAccountExists(), true);
		logger.info("Account Exists");
		
		logger.info("Clicking Logout Button");
		ap.clickLogout();
		logger.info("Logged Out");
		
		} catch(Exception e) {
			logger.error("Test Failed..");
			logger.debug("Debug Logs....");
			Assert.fail();
		}
		
		logger.info("*********** Finished TC002_AccountLoginTest ***********");
	}
	
}
