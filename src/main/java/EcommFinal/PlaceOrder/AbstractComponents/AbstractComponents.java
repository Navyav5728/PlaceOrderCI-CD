package EcommFinal.PlaceOrder.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import EcommFinal.PlaceOrder.pageobjects.CartPage;
import EcommFinal.PlaceOrder.pageobjects.OrderHistoryPage;

public class AbstractComponents {
	
	WebDriver driver;
	
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement myOrder;
	By cartClick=By.cssSelector(".cartSection h3");
	
	public AbstractComponents(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public void waitforelementToAppear(By findBy) {
		WebDriverWait w= new WebDriverWait(driver,Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		
	}
	public void waitforWebelementToAppear(WebElement ele1) {
		WebDriverWait w= new WebDriverWait(driver,Duration.ofSeconds(10));
		w.until(ExpectedConditions.visibilityOf(ele1));
		
	}

public void waitforElementToDisapper(WebElement ele) {
	WebDriverWait w= new WebDriverWait(driver,Duration.ofSeconds(5));
	w.until(ExpectedConditions.invisibilityOf(ele));
}
public CartPage gotToCartPage() {
	cartHeader.click();
//	waitforelementToAppear(cartClick);
	CartPage cartPage=new CartPage(driver);
	return cartPage;
}

public OrderHistoryPage goToOrderHistoryPage() {
	myOrder.click();
	OrderHistoryPage order=new OrderHistoryPage(driver);
	return order;
}
}
