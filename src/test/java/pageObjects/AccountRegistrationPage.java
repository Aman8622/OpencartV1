package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
	
	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement firstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement lastName;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement email;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement password;
	
	@FindBy(xpath="//input[@id='input-newsletter']")
	WebElement subscribeBtn;
	
	@FindBy(xpath="//input[@id='input-agree']")
	WebElement policyChkd;
	
	@FindBy(xpath="//button[normalize-space()='Continue']")
	WebElement continueBtn;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement confirmationMsg;
	
	public void enterFirstName(String fname) {
		firstName.sendKeys(fname);
	}
	
	public void enterLastName(String lname) {
		lastName.sendKeys(lname);
	}
	
	public void enterEmail(String usrEmail) {
		email.sendKeys(usrEmail);
	}
	
	public void enterPassword(String usrPassword) {
		password.sendKeys(usrPassword);
	}
	
	public void subscribe() {
		subscribeBtn.click();
	}
	
	public void acceptPolicy() {
		policyChkd.click();
	}
	
	public void clickContinue() {
		continueBtn.click();
	}
	
	public String getConfirmationMsg() {
		try {
			return confirmationMsg.getText();
		} catch(Exception e) {
			return (e.getMessage());
		}
	}
	
}
