package standalone.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import EcommFinal.PlaceOrder.pageobjects.CartPage;
import EcommFinal.PlaceOrder.pageobjects.CheckoutPage;
import EcommFinal.PlaceOrder.pageobjects.OrderConfirmationPage;
import EcommFinal.PlaceOrder.pageobjects.OrderHistoryPage;
import EcommFinal.PlaceOrder.pageobjects.ProductCatalog;
import standalone.TestComponents.BaseTest;

public class submitorderTest extends BaseTest{
WebDriver driver;
//String productName="ZARA COAT 3";
	@Test(dataProvider="getData",groups={"purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException {
		
		String actualMsg="THANKYOU FOR THE ORDER.";
		// TODO Auto-generated method stub
ProductCatalog pd=landing.login(input.get("email"), input.get("password"));

List<WebElement>products=pd.getProductList();
pd.addToCart(input.get("productName"));
CartPage cartPage=pd.gotToCartPage();


Boolean match=cartPage.verifyProductList(input.get("productName"));
Assert.assertTrue(match);
CheckoutPage ch=cartPage.goToCheckout();
ch.selectCountry("India");
OrderConfirmationPage op=ch.submitOrder();
String Expectedconfmsg=op.confimmessage();
Assert.assertTrue(Expectedconfmsg.equalsIgnoreCase(actualMsg));

	}
@Test(dependsOnMethods={"submitOrder"})
public void OrderHistoryTest(HashMap<String,String> input) {
	ProductCatalog pd=landing.login(input.get("email"), input.get("password"));
	OrderHistoryPage order=pd.goToOrderHistoryPage();
	Assert.assertTrue(order.verifyOrderDisplay(input.get("productName")));
}


@DataProvider
public Object[][] getData() throws IOException {
	
	
	
	List<HashMap<String, String>> data=getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//standalone//data//PurchaseOrder.json");
	return new Object[][] {{data.get(0)},{data.get(1)}};
}

/*@DataProvider
public Object[][] getData() {
	return new Object[][] {{"navya.veerlanka@gmail.com", "Test@123","ZARA COAT 3"},{"testnowc@gmail.com","Test@123","ADIDAS ORIGINAL"}};
}*/

/*HashMap<String,String> map= new HashMap<String,String>();
map.put("email","navya.veerlanka@gmail.com");
map.put("password","Test@123");
map.put("productName","ZARA COAT 3");
HashMap<String,String> map1= new HashMap<String,String>();
map1.put("email","testnowc@gmail.com");
map1.put("password","Test@123");
map1.put("productName","ADIDAS ORIGINAL");
*/
}






	

