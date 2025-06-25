package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass{
	
	@Test(dataProvider = "LoginData", dataProviderClass=DataProviders.class, groups="Datadriven")
	public void verify_loginDDT(String email, String pwd, String exp) {
		
		logger.info("*********** Starting TC003_LoginDDT ***********");
		
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount Link");
		hp.clicklogin();
		logger.info("Clicked on Login Link");
		
		logger.info("Entering Login Credentials");
		LoginPage lp = new LoginPage(driver);
		lp.enterEmail(email);
		logger.info("Entered User Email");
		lp.enterPassword(pwd);
		logger.info("Entered User Password");
		lp.clickLogin();
		logger.info("Clicked Login Button");
		
		logger.info("Checking if Account Page Exists");
		MyAccountPage ap = new MyAccountPage(driver);
		boolean targetPage = ap.ifAccountExists();
		
		//Validation
		/* 
		 * Valid Data -- Login success -- Test Passed -- Logout
		 * 			  -- Login Failed -- Test Failed 
		 * 
		 * Invalid Data -- Login Success -- Test Failed -- Logout
		 * 				-- Login Failed -- Test Passed
		 */
		
		if(exp.equalsIgnoreCase("Valid")) {
			if(targetPage==true) {
				ap.clickLogout();
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("Invalid")) {
			if(targetPage==true) {
				ap.clickLogout();
				Assert.assertTrue(false);
			} else {
				Assert.assertTrue(true);
			}
		}
		
		
		} catch(Exception e) {
			logger.error("Test Failed..");
			logger.debug("Debug Logs....");
			Assert.fail();
		}
		
		logger.info("*********** Finished TC003_LoginDDT ***********");
	}
}
