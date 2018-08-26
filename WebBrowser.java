package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.ie.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

	public class WebBrowser {
	public static WebDriver driver = null;
	public static final String BROWSER = "Chrome";

	public static final String BASE_URL = "http://www.booking.com";
	
	public static WebDriver getDriver(){
		
		if (driver == null) {

			if (BROWSER.equalsIgnoreCase("Chrome")){

				System.setProperty("webdriver.chrome.driver","..\\BookingFilters\\Drivers64\\chromedriver.exe");
				driver = new ChromeDriver(); 
				
				driver.manage().window().maximize();
				driver.navigate().to(BASE_URL);
			}
			
			
		}

	return driver;
		
	}
}
	

