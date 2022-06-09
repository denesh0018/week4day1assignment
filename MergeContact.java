package week4day1assignment;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact {

	public static void main(String[] args) throws InterruptedException {
		//Setup Chrome Driver
		WebDriverManager.chromedriver().setup();

		//open the browser
		ChromeDriver driver= new ChromeDriver();

		// 1. Launch URL "http://leaftaps.com/opentaps/control/login"
		driver.get("http://leaftaps.com/opentaps/control/login");

		//Maximime the window screen
		driver.manage().window().maximize();
		
		//Implicit Wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
		
		//Attribute xPath // Enter UserName and Password Using Id Locator
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("DemoSalesManager");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("crmsfa");
		
		//Click on Login Button using Class Locator
		driver.findElement(By.xpath("//input[@class='decorativeSubmit']")).click();
		
		//Partial Text based xPath  // Click on CRM/SFA Link
		driver.findElement(By.xpath("//a[contains(text(),'CRM/SFA')]")).click();
		
		// Click on contacts Button
		driver.findElement(By.xpath("//a[contains(text(),'Contacts')]")).click();

		//Click on Merge Contacts using Xpath Locator
		driver.findElement(By.xpath("//a[contains(text(),'Merge Contacts')]")).click(); 
		// Click on Widget of From Contact
		driver.findElement(By.xpath("(//img[@alt='Lookup'])[1]")).click();

		Set<String> winnew = driver.getWindowHandles();

		List<String> win= new ArrayList<String>(winnew);

		driver.switchTo().window(win.get(1));

		//  Click on First Resulting Contact
		String text = driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[1]")).getText();

		System.out.println(text);

		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[1]")).click();

		//	back to primary window
		driver.switchTo().window(win.get(0));

		//  Click on Widget of To Contact
		driver.findElement(By.xpath("//table[@id='widget_ComboBox_partyIdTo']/following-sibling::a/img")).click();

		//  Click on Second Resulting Contact
		Set<String> winnew1 = driver.getWindowHandles();
		List<String> win1= new ArrayList<String>(winnew1);
		driver.switchTo().window(win1.get(1));

		String text2 = driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId'])[2]/a")).getText();
		System.out.println(text2);

		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId'])[2]/a")).click();
		////	back to primary window
		driver.switchTo().window(win1.get(0));

		// Click on Merge button using Xpath Locator
		driver.findElement(By.xpath("//a[contains(text(),'Merge') and (@class='buttonDangerous')]")).click();

		//Wait
		Thread.sleep(3000);
		
		//  Accept the Alert
		Alert alr = driver.switchTo().alert();
		alr.accept();

		// Verify the title of the page
		System.out.println(driver.getTitle());

	}

}
