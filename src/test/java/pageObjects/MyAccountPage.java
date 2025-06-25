package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {
	
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//h1[normalize-space()='My Account']")
	WebElement msgHeading;
	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement logoutBtn;
	
	public boolean ifAccountExists() {
		try {
			return (msgHeading.isDisplayed());
		} catch(Exception e) {
			return false;
		}
	}
	
	public void clickLogout() {
		logoutBtn.click();
	}
	
}
