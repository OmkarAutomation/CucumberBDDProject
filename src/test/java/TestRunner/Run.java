package TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
	
@RunWith(Cucumber.class)
@CucumberOptions(features=".//Features/Customers.feature", 
		glue="StepDefination",
		dryRun= false,
		monochrome= true,
		tags="@Sanity", //scenarios which are under @Sanity will get executed.
		plugin= {"pretty", "html:target/cucumber-reports/reports1.html"}
		)

public class Run {

}
