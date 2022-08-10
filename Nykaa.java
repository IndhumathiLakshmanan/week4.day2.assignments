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
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();

		ChromeOptions options = new ChromeOptions();

		options.addArguments("--disable-notifications");

		ChromeDriver driver = new ChromeDriver(options);

		driver.get("https://www.nykaa.com/");

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		Actions builder = new Actions(driver);

		Thread.sleep(3000);

		WebElement eleBrands = driver.findElement(By.xpath("//a[text()='brands']"));

		builder.moveToElement(eleBrands).perform();

		builder.scrollToElement(eleBrands).perform();

		driver.findElement(By.xpath("//div[@id='scroller-container']/div[7]/a[1]")).click();

		Thread.sleep(3000);

		String brandName = driver.findElement(By.xpath("//ul[@class='css-1uxnb1o']/li[@class='last-list css-vnn8hz']")).getText();
		System.out.println(brandName);

		driver.findElement(By.xpath("//button[@class=' css-n0ptfk']")).click();

		driver.findElement(By.xpath("(//div[@class='control-indicator radio '])[3]")).click();

		Thread.sleep(3000);

		driver.findElement(By.xpath("//span[text()='Category']")).click();

		driver.findElement(By.xpath("//span[text()='Hair']")).click();

		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();

		driver.findElement(By.xpath("//span[text()='Shampoo']")).click();

		driver.findElement(By.xpath("//span[text()='Concern']")).click();

		driver.findElement(By.xpath("//span[text()='Color Protection']")).click();

		String text = driver.findElement(By.xpath("(//div[@class='css-rtde4j']/div)[2]")).getText();

		if(text.equals("Color Protection")) {

			System.out.println("Color Protection Filter Applied");
		}
		else {
			System.out.println("Color Protection Filter Not Applied");
		}

		driver.findElement(By.xpath("//div[contains(text(),'Colour Protect Shampoo')]")).click();

		Set<String> windowHandles = driver.getWindowHandles();

		List<String> lsWindowHandles = new ArrayList<String>(windowHandles);

		driver.switchTo().window(lsWindowHandles.get(1));

		WebElement dropDown1 = driver.findElement(By.xpath("//select[@title='SIZE']"));

		Select select = new Select(dropDown1);

		select.selectByVisibleText("175ml");

		String price = driver.findElement(By.className("css-1jczs19")).getText();		

		System.out.println(price);

		driver.findElement(By.xpath("//span[text()='Add to Bag']")).click();
		
		driver.findElement(By.xpath("//div[@class='css-0 e1ewpqpu1']")).click();
		
		Thread.sleep(3000);
		
		WebElement frameElement = driver.findElement(By.xpath("//iframe[@class='css-acpm4k']"));
		
		driver.switchTo().frame(frameElement);
		
		String grandTotal = driver.findElement(By.xpath("(//div[@class='value medium-strong'])[1]")).getText();
		
		System.out.println(grandTotal.replace('?', ' '));
		
		driver.findElement(By.xpath("//span[text()='Proceed']")).click();
		
		driver.switchTo().window(lsWindowHandles.get(1));
			
		driver.findElement(By.xpath("//button[@class='btn full big']")).click();

		String total = driver.findElement(By.xpath("//div[@class='payment-details-tbl grand-total-cell prl20']/div[@class='value']")).getText();
		
		System.out.println(total.replace('?', ' '));		
		
		if(grandTotal.equals(total)) {
			
			System.out.println("Grand Total Verified");
		}
		else {
			System.out.println("Grand Total Not the same");
		}
		
		driver.quit();
	}
}
