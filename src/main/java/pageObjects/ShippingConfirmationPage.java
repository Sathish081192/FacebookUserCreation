package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShippingConfirmationPage {

	@FindBy(xpath="//strong[contains(text(),'My carrier')]")
	public WebElement shippingCareer;
	
	@FindBy(xpath="//input[@type='checkbox' and @name='cgv']")
	public WebElement termsOfServiceCheck;
	
	@FindBy(xpath="//button[@name='processCarrier']/span[contains(text(),'Proceed to checkout')]")
	public WebElement checkoutButton;
}
