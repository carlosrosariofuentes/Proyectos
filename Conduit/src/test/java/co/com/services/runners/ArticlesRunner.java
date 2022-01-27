package co.com.services.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/articles.feature",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        glue = "co.com.services.stepsdefinitions",
        dryRun = false


)
public class ArticlesRunner {
}
