package pageObjects;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import coreClasses.BasePageObject;



public class FedexHome extends BasePageObject{


	private static Logger Log = Logger.getLogger(FedexHome.class.getName());
	
	By username = By.id("username");
	By password = By.id("password");
	By btnSubmit = By.id("submit");
	By errormessage = By.id("");
			
	
	public FedexHome(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stubz
	}
	
	public void doLogin(String UserName, String Password) throws IOException{

		sendkeys(username, UserName);
		sendkeys(password, Password);
		click(btnSubmit);
		
	}
	
	public boolean verifyusersuccess() throws IOException, InterruptedException {
		
		return getText(errormessage).equalsIgnoreCase("");
	}
	
}
