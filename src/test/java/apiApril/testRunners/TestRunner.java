package apiApril.testRunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
//configuration fo running the tests
//to tell that we are not going to use Junit to execute the tests, but Cucumber:
@RunWith(Cucumber.class)
//to tell Cucumber where to find all the files for test execution:
@CucumberOptions(
        //to tell where the plugins are:
        plugin = {"pretty",
                //to specify where the reports will be saved, can be seen in "target" under the "cucumber-reports", not meant for human reading:
                //reports for human reading: target --> generated reports --> index.html, can be opened in browser
                "json:target/cucumber-reports/CucumberTestReport.json",
                "junit:target/cucumber-reports/report.xml"},
        //where feature files are:
        features = {"src/test/resources/features"},
        //where the steps are:
        glue = {"apiApril/stepdefinitions"}
)
public class TestRunner {
}
