package Runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        features ="src\\test\\resources\\features\\APIWorkFlow.feature",

        glue="APISteps",

        dryRun = false,

        monochrome = true,

        tags = "  @APIWorkflow",
        plugin = {"pretty","html:target/cucumber-default report.html","json:target/cucumber.json"}

)

public class APIRunner {
}
