package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaymentConfirmationPage {
	@FindBy(xpath="//p[@class='product-name']/a")
	public WebElement productName;
	
	@FindBy(xpath="//td[@class='cart_avail']/span")
	public WebElement itemAvailablity;
	
	@FindBy(xpath="//td[@class='cart_unit']/span[@class='price']/span")
	public WebElement productPrice;
	
	@FindBy(xpath="//span[@id='total_price']")
	public WebElement totalPrice;
	
	@FindBy(xpath="//a[@class='bankwire']")
	public WebElement paymentSelection;
	
	@FindBy(xpath="//span[contains(text(),'I confirm my order')]")
	public WebElement confirmOrderButton;
	
	@FindBy(xpath="//p[@class='cheque-indent']/strong")
	public WebElement orderConfirmationMessage;
	
	@FindBy(xpath="//a[@title='Back to orders']/i")
	public WebElement backToOrderButton;
	
	

}
