package Runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/Features",
        glue = "StepDefinitions",
        tags = "@Sanity",
        plugin = { "pretty",
                "html:target/reports/cucumber.html",
                "json:target/reports/cucumber.json",
                "junit:target/reports/cukes.xml",
                "rerun:target/reports/rerun.txt"}
)

public class TestRunners {
}