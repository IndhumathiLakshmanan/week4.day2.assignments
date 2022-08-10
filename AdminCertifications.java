package week4.day2.assignments;

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

public class AdminCertifications {

	public static void main(String[] args) throws InterruptedException {

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
		
		List<String> lsWindowHandles = new ArrayList<String>(windowHandles);	
		
		driver.switchTo().window(lsWindowHandles.get(1));
		
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
		
		driver.findElement(By.linkText("Administrator")).click();
		
		List<WebElement> certifications = driver.findElements(By.xpath("(//div[@class='trailMix-card-body']/div[2])/a"));
		
		System.out.println(certifications.size());
		
		for (WebElement webElement : certifications) {
			
			String text = webElement.getText();
			
			System.out.println(text);
		}

	}


}
