package standalone.TestComponents;

import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import EcommFinal.PlaceOrder.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landing;
	
	public WebDriver initializeDriver() throws IOException {
		Properties p=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//src//main//java//EcommFinal//PlaceOrder//resources//GlobalProperties.properties");
		p.load(fis);
		String browserName=System.getProperty("browser")!=null ? System.getProperty("browser"): p.getProperty("browser");
		//String browserName=p.getProperty("browser");
		if(browserName.contains("chrome")) {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options= new ChromeOptions();
		if(browserName.contains("headless")) {
		options.addArguments("headless");
		}
		driver=new ChromeDriver(options);
		driver.manage().window().setSize(new Dimension(1440,900));//help you to run in full screen
		
		}
		else if(browserName.contains("firefox")) {
			driver= new FirefoxDriver();
		}
        else if(browserName.contains("edge")) {
			
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		//read Json to string
	String jsonContent=	FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		//to convert json content to hasmap we need jackson binding dependency
		//String to hasmap
		ObjectMapper mapper=new ObjectMapper();
	List<HashMap<String,String>> data=	mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){
		
	});
	return data;
		
	}
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {
		
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File destination=new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source, destination);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
		
	}

	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		driver=initializeDriver();
		landing=new LandingPage(driver);
		landing.goTo();
		return landing;
	}
	@AfterMethod(alwaysRun=true)

	public void tearDown() {
		driver.close();
	}
}
