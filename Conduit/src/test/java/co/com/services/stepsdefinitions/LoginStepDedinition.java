package co.com.services.stepsdefinitions;


import co.com.services.models.login.User;
import co.com.services.questions.LoginQuestion;
import co.com.services.questions.ResponseCode;
import co.com.services.tasks.LoginTask;
import co.com.services.utils.Constans;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import static co.com.services.utils.Constans.URL;
import static org.hamcrest.CoreMatchers.equalTo;

public class LoginStepDedinition {
    Actor Carlos = Actor.named("Carlos");

    @Given("carlos is a user registed  and can login")
    public void carlosIsAUserRegistedAndCanLogin() {
        Carlos.whoCan(CallAnApi.at(URL));
    }

    @When("he send the credencials of acces {string} and {string}")
    public void heSendTheCredencialsOfAccesAnd(String email, String password) {
        User dataLogin = new User();
        dataLogin.setEmail(email);
        dataLogin.setPassword(password);
        Carlos.attemptsTo(LoginTask.loginInformacion(dataLogin));
    }

    @Then("he access to the page")
    public void heAccessToThePage() {
        Carlos.should(GivenWhenThen.seeThat(Constans.MENSAJE_CODIGO,
                ResponseCode.statusCode(), equalTo(200)));
        Carlos.should(GivenWhenThen.seeThat("Validacion usuario", LoginQuestion.email(), equalTo("charlyrosariofuentes@")));
        //seeThat("Email enviado", Respuesta.emailEnviado(), equalTo(TOAS_EMAIL_ENVIADO))
    }



}
