package stepdefinitions1;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.Matchers.equalTo;
import java.util.Map;

public class OrderSteps {
   
    private RequestSpecification request;
    private Response response;
    private String baseUri = "http://localhost:3000";

    @Given("the following user and restaurant exist:")
    public void the_following_user_and_restaurant_exist(io.cucumber.datatable.DataTable dataTable) {
        // You can implement this method to set up the user and restaurant in the database if needed
        // For simplicity, assuming they already exist
        request = RestAssured.given().baseUri(baseUri);
    }

    @When("I place a new order with the following details:")
    public void i_place_a_new_order_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> orderDetails = dataTable.asMap(String.class, String.class);
        String foodItems = orderDetails.get("foodItems");
        String totalPrice = orderDetails.get("totalPrice");

        String orderJson = String.format("{ \"userId\": 1, \"restaurantId\": 1, \"foodItems\": \"%s\", \"totalPrice\": %s }", foodItems, totalPrice);
        response = request.body(orderJson)
            .header("Content-Type", "application/json")
            .when()
            .post("/orders");
    }

    @Then("the order should be placed successfully")
    public void the_order_should_be_placed_successfully() {
        response.then().statusCode(201);
    }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(Integer statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the response should contain the order details")
    public void the_response_should_contain_the_order_details() {
        response.then()
            .body("userId", equalTo(1))
            .body("restaurantId", equalTo(1))
            .body("foodItems", equalTo("Pasta, Garlic Bread"))
            .body("totalPrice", equalTo(25.00f));
    }
}

