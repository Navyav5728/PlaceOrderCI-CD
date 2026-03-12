package standalone.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import EcommFinal.PlaceOrder.pageobjects.CartPage;
import EcommFinal.PlaceOrder.pageobjects.CheckoutPage;
import EcommFinal.PlaceOrder.pageobjects.LandingPage;
import EcommFinal.PlaceOrder.pageobjects.OrderConfirmationPage;
import EcommFinal.PlaceOrder.pageobjects.ProductCatalog;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import standalone.TestComponents.BaseTest;

public class StepDefinitions extends BaseTest{
LandingPage landing;
ProductCatalog pd;

OrderConfirmationPage op;
	@Given ("I landed on Ecommerce page")
       public void I_landed_on_Ecommerce_page() throws IOException {
		landing=launchApplication();
		
	}


	@Given ("^Logged in with username(.+) and password(.+)$")
	public void logged_in_with_username_and_password(String email,String password) {
		ProductCatalog pd=landing.login(email, password);
	}
	
	@When( "^I add product(.+) to cart$")
	public void I_add_product_to_cart(String productName) {
		List<WebElement>products=pd.getProductList();
		pd.addToCart(productName);
		
	}
	@When ("^Checkout (.+)and submit the order$")
	public void checkout_and_submit_the_order(String productName) {
		CartPage cartPage=pd.gotToCartPage();
		Boolean match=cartPage.verifyProductList(productName);
		Assert.assertTrue(match);
		CheckoutPage ch=cartPage.goToCheckout();
		ch.selectCountry("India");
		OrderConfirmationPage op=ch.submitOrder();
	}
	@Then ("{string} message is displayed on the confirmation page")
	public void message_displayed_on_confirmation_page(String string) {
		String Expectedconfmsg=op.confimmessage();
		Assert.assertTrue(Expectedconfmsg.equalsIgnoreCase(string));
		driver.close();
	}
}
