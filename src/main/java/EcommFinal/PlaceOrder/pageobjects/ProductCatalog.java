package EcommFinal.PlaceOrder.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import EcommFinal.PlaceOrder.AbstractComponents.AbstractComponents;

public class ProductCatalog extends AbstractComponents{
WebDriver driver;
public ProductCatalog(WebDriver driver) {
	super(driver);
	this.driver=driver;
	PageFactory.initElements(driver,this);
}


@FindBy(css=".card-body")
List<WebElement> products;
@FindBy(css=".ng-animating")
WebElement spinner;

By productsBy=By.cssSelector(".card-body");
By toastmsg=By.cssSelector("#toast-container");

By addToCart=By.cssSelector(".card-body button:last-of-type");

public List<WebElement> getProductList() {
	waitforelementToAppear(productsBy);
	return products;
}
public WebElement getProductByName(String productName) {
	WebElement prod=getProductList().stream().filter(s->s.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
	return prod;
}
public void addToCart(String productName) {
	WebElement prod=getProductByName(productName);
	
	prod.findElement(addToCart).click();
	waitforelementToAppear(toastmsg);
	waitforElementToDisapper(spinner);
	
}

}



