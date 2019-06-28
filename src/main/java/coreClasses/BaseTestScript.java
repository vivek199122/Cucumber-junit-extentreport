package coreClasses;


import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseTestScript {

	public static WebDriver driver;

	static String APPLICATION_URL;

	

	// builds a new report using the html template

	static ExtentHtmlReporter htmlReporter;

	public static ExtentReports extent;

	// helps to generate the logs in test report.

	public ExtentTest test;

	@BeforeSuite

	public void beforesuite() {

		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/testReport.html");

		// initialize ExtentReports and attach the HtmlReporter

		extent = new ExtentReports();

		extent.attachReporter(htmlReporter);

		// To add system or environment info by using the setSystemInfo method.

//                    extent.setSystemInfo("OS", OS);

//                    extent.setSystemInfo("Browser", browser);

		// configuration items to change the look and feel

		// add content, manage tests etc

		htmlReporter.config().setChartVisibilityOnOpen(true);

		htmlReporter.config().setDocumentTitle("Extent Report Demo");

		htmlReporter.config().setReportName("Test Report");

		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);

		htmlReporter.config().setTheme(Theme.STANDARD);

		htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

	}

	@BeforeTest
	public static void beoferTest() throws IOException {
		driver = setUp();
		//driver.navigate().to("https://testoam.secure.fedex.com/oam/server/obrareq.cgi?encquery%3DQK0xaQxrwBQRW56DEq894MLBJ5gbH5NeEQ3RuVWvocbN%2BcHQ9udhBUeSq9qyPHU9bsWMmiyCGoxJUns%2BpraQw%2BDDo%2BmFrETLWQs2XnN%2B4CjGsiXEr8h2bxBxARORpDWfAd05bR0P43ZNrzJFALGf2s3f42TLyfwgkR0hJHpHqvqD3Bjzpu6gGZDiz%2FkQD4VkFz4zcTc5bVKlLmDmL4VuKo9AaqBq4wWlkIME40h3A8WELTJUMfGvArvmDy7EvtFa%2BjiHg7kSKUeeKgtV71sy8P9YD8o9NkfbvccYlcy3DGKAHGlrjMv5DNii%2FQ1u06sNyCpG%2BFgif5PfEKEvQ0HQ6AREcL9IoIdm6qSXjY0WvD0%3D%20agentid%3DWTCApacheWebgate%20ver%3D1%20crmethod%3D2%26cksum%3D02dbf3eaa886477a1f19d33be9cfdb0fd6c3f1aa");
		driver.navigate().to("https://www.google.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	public static WebDriver setUp() throws MalformedURLException {
		System.out.println(System.getProperty("browserName"));
		if (System.getProperty("browserName") == null) {

			DOMConfigurator.configure("log4j.xml");
			//DesiredCapabilities caps = DesiredCapabilities.chrome();
			//caps.setCapability("deviceName", "Google Nexus 7 HD Emulator");
			//caps.setCapability("version", "latest");
			//return new RemoteWebDriver(new java.net.URL(URL), caps);*/
			
			System.setProperty("webdriver.chrome.driver", "/Software/chromedriver");
			ChromeOptions chromeoptions = new ChromeOptions();
			chromeoptions.addArguments("disable -extensions");
			chromeoptions.addArguments("--start-maximized");
			return new ChromeDriver(chromeoptions);

		} else if (System.getProperty("browserName").equalsIgnoreCase("localchrome")) {
			DOMConfigurator.configure("log4j.xml");
			System.setProperty("webdriver.chrome.driver", "./bin/chromedriver.exe");
			ChromeOptions chromeoptions = new ChromeOptions();
			chromeoptions.addArguments("disable -extensions");
			chromeoptions.addArguments("--start-maximized");
			return new ChromeDriver(chromeoptions);
		} else if (System.getProperty("browserName").equalsIgnoreCase("localfirefox")) {
			System.setProperty("webdriver.gecko.driver", "./bin/geckodriver.exe");
			FirefoxOptions firefoxoptions = new FirefoxOptions();
			firefoxoptions.addArguments("disable -extensions");
			firefoxoptions.addArguments("--start-maximized");
			return new FirefoxDriver(firefoxoptions);
		} else if (System.getProperty("browserName").equalsIgnoreCase("localie")) {
			System.setProperty("webdriver.ie.driver", "./bin/IEDriverServer.exe");
			InternetExplorerOptions ieoptions = new InternetExplorerOptions();
			/*
			 * ieoptions.addArguments("disable -extensions");
			 * ieoptions.addArguments("--start-maximized");
			 */
			return new InternetExplorerDriver(ieoptions);

		}  else {

			DOMConfigurator.configure("log4j.xml");
			System.setProperty("webdriver.chrome.driver", "./bin/chromedriver.exe");
			ChromeOptions dchromeoptions = new ChromeOptions();
			dchromeoptions.addArguments("disable -extensions");
			dchromeoptions.addArguments("--start-maximized");
			return new ChromeDriver(dchromeoptions);
		}

	}


	@AfterMethod

	public void getResult(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {

			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAILED ", ExtentColor.RED));

			test.fail(result.getThrowable());

		} else if (result.getStatus() == ITestResult.SUCCESS) {

			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASSED ", ExtentColor.GREEN));

		} else {

			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIPPED ", ExtentColor.ORANGE));

			test.skip(result.getThrowable());

		}


	}

	@AfterTest
	public static void tearDown() {
		driver.quit();

	}


}