import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.opentelemetry.exporter.logging.SystemOutLogExporter;

public class IndigoHomePage {
	
	public static WebDriver driver = Utils.openBrowser("https://www.chapters.indigo.ca/en-ca/");

	
	//Mail popup can be closed when reaching home page
	public static void closeMailPopup() {
		WebElement popupCloseBtn;
		popupCloseBtn = driver.findElement(By.className("browsepopup-closebtn"));
		popupCloseBtn.click();
	}
	
	
	//User is able to login in his account
	public static void loginAccount() throws InterruptedException {
		WebElement accountBtn, signBtn, emailInput, passwordInput, loginBtn;
		
		accountBtn = driver.findElement(By.xpath("//button[@aria-label='Your Account']"));
		signBtn = driver.findElement(By.xpath("//button[@title='sign in']"));

		accountBtn.click();
		signBtn.click();
		
		Thread.sleep(1000);
		emailInput = driver.findElement(By.id("browseCart-signInPopup-emailTextbox"));
		passwordInput = driver.findElement(By.id("browseCart-signInPopup-passwordTextbox"));
		loginBtn = driver.findElement(By.cssSelector("div#browseCart-signInPopup-signInFields button"));
		
		emailInput.click();
		emailInput.sendKeys("qat055848@gmail.com");
		passwordInput.click();
		passwordInput.sendKeys("GoldenAcht123");
		loginBtn.click();
	}
	
	
	//User can access to a subcategory of a product from the home page and sort the products by availability
	public static void searchSubcategoryByAvailability() {
		WebElement bookNavBtn, scienceCategoryLink, cboMatches, cboAvailability;
		Actions actions = new Actions(driver);
		
		bookNavBtn = driver.findElement(By.xpath("//li[@data-menuid='28295']"));
		actions.moveToElement(bookNavBtn).build().perform();
		
		scienceCategoryLink = Utils.waitForElement("//ul[@id='child3_1']/li[7]");
		scienceCategoryLink.click();	
		
		cboMatches = driver.findElement(By.className("Select-value-label"));
		cboMatches.click();
		actions.sendKeys(Keys.chord(Keys.DOWN, Keys.ENTER)).perform();
	}
	
	
	//User can search products in the search box and change the view type
	public static void searchProduct() {
		WebElement searchBox, searchBtn, viewBtn, resultCount;
		List <WebElement> productsDisplayed;
		
		searchBox = driver.findElement(By.id("header__quick-search"));
		searchBox.sendKeys("Lovecraft");
		
		searchBtn = Utils.waitForElement("//button[@class='quick-search__submit']");
		searchBtn.click();
		
		viewBtn = Utils.waitForElement("//button[@class='results-view-toggle__toggle-list']");
		viewBtn.click();
		
		resultCount = driver.findElement(By.cssSelector("div#resultSummary span"));
		String [] resultText = resultCount.getText().split(" ");
		System.out.println(resultText[0] + " results found for " + driver.findElement(By.className("keyword-heading")).getText().toLowerCase());
		
		productsDisplayed = driver.findElements(By.cssSelector("a.product-list__product-title"));
		int count = 1;
		for(WebElement product : productsDisplayed) {
			System.out.println("Product " + count + " is: " + product.getAttribute("title"));
			count++;
		}
	}
	
	
	//User is able to scroll down in the page results, select a quantity of a product and add it to the shopping cart
	public static void addProductToCart() {
		WebElement productChoosed, quantitySelected, btnAddProduct;
		Utils.scrollDownThePage(driver);
		
		productChoosed = driver.findElement(By.xpath("//div[@data-id='978057508157'] //a[1]"));
		productChoosed.click();
		
		quantitySelected = Utils.waitForElement("//select[@name='quantity-select']");
		Select quantitySelectedList = new Select(quantitySelected);
		quantitySelectedList.selectByVisibleText("2");
		
		btnAddProduct = driver.findElement(By.xpath("//button[@class='common-button async-button__button common-button--full-width']"));
		btnAddProduct.click();
	}
}
