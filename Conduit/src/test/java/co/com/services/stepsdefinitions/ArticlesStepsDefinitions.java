package co.com.services.stepsdefinitions;

import co.com.services.questions.ResponseCode;
import co.com.services.tasks.ArticlesTask;
import co.com.services.tasks.ArticuloUnoTask;
import co.com.services.tasks.CreateArticleTask;
import co.com.services.tasks.DeleteArticleTask;
import co.com.services.utils.Constans;
import co.com.services.utils.Slug;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import static co.com.services.utils.Constans.URL;
import static org.hamcrest.CoreMatchers.equalTo;

public class ArticlesStepsDefinitions {
    Actor Carlos = Actor.named("Carlos");



    @Given("carlos is a user registed, and can created a article")
    public void carlosIsAUserRegistedAndCanCreatedAArticle() {
        Carlos.whoCan(CallAnApi.at(URL));
    }

    @When("Enter the article data")
    public void enterTheArticleData() {
        Carlos.attemptsTo(CreateArticleTask.crearAticle());
    }

    @Then("article created")
    public void articleCreated() {

    }





    //-------------------------------------------

    @Given("carlos is a user registed, and can articles list")
    public void carlosIsAUserRegistedAndCanArticlesList() {

        Carlos.whoCan(CallAnApi.at(URL));

    }

    @When("he makes a request")
    public void heMakesARequest() {
        Carlos.attemptsTo(ArticlesTask.articles());
        System.out.println("Este es el eslug"+ Slug.slug());


    }
    @Then("List of articles")
    public void listOfArticles() {
        Carlos.should(GivenWhenThen.seeThat(Constans.MENSAJE_CODIGO,
                ResponseCode.statusCode(), equalTo(200)));

    }
    //-------------------------------------------------------
    @Given("carlos is a user registed, and he need edit a artilce")
    public void carlosIsAUserRegistedAndHeNeedEditAArtilce() {

    }

    @When("Enter the article data to edit")
    public void enterTheArticleDataToEdit() {

    }

    @Then("article edited")
    public void articleEdited() {

    }


    @Given("carlos is a user registed, and he need look a artilce")
    public void carlosIsAUserRegistedAndHeNeedLookAArtilce() {
        Carlos.whoCan(CallAnApi.at(URL));

    }

    @When("list of articles, choose the one from position zero")
    public void listOfArticlesChooseTheOneFromPositionZero() {
        Carlos.attemptsTo(ArticuloUnoTask.articuloUno());
    }

    @Then("article listed")
    public void articleListed() {

    }

    @Given("carlos is a user registed, and he need delete a artilce")
    public void carlosIsAUserRegistedAndHeNeedDeleteAArtilce() {
        Carlos.whoCan(CallAnApi.at(URL));
    }

    @When("when you get the item, delete it")
    public void whenYouGetTheItemDeleteIt() {
        Carlos.attemptsTo(DeleteArticleTask.deleteArticle());

    }

    @Then("article delete")
    public void articleDelete() {

    }



}
