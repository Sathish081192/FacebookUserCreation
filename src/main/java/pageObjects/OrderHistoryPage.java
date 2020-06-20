package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderHistoryPage {

	@FindBy(xpath="//td[@class='history_date bold']")
	public WebElement orderDate;
	
	@FindBy(xpath="//span[@class='price']")
	public WebElement totalPrice;
	
	@FindBy(xpath="//span[@class='label dark']")
	public WebElement orderStatus;
}
