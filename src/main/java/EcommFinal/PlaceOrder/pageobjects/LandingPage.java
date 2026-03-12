package EcommFinal.PlaceOrder.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import EcommFinal.PlaceOrder.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents{
WebDriver driver;
public LandingPage(WebDriver driver) {
	super(driver);
	this.driver=driver;
	PageFactory.initElements(driver, this);
}


@FindBy(id="userEmail")
WebElement userEmail;

@FindBy(id="userPassword")
WebElement userPassword;

@FindBy(id="login")
WebElement login;

@FindBy(css="[class*='flyInOut']")
WebElement errorMessage;

public void goTo() {
	driver.get("https://rahulshettyacademy.com/client");
	
}
public ProductCatalog login(String email,String password) {
	userEmail.sendKeys(email);
	userPassword.sendKeys(password);
	login.click();
	ProductCatalog pd=new ProductCatalog(driver);
	return pd;
}

public String errorValidation() {
	waitforWebelementToAppear(errorMessage);
	String msg=errorMessage.getText();
	return msg;
}
private void waitforWebElementToAppera() {
	// TODO Auto-generated method stub
	
}
}
