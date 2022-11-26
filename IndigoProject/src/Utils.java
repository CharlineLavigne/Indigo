import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {
	
	public static WebDriver driver;
	
	
	public static WebDriver openBrowser(String url) {
		System.setProperty("webdriver.chrome.driver", "/Users/charlinelavigne/Indigo/chromedriver");
		driver = new ChromeDriver();
		driver.navigate().to(url);
		driver.manage().window().maximize();
		System.out.println("Website's URL is " + driver.getCurrentUrl());
		return driver;
	}
	
	
	public static WebElement waitForElement(String xpathValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.pollingEvery(Duration.ofSeconds(2));
		return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathValue)));
	}
	
	
	public static void scrollDownThePage(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scroll(0,200)");
	}
	
	
	public static void closeBrowser() {
		driver.close();
	}
}
