package ui.or;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import ui.constants.BookingConstants;
import utilities.WebBrowser;

public class BookingResultsOR extends WebBrowser {

	@FindBy(xpath=BookingConstants.XPATH_5_STAR_FILTER_LINK)
    public WebElement fiveStarCheckBox;

    @FindBy(xpath=BookingConstants.XPATH_SAUNA_FILTER_LINK)
    public WebElement saunaCheckBox;
    
    @FindBy(xpath=BookingConstants.XPATH_HOTEL_FILTER_SPANS)
    public List<WebElement> hotelResultSpans;

}
