package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CommonActions{	
	
	/*This method will wait for webelement to be Visible*/
	public void waitForWebElement(WebDriver driver,WebElement element) {
		WebDriverWait wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/*This method is used to passing the text to WebElement */
	public void enterText(WebDriver driver,WebElement element,String text) {
		waitForWebElement(driver, element);
		element.sendKeys(text);
	}
	
	/* This method will return the text from WebElement */
	public String getTextFromWebElement(WebDriver driver,WebElement element) {
		waitForWebElement(driver, element);
		return element.getText();
	}
	
	/* This method will click the WebElement */
	public void clickAnWebElement(WebDriver driver,WebElement element) {
		waitForWebElement(driver, element);
		element.click();
	}
	
	/*This method used for move to particluar element using Action Class*/
	public void moveToElement(WebDriver driver,WebElement element) {
		Actions mouseAction=new Actions(driver);
		waitForWebElement(driver, element);
		mouseAction.moveToElement(element).build().perform();		
	}
	
	/*This method used for decrypyting the encrypted password*/
	public String decryptPassword(String encodedString) {
		byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
		String decodedString = new String(decodedBytes);
		return decodedString;		
	}
	
	/*This method used for encrypting the password string */
	public String encryptPassword(String decodedString) {
		String encodedString = Base64.getEncoder().encodeToString(decodedString.getBytes());
		return encodedString;	
		
	}
	
	/*This method will return the current date alone*/
	public String getCurrentDate() {
		Date date = new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
		return sdf.format(date);
	}
	
	/*This method will generate random username for FirstName Modification*/
	public String generateRandomUserName() {
		Random r = new Random();
		char c = (char)(r.nextInt(26) + 'a');
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<=6;i++) {
			c = (char)(r.nextInt(26) + 'a');
			sb.append(c);
		}
		return sb.toString();
	}
	
	/*This method will return current date and time*/
	public String getcurrentdateandtime(){
		String str = null;
		try{
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SSS");
		Date date = new Date();
		str= dateFormat.format(date);
		str = str.replace(" ", "").replaceAll("/", "").replaceAll(":", "");
		
	
		}
		catch(Exception e){

		}
		return str;
}
	
	public static void main(String args[]) {
		CommonActions com=new CommonActions();
		System.out.println(com.encryptPassword("Gowtham1!"));
	}
	
	
}
