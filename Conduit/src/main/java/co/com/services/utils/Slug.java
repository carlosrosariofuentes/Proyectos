package co.com.services.utils;

import net.serenitybdd.rest.SerenityRest;

public class Slug {

    public static String slug(){
        return SerenityRest.lastResponse().jsonPath().getString("articles[0].slug");

    }


    public static String reusar = slug();
}
