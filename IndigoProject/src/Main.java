import java.awt.AWTException;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		IndigoHomePage.closeMailPopup();
		//IndigoHomePage.loginAccount();
		IndigoHomePage.searchSubcategoryByAvailability();
		IndigoHomePage.searchProduct();
		IndigoHomePage.addProductToCart();
		Utils.closeBrowser();
	}
}
