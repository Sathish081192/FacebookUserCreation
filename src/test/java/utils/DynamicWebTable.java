package utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

public class DynamicWebTable {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		DynamicWebTable.getProductAttributes();
	}
	
	
	//To find the Row and Column index of input String - Ltd
	public static void findProductIndex() {
		System.setProperty("webdriver.chrome.driver", "Library\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		WebDriverWait wait=new WebDriverWait(driver, 30);
		driver.get("http://demo.guru99.com/test/web-table-element.php#");
		WebElement test=driver.findElement(By.xpath("//table/tbody/tr[1]/td[2]"));
		wait.until(ExpectedConditions.visibilityOf(test));
		List<WebElement> rows=driver.findElements(By.tagName("tr"));
		for(int i=1;i<rows.size();i++) {
			List<WebElement> columns=driver.findElements(By.xpath("//table/tbody/tr[1]/td"));
			for(int j=1;j<columns.size();j++) {
				String text=driver.findElement(By.xpath("//table/tbody/tr["+i+"]/td["+j+"]")).getText();
				if(text.contains("Ltd")) {
					System.out.println("Product Name: "+text);
					System.out.println("Row Number: "+i);
					System.out.println("Column Number: "+j);
				}
			}
			
		}
	}
	
	public static void getProductAttributes() throws IOException {
		System.setProperty("webdriver.chrome.driver", "Library\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		WebDriverWait wait=new WebDriverWait(driver, 30);
		driver.get("http://demo.guru99.com/test/web-table-element.php#");
		WebElement test=driver.findElement(By.xpath("//table/tbody/tr[1]/td[2]"));
		wait.until(ExpectedConditions.visibilityOf(test));
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("screenshots/test.png"));
		List<WebElement> rows=driver.findElements(By.tagName("tr"));
		for(int i=1;i<rows.size();i++) {
			List<WebElement> columns=driver.findElements(By.xpath("//table/tbody/tr[1]/td"));
			String productName=driver.findElement(By.xpath("//table/tbody/tr["+i+"]/td[1]")).getText();
			if(productName.contains("Ltd"))
			for(int j=1;j<columns.size();j++) {
				String text=driver.findElement(By.xpath("//table/tbody/tr["+i+"]/td["+j+"]")).getText();
				System.out.print(text+" | ");
			}
			System.out.println();
	}
	}

}
