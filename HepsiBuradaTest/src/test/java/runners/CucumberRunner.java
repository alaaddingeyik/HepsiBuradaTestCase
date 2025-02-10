package runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        glue = "/step_definitions",

        features = "src/test/feature",

        dryRun = false,
        tags = "@Hepsi",


        plugin ={

                "html:target/default-report",
                "json:target/cucumber2.json",
                "rerun:target/rerun.txt"
        }
)
public class CucumberRunner {

}
