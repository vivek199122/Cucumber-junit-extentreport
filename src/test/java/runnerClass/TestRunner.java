package runnerClass;

import java.io.File;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterTest;

import com.cucumber.listener.Reporter;
import com.google.common.io.Files;

import coreClasses.BaseTestScript;
import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resource/Functional", glue = { "webStepDef" }, tags = {
		"@LoginTest , @LoginTest1"  }, dryRun = false, plugin = {
				"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html" })
public class TestRunner {

	@AfterClass
	public static void afterScenario() throws IOException {

		Reporter.loadXMLConfig(new File("/Users/vivek/Downloads/HRO-Exit/extent-config.xml"));
		Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
		Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
		Reporter.addScreenCaptureFromPath("/Users/vivek/Downloads/HRO-Exit/target/cucumber-reports");
//		    Reporter.setSystemInfo("Machine", 	"Windows 10" + "64 Bit");
//		    Reporter.setSystemInfo("Selenium", "3.7.0");
//		    Reporter.setSystemInfo("Maven", "3.5.2");
//		    Reporter.setSystemInfo("Java Version", "1.8.0_151");

	}

}
