package week4.day2.assignments;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class ArchitectCertification {
	
	public static void main(String[] args) throws InterruptedException, IOException {
	
			WebDriverManager.chromedriver().setup();
			
			ChromeOptions options = new ChromeOptions();
			
	        options.addArguments("--disable-notifications");
	        
	        ChromeDriver driver = new ChromeDriver(options);
	        
			driver.get("https://login.salesforce.com/");
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			
			driver.manage().window().maximize();
			
			driver.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com");
			
			driver.findElement(By.id("password")).sendKeys("Password#123");		
			
			driver.findElement(By.id("Login")).click();
			
			driver.findElement(By.xpath("//span[text()='Learn More']")).click();
			
			Set<String> windowHandles = driver.getWindowHandles();
			
			System.out.println(windowHandles);
			
			Thread.sleep(3000);

			List<String> lstWin=new ArrayList<String>(windowHandles);
			
			driver.switchTo().window(lstWin.get(1));
			
			driver.findElement(By.xpath("//button[text()='Confirm']")).click();
			
			Shadow dom=new Shadow(driver);
			
			dom.findElementByXPath("//span[text()='Learning']").click();

			Thread.sleep(3000);
			
			Actions builder = new Actions(driver);
			
			WebElement trailHead = dom.findElementByXPath("//span[text()='Learning on Trailhead']");
			
			builder.moveToElement(trailHead).perform();

			builder.scrollToElement(trailHead).perform();		

			WebElement salesCertifi = dom.findElementByXPath("//a[text()='Salesforce Certification']");		
			
			driver.executeScript("arguments[0].click();", salesCertifi);	

		
			driver.findElement(By.xpath("//img[@alt='Salesforce<br/>Architect']")).click();

		 
			String salesForceText = driver.findElement(By.xpath("//h1[text()='Salesforce Architect']/following-sibling::div")).getText();
			System.out.println(salesForceText);
			
			
			
			List<WebElement> nameCerti = driver.findElements(By.xpath("//div[@class='credentials-card_title']"));
			
			for(WebElement name:nameCerti)
			{
				System.out.println(name.getText());
			}
			driver.findElement(By.linkText("Application Architect")).click();
			
			List<WebElement> archiCerti = driver.findElements(By.xpath("//div[@class='credentials-card_title']"));
			
			System.out.println("Architect certifications");
			
			for(int i=0; i<4; i++)
			{
				System.out.println(archiCerti.get(i).getText());
			}
			
			driver.quit();
			
		}

}
