package runnerClass;



import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
features = "src/test/resource/Functional", glue= {"webStepDef"},
tags= {"@LoginTest"}, plugin = { "pretty", "html:target/cucumber-reports","json:target/cucumber.json"}, dryRun = false
)
public class TestRunner {

}


 