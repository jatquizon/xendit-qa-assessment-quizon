import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        glue= {"step_definitions"},
        plugin = { "pretty" },
        publish = true,
        monochrome = true
)

public class TestRunner {

}
