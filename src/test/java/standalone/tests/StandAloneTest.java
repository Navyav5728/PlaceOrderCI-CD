package standalone.tests;

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

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		String productName="ZARA COAT 3";
		String actualMsg="THANKYOU FOR THE ORDER.";
		// TODO Auto-generated method stub
WebDriverManager.chromedriver().setup();
WebDriver driver=new ChromeDriver();
driver.manage().window().maximize();
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
driver.get("https://rahulshettyacademy.com/client");
driver.findElement(By.id("userEmail")).sendKeys("navya.veerlanka@gmail.com");
driver.findElement(By.id("userPassword")).sendKeys("Test@123");
driver.findElement(By.id("login")).click();
WebDriverWait w= new WebDriverWait(driver,Duration.ofSeconds(5));
w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".card-body")));
List<WebElement> products=driver.findElements(By.cssSelector(".card-body"));
WebElement product=products.stream().filter(s->s.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
product.findElement(By.cssSelector(".card-body button:last-of-type")).click();

w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
List<WebElement>
CartProducts=driver.findElements(By.cssSelector(".cartSection"));
Boolean match=CartProducts.stream().anyMatch(p->p.findElement(By.cssSelector("h3")).getText().equals(productName));
Assert.assertTrue(match);
driver.findElement(By.cssSelector(".totalRow button")).click();
Actions a = new Actions(driver);

WebElement country=driver.findElement(By.cssSelector("[placeholder='Select Country']"));
a.sendKeys(country,"ind").build().perform();

w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
driver.findElement(By.cssSelector(".action__submit")).click();
String Expectedconfmsg=driver.findElement(By.cssSelector(".hero-primary")).getText();
// cannot write Assert.assert equals because selenium checks only text present in Ui not html and the text is in caps, but expected will come in small 
//so keep o=in assert equals ignore case and wrap in assert true
Assert.assertTrue(Expectedconfmsg.equalsIgnoreCase(actualMsg));

/*driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("ind");
driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();*/


//product.findElement(By.xpath("//div[@class='card-body']/child::button[2]")).click();
/*for (int i=0;i<products.size();i++) {
	String g=products.get(i).getText();
	if(g.contains("ZARA COAT 3")) {
		driver.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	}
}*/


	}

}
