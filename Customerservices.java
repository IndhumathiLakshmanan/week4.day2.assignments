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
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class Customerservices {

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
		
		Set<String> newWindow = driver.getWindowHandles();
		
		System.out.println(newWindow);
		
		Thread.sleep(3000);

		List<String> lstWndw = new ArrayList<String> (newWindow);

		driver.switchTo().window(lstWndw.get(1));

		driver.findElement(By.xpath("//button[text()='Confirm']")).click();

		Shadow dom = new Shadow (driver);

		dom.findElementByXPath("//span[text()='Products']").click();

		dom.findElementByXPath("//span[text()='Service']").click();

		dom.findElementByXPath("//a[text()='Customer Service']").click();

		System.out.println(driver.getTitle());

		List<WebElement> services = driver.findElements(By.xpath("//ul[@class='page-list page-list-level-2']/li/a"));

		System.out.println("The number of services available are : " + services.size());
		
		for(WebElement serviceName:services)
		{
			System.out.println(serviceName.getText());
		}

		System.out.println("Page title -- "+driver.getTitle());

		driver.quit();
	}
}
