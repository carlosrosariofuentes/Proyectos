package co.com.services.tasks;

import co.com.services.interactions.Get;
import co.com.services.utils.Constans;
import co.com.services.utils.Slug;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

import static co.com.services.utils.Constans.TOKEN;

public class ArticuloUnoTask implements Task {
    @Override
    public <T extends Actor> void performAs(T actor) {
        String slug= Slug.slug();
        String token = TOKEN;
        actor.attemptsTo(
                Get.resource("articles/"+slug)
                        .with(requestSpecification -> requestSpecification
                                .contentType(ContentType.JSON)
                                .auth()
                                .oauth2(token)
                        )

        );
    }
    public static Performable articuloUno (){
        return Tasks.instrumented(ArticuloUnoTask.class);

    }
}
