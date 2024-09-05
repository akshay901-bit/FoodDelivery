package testrunner;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "features1",
    glue = {"stepdefinitions2"},
    plugin = {"pretty", "html:target/cucumber-reports"},
    monochrome = true
)

public class TestRunner2 {

}
