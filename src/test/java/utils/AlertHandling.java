package utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertHandling {

	public static void main(String[] args) {
		AlertHandling.dismissAlertHandle();

	}
	
	public static void acceptAlertHandle() {
		System.setProperty("webdriver.chrome.driver", "Library\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		WebDriverWait wait=new WebDriverWait(driver, 30);
		driver.get("https://letskodeit.teachable.com/p/practice");
		WebElement textbox=driver.findElement(By.xpath("//input[@id='name']"));
		wait.until(ExpectedConditions.visibilityOf(textbox));
		textbox.sendKeys("Test Purpose");
		driver.findElement(By.xpath("//input[@id='confirmbtn']")).click();
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert=driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.accept();
		System.out.println("Alert Accepted Successfully");
	}
	
	public static void dismissAlertHandle() {
		System.setProperty("webdriver.chrome.driver", "Library\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		WebDriverWait wait=new WebDriverWait(driver, 30);
		driver.get("https://letskodeit.teachable.com/p/practice");
		WebElement textbox=driver.findElement(By.xpath("//input[@id='name']"));
		wait.until(ExpectedConditions.visibilityOf(textbox));
		textbox.sendKeys("Test Purpose");
		driver.findElement(By.xpath("//input[@id='confirmbtn']")).click();
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert=driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.dismiss();
		System.out.println("Alert Dismissed Successfully");
		driver.close();
	}

}
