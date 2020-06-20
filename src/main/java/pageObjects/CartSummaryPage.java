package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartSummaryPage {
	
	@FindBy(xpath="//td[@class='cart_description']/p[@class='product-name']/a")
	public WebElement productName;
	
	@FindBy(xpath="//td[@class='cart_avail']/span")
	public WebElement itemAvailablity;
	
	@FindBy(xpath="//td[@class='cart_unit']/span[@class='price']/span")
	public WebElement productPrice;
	
	@FindBy(xpath="//span[@id='total_price']")
	public WebElement totalPrice;
	
	@FindBy(xpath="//p[@class='cart_navigation clearfix']/a[@title='Proceed to checkout']/span")
	public WebElement checkoutButton;

}
