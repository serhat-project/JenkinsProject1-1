package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = {"src/test/java/features/JenkinsProject.feature"},
        glue = {"stepdefs"},
        plugin = { "pretty",
                "json:test-output/cucumber-reports/cucumber.json",
                "html:test-output/cucumber-reports/cucumber.html",
        }

)
public class RunnerClass extends AbstractTestNGCucumberTests {


}
