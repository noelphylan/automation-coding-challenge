package ui.pageaction;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import ui.or.BookingResultsOR;

public class BookingResultsPage extends BookingResultsOR 
{
	WebDriver driver;
	
	public BookingResultsPage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
}
