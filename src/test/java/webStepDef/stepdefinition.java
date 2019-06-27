package webStepDef;



import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;

import coreClasses.BaseTestScript;

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
	

}
