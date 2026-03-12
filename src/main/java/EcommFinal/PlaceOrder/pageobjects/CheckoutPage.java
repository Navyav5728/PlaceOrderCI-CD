package EcommFinal.PlaceOrder.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import EcommFinal.PlaceOrder.AbstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents {
	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	By result=By.cssSelector(".ta-results");
	
	@FindBy(css=".ta-item:nth-of-type(2)")
WebElement countrySelection;

@FindBy(css=".action__submit")
WebElement submit;
	public void selectCountry(String countryName) {
	Actions a = new Actions(driver);

	a.sendKeys(country,countryName).build().perform();
    waitforelementToAppear(result);
	
    countrySelection.click();
	
	}
public OrderConfirmationPage submitOrder() {
	submit.click();
	OrderConfirmationPage op=new OrderConfirmationPage(driver);
	return op;
}
	
}
