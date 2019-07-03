package webStepDef;



import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;

import com.cucumber.listener.Reporter;
import com.google.common.io.Files;

import coreClasses.BaseTestScript;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import pageObjects.FedexHome;


public class stepdefinition extends BaseTestScript {
	
	private static Logger Log = Logger.getLogger(stepdefinition.class.getName());

	FedexHome fedexHome;

	
	@Given("^User on login page$")
	public void userIsOnHomePage() throws Throwable {
		BaseTestScript.beoferTest();
		fedexHome = new FedexHome(BaseTestScript.driver);
		//Assert.assertTrue(driver.findElement(By.xpath("//nav[@class='Header__NavigationWrapper-sc-1ryesrd-0 gOyvnw']")).isDisplayed(),"Page is Not Loaded ! ");

	}
	
	@Then("^User enters \"([^\"]*)\" and \"([^\"]*)\" and clicks on login button$")
	public void doLogin(String UserName, String Password) throws Throwable {
		//BaseTestScript.beoferTest();
		//fedexHome = new FedexHome(BaseTestScript.driver);
		fedexHome.doLogin(UserName, Password);
		Assert.assertFalse("".equalsIgnoreCase(""));
		Log.info("User logged in sucessfully");	

	}
	
	public void VerifyuserLoggedinsuccessfully() throws IOException, InterruptedException {
		
		if(fedexHome.verifyusersuccess()) {
			
			Log.info("User not able to logged in");
			Assert.assertFalse(true);
		}
	}
	
	@Then("User closes browser$")
	public void closeBrowser(){
		BaseTestScript.tearDown();
	}
	
	

	@Before
	public void beforeScenario(Scenario scenario) {
		Reporter.assignAuthor("ToolsQA - Lakshay Sharma");
	}

	@After(order = 1)
	public void afterScenario(Scenario scenario) {
		if (scenario.isFailed()) {
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			try {
				// This takes a screenshot from the driver at save it to the specified location
				File sourcePath = ((TakesScreenshot) BaseTestScript.driver).getScreenshotAs(OutputType.FILE);

				// Building up the destination path for the screenshot to save
				// Also make sure to create a folder 'screenshots' with in the cucumber-report
				// folder
				File destinationPath = new File(System.getProperty("user.dir") + "/target/cucumber-reports/"
						+ screenshotName + ".png");

				// Copy taken screenshot from source location to destination location
				Files.copy(sourcePath, destinationPath);

				// This attach the specified screenshot to the test
				Reporter.addScreenCaptureFromPath(destinationPath.toString());
			} catch (IOException e) {
			}
		}
	}

	@After(order = 1)
	public void AfterSteps() {
		BaseTestScript.tearDown();
	}
	
	

}
