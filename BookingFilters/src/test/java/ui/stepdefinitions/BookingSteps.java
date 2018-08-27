package ui.stepdefinitions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.testng.Assert;

import ui.constants.BookingConstants;
import ui.pageaction.BookingMainPage;
import ui.pageaction.BookingResultsPage;
import utilities.GenericFunctions;
import utilities.WebBrowser;

public class BookingSteps {
	
	private final static Logger LOGGER = 
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	WebDriver driver;	
	BookingMainPage bookingMainPage;
	BookingResultsPage bookingResultsPage;
	List<String> hotelResultsList;

	public BookingSteps() {
		this.driver = WebBrowser.getDriver();
	
		bookingMainPage = new BookingMainPage(driver);
		bookingResultsPage = new BookingResultsPage(driver);
	}

	
	@Given("^I am on Booking Home Page$")
	public void i_am_on_booking_homepage() throws Throwable {
		GenericFunctions.verifyPageTitleContains(BookingConstants.BOOKING_TITLE);
	}
	
	@When("^I enter \"([^\"]*)\" in the Destination input field$")
	public void when_i_enter_destination(String destination) throws Throwable {
		
		bookingMainPage.destinationInputField.sendKeys(destination);
		
		LOGGER.log(Level.INFO, "Destination entered");
	}
	
	
	@When("^I click on the CheckIn icon$")
	public void i_click_on_checkin_icon() throws Throwable {
		
		bookingMainPage.checkInDayMonthButton.click();
		
		LOGGER.log(Level.INFO, "checkInDayMonthButton clicked");

	}
	
	@When("^I select a check in date 3 months from today for 1 night$")
	public void i_select_a_checkin_date_3_months_from_today_for_1_night() throws Throwable {
		
		Calendar cal = Calendar.getInstance(); 
		cal.add(Calendar.MONTH, 3);
		
		Date checkInDate = cal.getTime();		
		
		selectCalendarDate(checkInDate);

	}	
	
	public void selectCalendarDate(Date bookingDate) throws Throwable {
		
		// the navRight chevron will advance each month, so determine check in month
		// add 1 to the get a non zero index month
		Calendar bookingCal = new GregorianCalendar();
		bookingCal.setTime(bookingDate);		
		
		int checkInMonth = bookingCal.get(Calendar.MONTH)+1;
		
		Calendar currentCal = new GregorianCalendar();
		int currentMonth = currentCal.get(Calendar.MONTH)+1;;

		int monthsToAdvance = checkInMonth-currentMonth;
		// if this is a negative number then it is in the next calendar year
		// find out how many months until end of year and add the absolute value of monthsToAdvance to it
		if (monthsToAdvance < 0) {
			int monthsToEndOfYear = 12-currentMonth;
			monthsToAdvance = Math.abs(monthsToAdvance)+monthsToEndOfYear;
		}
		
		for (int i=0;i<monthsToAdvance;i++) {
			
			List<WebElement> xpathcalNavRightEls = bookingMainPage.calNavRightEls;
			
			for (WebElement el:xpathcalNavRightEls) {
				if (el.getText().equals(BookingConstants.NAV_RIGHT_SYMBOL)) {
					el.click();
				}
			}
			
		}
		
		int checkInDay = bookingCal.get(Calendar.DATE);
		
		List<WebElement> dayCells = bookingMainPage.dayCellEls;

		// now click on the checkin date,
		for(int i=0;i<dayCells.size();i++) {
			try {
				WebElement spanCell = dayCells.get(i);
				if (spanCell.getText().equals(new Integer(checkInDay).toString())) {
					spanCell.click();
					break;
				}
			} catch (Exception err) {
				err.printStackTrace();
			}
		}
		
		// now click on the checkout date, 1 day later
		for (WebElement spanCell:dayCells) {
			try {
				if (spanCell.getText().equals(new Integer(checkInDay+1).toString())) {
					spanCell.click();
					break;
				}
			} catch (Exception err) {
				
				err.printStackTrace();
			}
		}
		
		
		LOGGER.log(Level.INFO, "calendar date clicked");

	}
	
	@When("^I click on the Search button$")
	public void i_click_on_the_search_button() throws Throwable {
		bookingMainPage.searchButton.click();
		GenericFunctions.waitForElementToBeVisible("filterbox_options", 5);
		LOGGER.log(Level.INFO, "Search button clicked");
		GenericFunctions.sleep(5000);
	}
	
