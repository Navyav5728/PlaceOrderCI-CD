package standalone.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import EcommFinal.PlaceOrder.pageobjects.CartPage;
import EcommFinal.PlaceOrder.pageobjects.CheckoutPage;
import EcommFinal.PlaceOrder.pageobjects.LandingPage;
import EcommFinal.PlaceOrder.pageobjects.OrderConfirmationPage;
import EcommFinal.PlaceOrder.pageobjects.ProductCatalog;
import io.github.bonigarcia.wdm.WebDriverManager;
import standalone.TestComponents.BaseTest;
import standalone.TestComponents.Retry;

public class ErrorValidationsTest extends BaseTest{
WebDriver driver;
	@Test(groups={"ErrorHandling"},retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException {
		
   landing.login("navya.veerlana@gmail.com", "Test@123");

Assert.assertEquals(landing.errorValidation(),"Incorrect email or password.");

	

	}
	@Test(groups={"ErrorHandling"})
	public void productErrorValidation() throws IOException {
		String productName="ZARA COAT 3";

		// TODO Auto-generated method stub
ProductCatalog pd=landing.login("navya.veerlanka@gmail.com", "Test@123");

List<WebElement>products=pd.getProductList();
pd.addToCart(productName);
CartPage cartPage=pd.gotToCartPage();


Boolean match=cartPage.verifyProductList("ZARA COAT 33");
Assert.assertFalse(match);

}
	
	
	
}