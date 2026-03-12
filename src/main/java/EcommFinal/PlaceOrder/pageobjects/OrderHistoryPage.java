package EcommFinal.PlaceOrder.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import EcommFinal.PlaceOrder.AbstractComponents.AbstractComponents;


public class OrderHistoryPage extends AbstractComponents{

	WebDriver driver;
	
	public OrderHistoryPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> orderProducts;
	
	
	

	
	
	
	
	
public Boolean verifyOrderDisplay(String productName) {
	Boolean match=orderProducts.stream().anyMatch(p->p.getText().equals(productName));
	return match;
	
}

}
