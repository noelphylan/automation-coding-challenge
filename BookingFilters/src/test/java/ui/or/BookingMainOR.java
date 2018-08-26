package ui.or;

import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.ui.*;

import ui.constants.BookingConstants;
import utilities.WebBrowser;

import java.util.List;

import org.openqa.selenium.*;

public class BookingMainOR extends WebBrowser {

	@FindBy(id="ss")
	public WebElement destinationInputField;
	
	@FindBy(xpath=".//button[@data-sb-id='main']")
	public WebElement searchButton;
	
	@FindBy(xpath=".//*[@id='frm']/div[1]/div[2]/div/div[2]/div/div/div/div[1]/div/button")
	public WebElement checkInDayMonthButton;

	@FindBy(xpath=BookingConstants.XPATH_CAL_NAV_RIGHT)
	public List<WebElement> calNavRightEls;

	@FindBy(xpath=BookingConstants.XPATH_CAL_DAY)
	public List<WebElement> dayCellEls;

	@FindBy(xpath=".//*[@id='frm']/div[1]/div[2]/div/div[2]/div/div/div/div[2]/div[2]/div[3]/div/div/div[4]/table/tbody/tr[4]/td[6]/span")
	public WebElement checkInDayCell;

	@FindBy(xpath=".//*[@id='frm']/div[1]/div[2]/div/div[3]/div/div/div/div[2]/div[2]/div[3]/div/div/div[4]/table/tbody/tr[4]/td[7]/span")
	public WebElement checkoutDayCell;
	
}   
