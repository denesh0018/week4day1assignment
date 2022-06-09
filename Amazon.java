package week4day1assignment;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {

	public static void main(String[] args) throws IOException, InterruptedException {
		//Setup Chrome Driver
		WebDriverManager.chromedriver().setup();

		//open the browser
		ChromeDriver driver= new ChromeDriver();

		// Load the URL https://www.amazon.in/
		driver.get("https://www.amazon.in/");

		//Maximime the window screen
		driver.manage().window().maximize();

		//Implicit Wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));

		//search as oneplus 9 pro 
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("oneplus 9 pro");
		driver.findElement(By.xpath("//input[@id='nav-search-submit-button']")).click();
		//Get the price of the first product
		String text1 = driver.findElement(By.xpath("(//span[@class='a-price-whole'])[1]")).getText();
		System.out.println(text1);

		//Thread.sleep(3000);
		//					4. Print the number of customer ratings for the first displayed product
		String text2 = driver.findElement(By.xpath("(//a/span[@class='a-size-base s-underline-text'])[1]")).getText();
		Thread.sleep(3000);
		System.out.println(text2);
		//					5. Click the first text link of the first image
		driver.findElement(By.xpath("(//a[@class='a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal'])[1]")).click();
		//					6. Take a screen shot of the product displayed
		File snap = driver.getScreenshotAs(OutputType.FILE);
		File copy= new File("./displya.png");
		FileUtils.copyFile(snap, copy);
		//					7. Click 'Add to Cart' button
		Set<String> a = driver.getWindowHandles();
		List<String> b=new ArrayList<String>(a);
		driver.switchTo().window(b.get(1));

		driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
		//					8. Get the cart subtotal and verify if it is correct.
		String text3 = driver.findElement(By.xpath("//b[contains(text(),'Cart subtotal')]/following::span/span")).getText();
		System.out.println(text3);
		Thread.sleep(3000);
		if(text3.contains(text1))
		{
			System.out.println("Correct Value");
		}
		else {
			System.out.println("Wrong Value");
		}
		//					9.close the browser
		driver.quit();
	}

}
