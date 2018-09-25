package utilities;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;


public class GenericFunctions extends WebBrowser {
	
	private final static Logger LOGGER = 
			 Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	 
	public static void verifyPageTitle(String expectedTitle) {
		Assert.assertEquals(driver.getTitle(), expectedTitle);
	}

	public static void verifyPageTitleContains(String expectedTitle) {
		CharSequence cs = expectedTitle;
		Assert.assertTrue(driver.getTitle().contains(cs));
	}
	
	public static boolean isElementDisplayed(By locator) {		
		boolean isVisible = false;
		try {
			isVisible = driver.findElement(locator).isDisplayed();
		} catch (Exception e) {
			
		}
		return isVisible;
	}

	
	public static void clickAnyButtonByText(String textToClick) {
		driver.findElement(By.xpath("//button[text()='" + textToClick + "']")).click();
	}

	
	public static void clickAnyLinkByText(String linkToClick) {
		driver.findElement(By.xpath("//a/span[text()='" + linkToClick + "']")).click();
	}

	
	public static String getCurrentPageURL() {
		return driver.getCurrentUrl();
	}

	
	public static String getElementText(WebElement element) {
		return element.getText();
	}

	
	public static void mouseHover(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}
	
	public static void clickByJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", element); 
	}

	
	public static void selectNewWindow() {
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
	}

	
	public static WebElement getOptions(String id, int i) {
		WebElement selectElement = driver.findElement(By.id(id));
		Select select = new Select(selectElement);	
		List<WebElement> allOptions = select.getOptions(); 			
			
		return allOptions.get(i);
	}
	 
	 
	public static void selectOption(String id,int option) {		 
		 
		for(int i =0;i<option;i++) {
			driver.findElement(By.id(id)).sendKeys(Keys.ARROW_DOWN);		    	
		}
	}
	 
	public static void javascriptClick(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).click().build().perform();
	}
	
	
	public static String getCurrentPageTitle(){
		return driver.getTitle();
	}
	
	
	public static void sendData(String inputField, String inputData) {
		
		driver.findElement(By.id("//*[text()='" + inputField + "']")).sendKeys(inputData);
		
	}
	
	
	public static void hoverAndClick(String hover, String click) throws InterruptedException {

         WebElement webElementToHover = driver.findElement(By.linkText(hover));

         Actions action = new Actions(driver);
         action.moveToElement(webElementToHover).build().perform();

         Thread.sleep(2000);

         driver.findElement(By.linkText(click)).click();
         
     }
	 
	 public static void sleep(int milliseconds) throws InterruptedException {
		 
		 Thread.sleep(milliseconds);
		 
	}
	 
	 public static Boolean waitForElementToBeVisible( String Id, int seconds) {
         try {

        	 WebDriverWait wait = new WebDriverWait(driver,seconds);


        	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
             return true;
         }
         catch (NoSuchElementException e) {
             return false;
         }
     }
	 
	    
	    
	 public static void scrolldown() {
	    	driver.findElement(By.tagName("body")).sendKeys(Keys.PAGE_DOWN);
	 }
	    
	    
	 public static void clearField(String ID) {
	    	driver.findElement(By.id(ID)).sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE);
	 }
	 
	 public static void captureScreenshoot(WebDriver driver, String ScreenshootName) {
	 	try {
	 		TakesScreenshot ts = (TakesScreenshot)driver;
				
			File source = ts.getScreenshotAs(OutputType.FILE);
			
			String path = "..\\BookingFilter\\target\\Screenshots\\"+ScreenshootName+".png";
			File destination = new File(path);
			
			FileUtils.copyFile(source, destination);
			
			LOGGER.log(Level.INFO, "##########################\n\nScreenshot Taken\n\n##########################");
	
			} catch(Exception e) {			
				LOGGER.log(Level.INFO, "Exception taking Screenshot: "+e.getMessage());			
			}
			
		}
		

}
	


