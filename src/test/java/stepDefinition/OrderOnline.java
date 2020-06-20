package stepDefinition;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.utils.FileUtil;
import com.cucumber.listener.Reporter;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.AddressConfirmationPage;
import pageObjects.CartSummaryPage;
import pageObjects.ItemSelectionPage;
import pageObjects.LoginPage;
import pageObjects.OrderHistoryPage;
import pageObjects.PaymentConfirmationPage;
import pageObjects.ProfileDetailsPage;
import pageObjects.ShippingConfirmationPage;
import utils.CommonActions;

public class OrderOnline {
	public static WebDriver driver;
	public CommonActions comActions;
	public LoginPage loginPage;
	public ProfileDetailsPage profilePage;
	public ItemSelectionPage itemselectionPage;
	public CartSummaryPage cartSummaryPage;
	public AddressConfirmationPage addressConfirmPage;
	public ShippingConfirmationPage shipConfirmPage;
	public PaymentConfirmationPage payConfirmPage;
	public OrderHistoryPage orderHistoryPage;
	public String expectedUsername;
	public String propertyFilePath = "src/test/resources/config.properties";
	public Properties property = new Properties();
	public String expectedItemName, expectedItemPrice, expectedItemStatus, expectedTotalPrice;

	@Before
	public void beforeScenario() throws IOException {
		System.setProperty("webdriver.chrome.driver", "Library\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		comActions = new CommonActions();
		loginPage = PageFactory.initElements(driver, LoginPage.class);
		profilePage = PageFactory.initElements(driver, ProfileDetailsPage.class);
		itemselectionPage = PageFactory.initElements(driver, ItemSelectionPage.class);
		cartSummaryPage = PageFactory.initElements(driver, CartSummaryPage.class);
		shipConfirmPage = PageFactory.initElements(driver, ShippingConfirmationPage.class);
		payConfirmPage = PageFactory.initElements(driver, PaymentConfirmationPage.class);
		addressConfirmPage = PageFactory.initElements(driver, AddressConfirmationPage.class);
		orderHistoryPage = PageFactory.initElements(driver, OrderHistoryPage.class);
		FileInputStream fileInput = new FileInputStream(new File(propertyFilePath));
		property.load(fileInput);
	}
	
	@After
	public void AfterScenario(Scenario scenario) throws IOException {
		Reporter.loadXMLConfig(new File("src/test/resources/extent-config.xml"));
		if (scenario.isFailed()) {
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String screenshotpath="target/screenshots/"+scenario.getName()+"_"+comActions.getcurrentdateandtime()+".png";
			File destination=new File(screenshotpath);
			FileUtils.copyFile(src, destination);
			Reporter.addScreenCaptureFromPath(destination.toString());
		}
		Reporter.setSystemInfo("User Name", "Sathish Rajendran");
		Reporter.setSystemInfo("Application Name", "Automation Practise - Shopping Website");
		Reporter.setSystemInfo("Operating System Type", System.getProperty("os.name").toString());
		Reporter.setSystemInfo("Java Version", System.getProperty("java.version"));
		Reporter.setSystemInfo("Environment", "Production");
		driver.quit();
	}

	@Given("^User Navigates to Application URL$")
	public void user_Navigates_to_Application_URL() throws Throwable {
		driver.get(property.getProperty("ApplicationURL"));
		comActions.clickAnWebElement(driver, loginPage.signIn);
	}

	@When("^User Enters Valid UserName and Password then click on SignIn$")
	public void user_Enters_the_Valid_UserName_and_Password() throws Throwable {
		comActions.enterText(driver, loginPage.userName, property.getProperty("Username"));
		String password = comActions.decryptPassword(property.getProperty("Pswd"));
		comActions.enterText(driver, loginPage.password, password);
		comActions.clickAnWebElement(driver, loginPage.loginBtn);
	}

	@Then("^User should be successfully logged into Application$")
	public void user_should_be_successfully_logged_into_Application() throws Throwable {
		Assert.assertEquals("My account - My Store", driver.getTitle());
	}

	@Given("^User is on the MyAccount Page$")
	public void user_is_on_the_MyAccount_Page_of_ShoppingWebsite() throws Throwable {
		String pageTitle = comActions.getTextFromWebElement(driver, profilePage.pageTitle);
		Assert.assertEquals("MY ACCOUNT", pageTitle);
	}

	@When("^User Click on HomePage Button$")
	public void user_Click_on_HomePage_Button() throws Throwable {
		comActions.clickAnWebElement(driver, profilePage.homeButton);
	}

	@Then("^User successfully navigated to Shopping Website HomePage$")
	public void user_should_see_the_HomePage_of_Shopping_Website() throws Throwable {
		String pageTitle = itemselectionPage.homePageTitle.getAttribute("title");
		Assert.assertEquals("My Store", pageTitle);
	}

	@Then("^User Selects the TShirt Category and list of Available Tshirts should be displayed$")
	public void user_Selects_the_TShirt_Category_and_list_of_Available_Tshirts_should_be_displayed() throws Throwable {
		comActions.clickAnWebElement(driver, itemselectionPage.tshirtMenu);
		int itemCount = itemselectionPage.availablePoductSize.size();
		Assert.assertTrue(itemCount > 0);
		WebDriverWait wait = new WebDriverWait(driver, 30);

	}

	@Then("^User Select the Item and Click on AddtoCart Button$")
	public void user_Select_the_Item_and_Click_on_AddtoCart_Button() throws Throwable {
		comActions.moveToElement(driver, itemselectionPage.productImageLink);
		expectedItemName = itemselectionPage.productImageLink.getAttribute("title");
		expectedItemPrice = comActions.getTextFromWebElement(driver, itemselectionPage.productPrice);
		expectedItemStatus = comActions.getTextFromWebElement(driver, itemselectionPage.itemAvailablity);
		comActions.clickAnWebElement(driver, itemselectionPage.addToCartButton);
	}

	@Then("^Item should be successfully added to Cart$")
	public void item_should_be_successfully_added_to_Cart() throws Throwable {
		String cartMessage = comActions.getTextFromWebElement(driver, itemselectionPage.cartConfirmationMessage);
		Assert.assertEquals("Product successfully added to your shopping cart", cartMessage.trim());
		comActions.clickAnWebElement(driver, itemselectionPage.checkoutButton);

	}

	@Then("^Item Details in Cart Summary Page should matched with Item Selection Page$")
	public void item_name_and_Price_in_Cart_Page_should_matched_with_Shopping_Website() throws Throwable {
		String itemName = comActions.getTextFromWebElement(driver, cartSummaryPage.productName);
		Assert.assertEquals(expectedItemName, itemName);
		String itemStatus = comActions.getTextFromWebElement(driver, cartSummaryPage.itemAvailablity);
		Assert.assertEquals(expectedItemStatus, itemStatus);
		String itemPrice = comActions.getTextFromWebElement(driver, cartSummaryPage.productPrice);
		Assert.assertEquals(expectedItemPrice, itemPrice);
		expectedTotalPrice = comActions.getTextFromWebElement(driver, cartSummaryPage.totalPrice);

	}

	@Then("^User Click on ProceedtoCheckOut Button on Cart Summary Page$")
	public void user_Click_on_ProceedtoCheckOut_Button_on_Item_Confirmation_Page() throws Throwable {
		comActions.clickAnWebElement(driver, cartSummaryPage.checkoutButton);
	}

	@Then("^User Selects Delivery Address and click ProceedtoCheckOut Button on Address Confirmation Page$")
	public void user_Selects_Delivery_Address_and_click_ProceedtoCheckOut_Button_on_Address_Confirmation_Page()
			throws Throwable {
		Select select = new Select(addressConfirmPage.addressDropdown);
		select.selectByIndex(0);
		if (!addressConfirmPage.addressDropdown.isEnabled())
			comActions.clickAnWebElement(driver, addressConfirmPage.addressSelectionCheckbox);
		comActions.enterText(driver, addressConfirmPage.addressComments, "FOR TESTING");
		comActions.clickAnWebElement(driver, addressConfirmPage.checkoutButton);
	}

	@Then("^User Selects Shipping Option and click ProceedtoCheckOut Button on Shipping Confirmation Page$")
	public void user_Selects_Shipping_Option_and_click_ProceedtoCheckOut_Button_on_Shipping_Confirmation_Page()
			throws Throwable {
		comActions.waitForWebElement(driver, shipConfirmPage.shippingCareer);
		if (!shipConfirmPage.termsOfServiceCheck.isSelected())
			shipConfirmPage.termsOfServiceCheck.click();
		comActions.clickAnWebElement(driver, shipConfirmPage.checkoutButton);

	}

	@Then("^User Selects the Payment Method and Click on ConfirmMyOrder Button$")
	public void user_Selects_the_Payment_Method_and_Click_on_ConfirmMyOrder_Button() throws Throwable {
		String itemName = comActions.getTextFromWebElement(driver, payConfirmPage.productName);
		Assert.assertEquals(expectedItemName, itemName);
		String itemPrice = comActions.getTextFromWebElement(driver, payConfirmPage.productPrice);
		Assert.assertEquals(expectedItemPrice, itemPrice);
		String totalPrice = comActions.getTextFromWebElement(driver, payConfirmPage.totalPrice);
		Assert.assertEquals(expectedTotalPrice, totalPrice);
		String itemAvailability = comActions.getTextFromWebElement(driver, payConfirmPage.itemAvailablity);
		Assert.assertEquals(expectedItemStatus, itemAvailability);
		comActions.clickAnWebElement(driver, payConfirmPage.paymentSelection);
		comActions.clickAnWebElement(driver, payConfirmPage.confirmOrderButton);

	}

	@Then("^Order Confirmation Page should be displayed and Order details should present in OrderHistory Page$")
	public void order_Confirmation_Page_should_be_displayed_and_Order_details_should_present_in_OrderHistory_Page()
			throws Throwable {
		String confirmMessage = comActions.getTextFromWebElement(driver, payConfirmPage.orderConfirmationMessage);
		Assert.assertEquals("Your order on My Store is complete.", confirmMessage.trim());
		comActions.clickAnWebElement(driver, payConfirmPage.backToOrderButton);
		@SuppressWarnings("deprecation")
		Date orderDate = new Date(comActions.getTextFromWebElement(driver, orderHistoryPage.orderDate));
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		sdf.format(orderDate);
		Assert.assertEquals(comActions.getCurrentDate(), sdf.format(orderDate).toString());
		String totalPrice = comActions.getTextFromWebElement(driver, orderHistoryPage.totalPrice);
		Assert.assertEquals(expectedTotalPrice, totalPrice);
		String orderStatus = comActions.getTextFromWebElement(driver, orderHistoryPage.orderStatus);
		Assert.assertEquals("On backorder", orderStatus);

	}

	@Given("^User Click on MyPersonalInformation Menu$")
	public void user_Click_on_MyPersonalInformation_Menu() throws Throwable {
		comActions.clickAnWebElement(driver, profilePage.personalInformationMenu);
		String profilePagetitle = comActions.getTextFromWebElement(driver, profilePage.pageSubTitle);
		Assert.assertEquals("YOUR PERSONAL INFORMATION", profilePagetitle);

	}

	@Given("^User Modify the FirstName and Click on Save$")
	public void user_Modify_the_FirstName_and_Click_on_Save() throws Throwable {
		String existingFirstName = comActions.getTextFromWebElement(driver, profilePage.firstName);
		expectedUsername = comActions.generateRandomUserName();
		Assert.assertNotEquals(expectedUsername, existingFirstName);
		profilePage.firstName.clear();
		comActions.enterText(driver, profilePage.firstName, expectedUsername);
		comActions.enterText(driver, profilePage.currentPassword,
				comActions.decryptPassword(property.getProperty("Pswd")));
		comActions.clickAnWebElement(driver, profilePage.saveButton);

	}

	@Then("^User details update Confirmation should be displayed$")
	public void user_details_update_Confirmation_should_be_displayed() throws Throwable {
		String confirmMessage = comActions.getTextFromWebElement(driver, profilePage.userConfirmationMessage).trim();
		Assert.assertEquals("Your personal information has been successfully updated.", confirmMessage);

	}

	@Then("^Modified FirstName should updated in Right Top Corner of WebPage$")
	public void modified_FirstName_should_updated_in_Right_Top_Corner_of_WebPage() throws Throwable {
		String[] userName = comActions.getTextFromWebElement(driver, profilePage.rightTopUserName).split(" ");
		Assert.assertEquals(expectedUsername, userName[0].toLowerCase());

	}

	@Then("^Modified FirstName should updated in MyPersonalInformation Page$")
	public void modified_FirstName_should_updated_in_MyPersonalInformation_Page() throws Throwable {
		comActions.clickAnWebElement(driver, profilePage.rightTopUserName);
		comActions.clickAnWebElement(driver, profilePage.personalInformationMenu);
		String existUserName = profilePage.firstName.getAttribute("value").toLowerCase();
		Assert.assertEquals(expectedUsername, existUserName);

	}
	
	
}
