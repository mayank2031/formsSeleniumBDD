package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepdefinitions",
        dryRun = false,
        monochrome = true,
        tags = "@SignInWithValidCredential",
        plugin = {"pretty", "html:target/cucumber-reports/report.html"}
)

public class TestRunner extends AbstractTestNGCucumberTests {
}