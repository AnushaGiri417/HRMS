package Runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        //path to the feature files
        features ="C:\\Java Project\\hrms\\src\\test\\resources\\features",
        /**
         *glue is where we can find implementaion for Gherkin steps
         * it provides paths to the step definition files
        */
        glue="steps",
        /**
         * dryRun checks if all the Gherkin steps have the implementation
         *  it checks if there is proper mapping between feature files and step definition files
         *
         *  This option can either set as true or false (default value is false). If it is set as true, it means that Cucumber will only checks
         *  that every Step mentioned in the Feature File have corresponding code written in Step Definition file or not.
         *  So in case any of the function is missed in the Step Definition for any Step in Feature File, it will give us the message.
         *  So If you writing scenarios first and then implementing step definitions then add dryRun = true.
        */
        dryRun = false,
        /**
         * monochrome displays the console output in proper readable format
         * detailed and organized display of console output
        */
        monochrome = true,
        /**
         * strict will check if any step is not defined in the step definition file
         * if it finds any undefined steps, it will throw an error and stops the execution
         *
         * if strict option is set to false then at execution time if cucumber encounters any undefined/pending steps
         * then cucumber does not fail the execution and undefined steps are skipped and BUILD is SUCCESSFUL.
         * if Strict option is set to true then at execution time if cucumber encounters any undefined/pending steps
         * then cucumber  fails the execution and undefined steps are marked as fail and BUILD is FAILURE.
        */
        //   strict = true,
        tags = "  @regression ",
        plugin = {"pretty","html:target/cucumber-default report.html","json:target/cucumber.json","rerun:target/failed.txt"}

)

public class RegressionRunner {
}
