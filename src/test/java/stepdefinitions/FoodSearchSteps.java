package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class FoodSearchSteps {

    private RequestSpecification request;
    private Response response;
    private String baseUri = "http://localhost:3000";

    @Given("the food item {string} exists in the system")
    public void the_food_item_exists_in_the_system(String foodName) {
        request = RestAssured.given().baseUri(baseUri);
        // Assuming the food item already exists in the system
    }

    @When("I search for food by name {string}")
    public void i_search_for_food_by_name(String foodName) {
        response = request
            .queryParam("name", foodName)
            .when()
            .get("/foods/search");
    }

    @Then("I should receive a list containing {string}")
    public void i_should_receive_a_list_containing(String foodName) {
        response.then()
            .statusCode(200)
            .body("name", hasItem(foodName));
    }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(Integer statusCode) {
        response.then().statusCode(statusCode);
    }
}
