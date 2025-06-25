package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement email;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement password;
	
	@FindBy(xpath="//button[normalize-space()='Login']")
	WebElement loginBtn;
	
	public void enterEmail(String usrEmail) {
		email.sendKeys(usrEmail);
	}
	
	public void enterPassword(String usrPassword) {
		password.sendKeys(usrPassword);
	}
	
	public void clickLogin() {
		loginBtn.click();
	}
}
