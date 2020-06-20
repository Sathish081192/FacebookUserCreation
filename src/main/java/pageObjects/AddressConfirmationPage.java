package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddressConfirmationPage {
	
	@FindBy(xpath="//select[@id='id_address_delivery']")
	public WebElement addressDropdown;
	
	@FindBy(xpath="//input[@id='addressesAreEquals']")
	public WebElement addressSelectionCheckbox;
	
	@FindBy(xpath="//textarea[@name='message']")
	public WebElement addressComments;
	
	@FindBy(xpath="//button[@name='processAddress']/span[contains(text(),'Proceed to checkout')]")
	public WebElement checkoutButton;
	
	
}
