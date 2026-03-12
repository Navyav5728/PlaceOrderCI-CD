package EcommFinal.PlaceOrder.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import EcommFinal.PlaceOrder.AbstractComponents.AbstractComponents;


public class CartPage extends AbstractComponents{

	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css=".cartSection")
	List<WebElement> cartProducts;
	
	By cartProductBy=By.cssSelector("h3");
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	
	
	/*public List<WebElement> getCartProductList() {
		 return cartProducts;
	}*/
	
public Boolean verifyProductList(String productName) {
	Boolean match=cartProducts.stream().anyMatch(p->p.findElement(cartProductBy).getText().equals(productName));
	return match;
	
}
public CheckoutPage goToCheckout() {
checkoutEle.click();
CheckoutPage ch=new CheckoutPage(driver);
return ch;
}
}
