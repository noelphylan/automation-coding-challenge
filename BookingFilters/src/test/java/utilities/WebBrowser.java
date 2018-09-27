package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

	public class WebBrowser {
	public static WebDriver driver = null;
	public static final String browser = "Chrome";

	public static final String baseURL = "http://www.booking.com";
	
	public static WebDriver getDriver(){
		
		if (driver == null) {

			if (browser.equalsIgnoreCase("Chrome")){

				// has to be an absolute path
				System.setProperty("webdriver.chrome.driver","..\\BookingFilters\\Drivers64\\chromedriver.exe");

				driver = new ChromeDriver(); 
				
				driver.manage().window().maximize();
				driver.navigate().to(baseURL);
			}
			
			
		}

		return driver;
		
	}
}
	

