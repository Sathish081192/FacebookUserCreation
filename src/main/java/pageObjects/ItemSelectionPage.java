package pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ItemSelectionPage{
	
	@FindBy(xpath="//div[@id='header_logo']/a")
	public WebElement homePageTitle;
	
	@FindBy(xpath="//li[a[@title='Women']]/following-sibling::li[2]")
	public WebElement tshirtMenu;
	
	@FindBy(xpath="//div[@class='product-container']")
	public List<WebElement> availablePoductSize;
	
	@FindBy(xpath="//div[@class='clearfix']/div/h2")
	public WebElement cartConfirmationMessage;
	
	@FindBy(xpath="//a[@class='product_img_link']/img")
	public WebElement productImageLink;
	
	@FindBy(xpath="//span[@class='price product-price']")
	public WebElement productPrice;
	
	@FindBy(xpath="//span[contains(text(),'Add to cart')]")
	public WebElement addToCartButton;
	
	@FindBy(xpath="//span[@class='available-now']")
	public WebElement itemAvailablity;
	
	@FindBy(xpath="//span[contains(text(),'Proceed to checkout')]")
	public WebElement checkoutButton;
}
