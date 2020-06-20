package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ProfileDetailsPage {
	
	@FindBy(xpath="//h1[@class='page-heading']")
	public WebElement pageTitle;
	
	@FindBy(xpath="//a[@title='Home']/span")
	public WebElement homeButton;
	
	@FindBy(xpath="//span[contains(text(),'My personal information')]")
	public WebElement personalInformationMenu;
	
	@FindBy(xpath="//input[@id='firstname']")
	public WebElement firstName;
	
	@FindBy(xpath="//input[@id='old_passwd']")
	public WebElement currentPassword;
	
	@FindBy(xpath="//span[contains(text(),'Save')]")
	public WebElement saveButton;
	
	@FindBy(xpath="//p[@class='alert alert-success']")
	public WebElement userConfirmationMessage;
	
	@FindBy(xpath="//a[@title='View my customer account']/span")
	public WebElement rightTopUserName;
	
	@FindBy(xpath="//h1[@class='page-subheading']")
	public WebElement pageSubTitle;
	
}
