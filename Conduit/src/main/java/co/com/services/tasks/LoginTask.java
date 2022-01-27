package co.com.services.tasks;

import co.com.services.interactions.Post;
import co.com.services.models.login.LoginData;
import co.com.services.models.login.User;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class LoginTask implements Task {
    User login;

    public LoginTask(User login) {

        this.login = login;
    }

    LoginData data = new LoginData();

    String response;

    @Override
    public <T extends Actor> void performAs(T actor) {
        data.setUser(login);
        actor.attemptsTo(

                Post.to("users/login")
                        .with(requestSpecification -> requestSpecification
                                .contentType(ContentType.JSON)
                                .body(data)
                        )
        );

    }
    // public static String  response= SerenityRest.lastResponse().jsonPath().getString("user.token");

    public static LoginTask loginInformacion(User datoUsuario) {
        return Tasks.instrumented(LoginTask.class, datoUsuario);

    }
}
