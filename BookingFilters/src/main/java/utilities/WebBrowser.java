package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.ie.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

	public class WebBrowser {
	public static WebDriver driver = null;
	public static final String browser = "Chrome";

	public static final String baseURL = "http://www.booking.com";
	
	public static WebDriver getDriver(){
		
		if (driver == null) {

			if (browser.equalsIgnoreCase("Chrome")){

				System.setProperty("webdriver.chrome.driver","..\\BookingFilters\\Drivers64\\chromedriver.exe");
				driver = new ChromeDriver(); 
				
				driver.manage().window().maximize();
				driver.navigate().to(baseURL);
			}
			
			
		}

		return driver;
		
	}
}
	

