package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class uitest {
	public static WebDriver driver;
	public static String name;
	public static WebDriverWait wait;
	
	public static void main(String args[]) throws InterruptedException  {
	
		//Implicit Wait
		System.setProperty("webdriver.chrome.driver", "Library\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wait=new WebDriverWait(driver, 30);		
		uitest.login();
		uitest.orderGrocery();
		
		
		
		/*
		ArrayList<String> test=new ArrayList<String>();
		test.add("apple");
		test.add("orange");
		test.add("apple");
		test.add("Pomogeranate");
		test.remove(0);
		test.size();
		test.clear();
		//test.remove("apple");
		System.out.println(test);
		
		HashSet<String> testone1=new HashSet<String>();
		testone.add("apple");
		testone.add("orange");
		testone.add("apple");
		testone.add("Pomogeranate");
		System.out.println(testone);
		
		HashMap<String,String> map=new HashMap<String,String>();
		map.put("Sathish", "1111");
		map.put("Naresh", "2222");
		map.put("Gowtham", "3333");
		System.out.println(map);
		
		
		
		System.setProperty("webdriver.chrome.driver", "Library\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.flipkart.com/");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@class='_2zrpKA _1dBPDZ']")).sendKeys("sathish0991@yahoo.com");
		driver.findElement(By.xpath("//input[@class='_2zrpKA _3v41xv _1dBPDZ']")).sendKeys("test@123");
		driver.findElement(By.xpath("//button[@class='_2AkmmA _1LctnI _7UHT_c']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@class='LM6RPg']")).sendKeys("Samsung mobiles");
		driver.findElement(By.xpath("//button[@class='vh79eN']")).click();
		Thread.sleep(5000);
		String mobilename=driver.findElement(By.xpath("(//div[@class='_3wU53n'])[1]")).getText();
		//System.out.println(mobilename);
		if(mobilename.equals("Samsung Guru Music 2")) {
			//System.out.println("Test Case Passed");
		}
		else {
			//System.out.println("Test Case Failed");
		}
		Select sel=new Select(driver.findElement(By.xpath("(//select[@class='fPjUPw'])[1]")));
		sel.selectByValue("10000");
		Thread.sleep(5000);
		
		ArrayList<String> allmobiles=new ArrayList<String>();
		
		
		List<WebElement> allmobileNameElement=driver.findElements(By.xpath("//div[@class='_3wU53n']"));
		List<WebElement> allmobilePriceElement=driver.findElements(By.xpath("//div[@class='_1vC4OE _2rQ-NK']"));
		HashMap<String,String> mobileDetails=new HashMap<String,String>();
		
		
		int size=allmobileNameElement.size();
		/*for(int i=0;i<size;i++) {
			allmobiles.add(allmobileNameElement.get(i).getText());
		}
		System.out.println(allmobiles);*/
		
		/*for(int i=0;i<size;i++) {
			String mobileName=allmobileNameElement.get(i).getText();
			String price=allmobilePriceElement.get(i).getText();
			mobileDetails.put(mobileName, price);
			
		}*/
		
		
	}
	
	
	
	public static void orderGrocery() throws InterruptedException {
		
		WebElement searchBox=driver.findElement(By.xpath("//input[@class='LM6RPg']"));
		wait.until(ExpectedConditions.visibilityOf(searchBox));		
		driver.findElement(By.xpath("//input[@class='LM6RPg']")).sendKeys("grocery");
		driver.findElement(By.xpath("//button[@class='vh79eN']")).click();
		WebElement productLink=driver.findElement(By.xpath("(//a[@class='_2cLu-l'])[4]"));		
		wait.until(ExpectedConditions.visibilityOf(productLink));
		String parentwindow=driver.getWindowHandle();
		driver.findElement(By.xpath("(//a[@class='_2cLu-l'])[4]")).click();
		Set<String> allwindows = driver.getWindowHandles();
		for(String window:allwindows) {
			if(!window.equals(parentwindow)) {
				driver.switchTo().window(window);
				driver.findElement(By.xpath("//button[contains(@class,'_2MWPVK')]")).click();
				Thread.sleep(5000);
			}
		}
		driver.switchTo().window(parentwindow);
		driver.navigate().refresh();
		WebElement cart=driver.findElement(By.xpath("//span[text()='Cart']"));
		wait.until(ExpectedConditions.visibilityOf(cart));
		
		
		try {
		driver.findElement(By.xpath("//span[text()='Cart']")).click();
		}
		catch(StaleElementReferenceException e) {
			int i=0;
			int counter=3;
			while(i<counter) {
			wait.until(ExpectedConditions.visibilityOf(cart));
			driver.findElement(By.xpath("//span[text()='Cart']")).click();
			}
			
		}
		WebElement productName=driver.findElement(By.xpath("//span[@class='_2ygQEF']"));
		try {
		wait.until(ExpectedConditions.visibilityOf(productName));
		}
		catch(StaleElementReferenceException e) {
			int i=0;
			int counter=3;
			while(i<counter) {
			wait.until(ExpectedConditions.visibilityOf(cart));
			driver.findElement(By.xpath("//span[text()='Cart']")).click();
			}
		}
		
		String expected=driver.findElement(By.xpath("//span[@class='_2ygQEF']")).getText();
		if(expected.equals("Supermart Basket (1 item)")){
			System.out.println("Test case passed");
		}
		else {
			System.out.println("Test case failed");
		}
		
		
		
	}
	
	public static void login() throws InterruptedException {
		
		driver.manage().window().maximize();
		driver.get("https://www.flipkart.com/");
		WebElement userName=driver.findElement(By.xpath("//input[@class='_2zrpKA _1dBPDZ']"));
		wait.until(ExpectedConditions.visibilityOf(userName));		
		driver.findElement(By.xpath("//input[@class='_2zrpKA _1dBPDZ']")).sendKeys("sathish0991@yahoo.com");
		driver.findElement(By.xpath("//input[@class='_2zrpKA _3v41xv _1dBPDZ']")).sendKeys("test@123");
		driver.findElement(By.xpath("//button[@class='_2AkmmA _1LctnI _7UHT_c']")).click();
		Thread.sleep(5000);
		
		
	}
	
	
	
	public static void exception() {
		try {			
			int i=0;
			int j=9;
			int k=j/i;
			System.out.println(k);			
		}
		catch(ArithmeticException e) {
			System.out.println("Arithmetic Exception Handled");			
		}
		finally {
			System.out.println("Test");
			//driver.close();
		}
			
	}

}
