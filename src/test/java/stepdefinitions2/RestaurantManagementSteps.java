package stepdefinitions2;



import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestaurantManagementSteps {

    private Response response;
    private String restaurantJson;

    @Given("I have restaurant details with name {string}, address {string}, cuisine type {string}, and contact information {string}")
    public void i_have_restaurant_details(String name, String address, String cuisineType, String contactInfo) {
        restaurantJson = String.format("{\"name\": \"%s\", \"address\": \"%s\", \"cuisineType\": \"%s\", \"contactInfo\": \"%s\"}",
                        name, address, cuisineType, contactInfo);
    }

    @When("I send a POST request to {string}")
    public void i_send_a_post_request(String endpoint) {
        response = given()
                       .header("Content-Type", "application/json")
                       .body(restaurantJson)
                   .when()
                       .post(endpoint);
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the response should contain the restaurant name {string}")
    public void the_response_should_contain_the_restaurant_name1(String name) {
        response.then().body("name", equalTo(name));
    }

    @Given("I have the restaurant ID {string}")
    public void i_have_the_restaurant_id(String restaurantId) {
        RestAssured.basePath = "/restaurants/" + restaurantId;
    }

    @When("I send a GET request to {string}")
    public void i_send_a_get_request_to(String endpoint) {
        response = given().when().get(endpoint);
    }

    @Then("the response should contain the restaurant name {string}")
    public void the_response_should_contain_the_restaurant_name(String name) {
        response.then().body("name", equalTo(name));
    }
}
