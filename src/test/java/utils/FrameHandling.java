package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FrameHandling {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		FrameHandling.multipleframeHandle();
	}
	
	public static void singleframeHandle() {
		System.setProperty("webdriver.chrome.driver", "Library\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		WebDriverWait wait=new WebDriverWait(driver, 30);
		driver.get("http://the-internet.herokuapp.com/iframe");
		WebElement frameid=driver.findElement(By.xpath("//iframe[@id='mce_0_ifr']"));
		wait.until(ExpectedConditions.visibilityOf(frameid));
		driver.switchTo().frame(frameid);		
		String value=driver.findElement(By.xpath("//body[@id='tinymce']/p")).getText();
		System.out.println(value);
		driver.close();
	}
	
	public static void multipleframeHandle() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "Library\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		WebDriverWait wait=new WebDriverWait(driver, 30);
		driver.get("http://the-internet.herokuapp.com/nested_frames");
		WebElement parentframe=driver.findElement(By.xpath("//frame[@name='frame-top']"));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(parentframe));
		WebElement childFrame=driver.findElement(By.xpath("//frame[@name='frame-left']"));
		try{
			driver.switchTo().frame(parentframe);
		}
		catch(StaleElementReferenceException e) {
			System.out.println("Inside Catch Block");
			driver.navigate().refresh();
			Thread.sleep(10000);
			driver.switchTo().frame(parentframe);
		}
		driver.switchTo().frame(childFrame);
		String value=driver.findElement(By.xpath("//html/body")).getText();
		System.out.println(value);
		
		
		
		
	}

}
