package ui.or;

import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.ui.*;

import ui.constants.BookingConstants;
import utilities.WebBrowser;

import java.util.List;

import org.openqa.selenium.*;

public class BookingMainOR extends WebBrowser {

	@FindBy(id=BookingConstants.DESTINATION_INPUT_ID)
	public WebElement destinationInputField;
	
	@FindBy(xpath=BookingConstants.XPATH_SEARCH_BUTTON)
	public WebElement searchButton;
	
	@FindBy(xpath=BookingConstants.XPATH_CHECK_IN_DAY_MONTH_BUTTON)
	public WebElement checkInDayMonthButton;

	@FindBy(xpath=BookingConstants.XPATH_CAL_NAV_RIGHT)
	public List<WebElement> calNavRightEls;

	@FindBy(xpath=BookingConstants.XPATH_CAL_DAY)
	public List<WebElement> dayCellEls;
	
}   
