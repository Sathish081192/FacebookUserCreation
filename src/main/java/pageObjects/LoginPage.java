package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage {

	@FindBy(xpath="//a[contains(text(),'Sign in')]")
	public WebElement signIn;
	
	@FindBy(xpath="//input[@id='email' and @type='text']")
	public WebElement userName;
	
	@FindBy(xpath="//input[@id='passwd' and @type='password']")
	public WebElement password;
	
	@FindBy(xpath="//button[@id='SubmitLogin']/span")
	public WebElement loginBtn;
	
}
