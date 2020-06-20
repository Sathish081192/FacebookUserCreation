package utils;

import java.io.File;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.NewSessionPayload;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserCreationinFacebook {

	public static void main(String[] args) throws EncryptedDocumentException, IOException, InterruptedException {
		// TODO Auto-generated method stub
		UserCreationinFacebook.createUser();
		//apache poi jar 4.1.2 download for eclipse

	}
	
	public static void createUser() throws EncryptedDocumentException, IOException, InterruptedException {
		System.setProperty("webdriver.chrome.driver", "Library\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		WebDriverWait wait=new WebDriverWait(driver, 30);
		driver.get("https://www.facebook.com/r.php");
		WebElement firstName=driver.findElement(By.xpath("//input[@name='firstname']"));
		WebElement surName=driver.findElement(By.xpath("//input[@name='lastname']"));
		WebElement mobileNumber=driver.findElement(By.xpath("//input[@name='reg_email__']"));
		WebElement password=driver.findElement(By.xpath("//input[@name='reg_passwd__']"));
		Select dateDropdown=new Select(driver.findElement(By.xpath("//select[@name='birthday_day']")));
		Select monthDropdown=new Select(driver.findElement(By.xpath("//select[@name='birthday_month']")));
		Select yearDropdown=new Select(driver.findElement(By.xpath("//select[@name='birthday_year']")));
		WebElement submitBtn=driver.findElement(By.xpath("//button[@name='websubmit']"));		
		
		
		Workbook wb=WorkbookFactory.create(new File("C:\\Users\\sathi\\Documents\\FacebookUserCreation.xlsx"));
		Sheet sh=wb.getSheet("TestUsers");
		int rowlength=sh.getLastRowNum();
		int columnLength=sh.getRow(0).getLastCellNum();
		for(int i=1;i<=rowlength;i++) {
			for(int j=0;j<columnLength;j++) {
				if(sh.getRow(0).getCell(j).getStringCellValue().equals("FirstName")) {
					try {
					firstName.sendKeys(sh.getRow(i).getCell(j).getStringCellValue());
					}
					catch(StaleElementReferenceException e) {
						firstName=driver.findElement(By.xpath("//input[@name='firstname']"));
						firstName.sendKeys(sh.getRow(i).getCell(j).getStringCellValue());
					}					
				}
				else if(sh.getRow(0).getCell(j).getStringCellValue().equals("Surname")) {
					try {
					surName.sendKeys(sh.getRow(i).getCell(j).getStringCellValue());
					}
					catch(StaleElementReferenceException e) {
						surName=driver.findElement(By.xpath("//input[@name='lastname']"));
						surName.sendKeys(sh.getRow(i).getCell(j).getStringCellValue());
					}
				}
				else if(sh.getRow(0).getCell(j).getStringCellValue().equals("MobileNumber")) {
					try {
					mobileNumber.sendKeys(sh.getRow(i).getCell(j).getStringCellValue());
					}
					catch(StaleElementReferenceException e) {
						mobileNumber=driver.findElement(By.xpath("//input[@name='reg_email__']"));
						mobileNumber.sendKeys(sh.getRow(i).getCell(j).getStringCellValue());
						
					}
				}
				else if(sh.getRow(0).getCell(j).getStringCellValue().equals("NewPassword")) {
					try {
					password.sendKeys(sh.getRow(i).getCell(j).getStringCellValue());
					}
					catch(StaleElementReferenceException e) {
						password=driver.findElement(By.xpath("//input[@name='reg_passwd__']"));
						password.sendKeys(sh.getRow(i).getCell(j).getStringCellValue());
					}
				}
				else if(sh.getRow(0).getCell(j).getStringCellValue().equals("Date")) {
					try {
					dateDropdown.selectByVisibleText(sh.getRow(i).getCell(j).getStringCellValue());
					}
					catch(StaleElementReferenceException e) {
						dateDropdown=new Select(driver.findElement(By.xpath("//select[@name='birthday_day']")));
						dateDropdown.selectByVisibleText(sh.getRow(i).getCell(j).getStringCellValue());
					}
				}
				else if(sh.getRow(0).getCell(j).getStringCellValue().equals("Month")) {
					try {
					monthDropdown.selectByVisibleText(sh.getRow(i).getCell(j).getStringCellValue());
					}
					catch(StaleElementReferenceException e) {
						monthDropdown=new Select(driver.findElement(By.xpath("//select[@name='birthday_month']")));
						monthDropdown.selectByVisibleText(sh.getRow(i).getCell(j).getStringCellValue());
					}
				}
				else if(sh.getRow(0).getCell(j).getStringCellValue().equals("Year")) {
					try{
						yearDropdown.selectByVisibleText(sh.getRow(i).getCell(j).getStringCellValue());
					}
					catch(StaleElementReferenceException e) {
						yearDropdown=new Select(driver.findElement(By.xpath("//select[@name='birthday_year']")));
						yearDropdown.selectByVisibleText(sh.getRow(i).getCell(j).getStringCellValue());
					}
				}
				else if(sh.getRow(0).getCell(j).getStringCellValue().equals("Gender")) {
					String genderString=sh.getRow(i).getCell(j).getStringCellValue();
					driver.findElement(By.xpath("//label[contains(text(),'"+genderString+"')]//preceding-sibling::input")).click();;
				}
				
				
			}
			try {
			submitBtn.click();
			
			}
			catch(StaleElementReferenceException e) {
				submitBtn=driver.findElement(By.xpath("//button[@name='websubmit']"));
				submitBtn.click();
			}
			Thread.sleep(5000);
			WebElement resultElement=driver.findElement(By.xpath("//div[@class='_1yqt']"));
			System.out.println(resultElement.getText()+i);
			driver.findElement(By.xpath("//a[contains(text(),'Log Out')]")).click();
			Thread.sleep(3000);
			driver.get("https://www.facebook.com/r.php");
			Thread.sleep(5000);
		}
		
		
		
		/*Workbook wb=WorkbookFactory.create(new File("C:\\Users\\sathi\\Documents\\JIRAAPI.xlsx"));
		org.apache.poi.ss.usermodel.Sheet sh=wb.getSheet("BUGCREATION");
		int rowlength=sh.getLastRowNum();
		int collength=sh.getRow(0).getLastCellNum();*/
		
		
		
	}

}
