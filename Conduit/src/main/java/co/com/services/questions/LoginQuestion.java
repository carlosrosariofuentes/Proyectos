package co.com.services.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class LoginQuestion implements Question<String>{


        @Override
        public String answeredBy(Actor actor) {
            return SerenityRest.lastResponse().path("user.email");

        }

        public static Question<String> email(){
            return new LoginQuestion();
        }




}
