package co.com.services.tasks;

import co.com.services.interactions.Get;
import co.com.services.utils.Constans;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

import static co.com.services.utils.Constans.TOKEN;

public class ArticlesTask implements Task {
    @Override
    public <T extends Actor> void performAs(T actor) {
        String token = TOKEN;
        actor.attemptsTo(

                Get.resource("articles")
                        .with(requestSpecification -> requestSpecification
                                .contentType(ContentType.JSON)
                                .auth().oauth2(token)
                        )
        );
    }

    public static Performable articles() {
        return Tasks.instrumented(ArticlesTask.class);

    }
}