	@When("^I click on the 5 star filter$")
	public void i_click_on_the_5_star_filter() throws Throwable {
		LOGGER.log(Level.INFO, "entering 5 star filter method");
		
		storeOriginalHotelResults();
		
		//close the survey no button if it appears
		try {
			bookingResultsPage.surveyNoButton.click();
		} catch (Exception err) {
			err.printStackTrace();
		}
		
		bookingResultsPage.fiveStarCheckBox.click();

		GenericFunctions.sleep(3000);
			
	}
	
	private void storeOriginalHotelResults() {
		List<WebElement> hotelSpans = bookingResultsPage.hotelResultSpans;
		
		hotelResultsList = new ArrayList<>();
		for (WebElement hotelSpan:hotelSpans) {
	
			// store the hotel results list
			if (!hotelSpan.getText().trim().equals("")) {
				hotelResultsList.add(hotelSpan.getText().trim());
			}
		}
	}
	
	@When("^I click on the Sauna filter$")
	public void i_click_on_the_sauna_filter() throws Throwable {
		LOGGER.log(Level.INFO, "entering sauna filter method");
		
		storeOriginalHotelResults();
		//close the survey no button if it appears
		try {
			bookingResultsPage.surveyNoButton.click();
		} catch (Exception err) {
			err.printStackTrace();
		}
		
		bookingResultsPage.saunaCheckBox.click();
		
		GenericFunctions.sleep(3000);
		
	}
	
	@When("^I click to remove the Applied filter$")
	public void i_remove_the_applied_filter() throws Throwable {
		LOGGER.log(Level.INFO, "entering remove applied filter method");		

		bookingResultsPage.removeFilterCheckBox.click();
		
		GenericFunctions.sleep(3000);
		
	}
	
	@Then("The results page has the unfiltered hotel list")
	public void the_results_page_has_the_unfiltered_hotel_list() throws Throwable {
		LOGGER.log(Level.INFO, "entering results page  has the unfiltered hotel list method");
		
 
		List<WebElement> hotelSpans = bookingResultsPage.hotelResultSpans;
		boolean hotelMatch = false;
		
		if (hotelResultsList != null && hotelSpans != null) {		
				
			// now check to see if the content is the same, start with the previous list
			for (String hotelResult:hotelResultsList) {
				for (WebElement hotelSpan:hotelSpans) {
					if (hotelResult.equals(hotelSpan.getText().trim())) {
						hotelMatch = true;
					}
				}
				
			}
		}
		
		Assert.assertTrue(hotelMatch);
	}
	
	
	@Then("The results page has these hotels")
	public void the_results_page_has_these_hotels(DataTable dataTable) throws Throwable {
		LOGGER.log(Level.INFO, "entering results page method");
		//Write the code to handle Data Table
		//This is to get the first data of the set(i.e 2nd row), 1st row are headers
		List<List<String>> data = dataTable.raw();
 
		List<WebElement> hotelSpans = bookingResultsPage.hotelResultSpans;
		boolean hotelFound = false;
		
		for (WebElement hotelSpan:hotelSpans) {
			
			if (data.get(1).get(0).equals(hotelSpan.getText().trim())) {
				hotelFound = true;
				LOGGER.log(Level.INFO, data.get(1).get(0)+" is found");
				break;
			}
			
		}
		Assert.assertTrue(hotelFound);	
		
		hotelFound = false;
		
		for (WebElement hotelSpan:hotelSpans) {
				
			if (data.get(2).get(0).equals(hotelSpan.getText().trim())) {
				hotelFound = true;
				LOGGER.log(Level.INFO, data.get(1).get(0)+" is found!!");
				break;
			}				
			
		}
		Assert.assertFalse(hotelFound);	
	}
	
	@Then("The results page has at least 1 hotel")
	public void the_results_page_has_at_least_these_hotels() throws Throwable {
		LOGGER.log(Level.INFO, "entering results page has at least 1 hotel method");
 
		List<WebElement> hotelSpans = bookingResultsPage.hotelResultSpans;
		
		boolean hotelFound = false;
		
		if (hotelSpans.size() > 0) {
			hotelFound = true;
			LOGGER.log(Level.INFO, "A hotel is found");
		}
		
		Assert.assertTrue(hotelFound);	
		
	}
	


}