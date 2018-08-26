package ui.stepdefinitions;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import utilities.GenericFunctions;
import utilities.WebBrowser;

public class Hooks extends WebBrowser {
	
	Format formatter = new SimpleDateFormat("dd-MM-YYYY_hh-mm-ss");
	Date date = new Date();
	
	public Hooks() {
		driver = WebBrowser.getDriver();
	}

	@After
	public void tearDown(Scenario scenario) {
		
		if(scenario.isFailed())
		{
			GenericFunctions.captureScreenshoot(driver, scenario.getName()+" "+formatter.format(date));
		}
		 if(driver!= null){
				
				driver.quit();
				driver = null;
				}
	}
}
