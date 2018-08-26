package ui.stepdefinitions;

import org.junit.runner.RunWith;

import com.github.mkolisnyk.cucumber.runner.ExtendedCucumber;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;

import cucumber.api.CucumberOptions;

import cucumber.api.junit.Cucumber;

@RunWith(ExtendedCucumber.class)
@ExtendedCucumberOptions( 
		retryCount = 0,
		detailedReport = true,
		detailedAggregatedReport = true,
		overviewReport = true,
		coverageReport = true,
		jsonUsageReport = "target/Extended-Runner/cucumber-usage.json",
		usageReport = true,
		toPDF = true,
		excludeCoverageTags = {"@flaky" },
		includeCoverageTags = {"@passed" },
		outputFolder = "target/Extended-Runner")


@CucumberOptions( 
				features = {"src/test/resources/features/"}, 
                plugin = { "pretty","json:target/cucumber.json", "html:target/site/cucumber-pretty","usage:target/Extended-Runner/cucumber-usage.json"},
                glue = {"ui.stepdefinitions"},
            
		// dryRun= true4
		tags = { "@local"}) // ~@test


		

public class RunCukesTest {
}  
//format = { "pretty","json:target/cucumber.json" },
//html:target/cucumber-html-report
