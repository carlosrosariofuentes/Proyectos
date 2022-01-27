package co.com.services.tasks;

import co.com.services.interactions.Post;
import co.com.services.models.article.Articles;
import co.com.services.models.article.Data;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

import java.util.ArrayList;
import java.util.List;

import static co.com.services.utils.Constans.TOKEN;

public class CreateArticleTask implements Task {
    Articles article = new Articles();
    Data data = new Data();
    @Override
    public <T extends Actor> void performAs(T actor) {
        String token = TOKEN;
        List<Object> Tags = new ArrayList<Object>();
        Tags.add("Boqui Duro");

        article.setTitle("lucho nuevo");
        article.setDescription("el luchito de qvision en serenity");
        article.setBody("al lucho lo cogieron asando mazorca con el nico");
        article.setTagList(Tags);
        data.setArticle(article);
        actor.attemptsTo(
                Post.to("articles/")
                        .with(requestSpecification -> requestSpecification
                                .contentType(ContentType.JSON)
                                .auth().oauth2(token)
                                .body(data)
                        )

        );

    }
    public static Performable crearAticle(){

        return Tasks.instrumented(CreateArticleTask.class);
    }
}
