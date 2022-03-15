package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features="src/test/java/FeatureAddPlace",
        glue="StepDef",
        tags="@AddPlace",
        plugin = {"json:target/jsonReports/cucumber-report.json",
                    "html:target/jsonReports/cucumber-report.html"}
)
public class TestRunner {
}
