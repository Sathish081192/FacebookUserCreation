package runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/Features/OrderOnline.feature", 
glue = { "stepDefinition" },
plugin = {
		"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/ExtentReport.html" })

public class TestRunner {

}
