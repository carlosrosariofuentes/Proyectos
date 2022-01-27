package co.com.services.tasks;

import co.com.services.interactions.Delete;
import co.com.services.utils.Constans;
import co.com.services.utils.Slug;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

import static co.com.services.utils.Constans.TOKEN;

public class DeleteArticleTask implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        String slug= Slug.reusar;
        actor.attemptsTo(
                Delete.from("articles/"+ slug)
                        .with(requestSpecification -> requestSpecification
                                .auth()
                                .oauth2(TOKEN)
                        )

        );
    }

    public static Performable deleteArticle(){
        return Tasks.instrumented(DeleteArticleTask.class);
    }
}
