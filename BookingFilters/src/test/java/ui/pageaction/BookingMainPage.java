package ui.pageaction;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import ui.or.BookingMainOR;

public class BookingMainPage extends BookingMainOR 
{
	WebDriver driver;
	
	public BookingMainPage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
}
