package stepdefinitions3;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UserManagementSteps {

    private RequestSpecification request;

    @Given("User is registered")
    public void user_is_registered() {
        baseURI = "http://localhost:3000";
        request = given()
                    .header("Content-Type", "application/json");
    }

    @When("User updates the profile with valid data")
    public void user_updates_the_profile_with_valid_data() {
        request.body("{\"username\": \"john_updated\", \"email\": \"john_updated@example.com\"}")
               .when().put("/users/1")
               .then().statusCode(200);
    }

    @Then("The response status code should be {int}")
    public void the_response_status_code_should_be(Integer statusCode) {
        request.then().statusCode(statusCode);
    }

    @Then("The username should be {string}")
    public void the_username_should_be(String username) {
        request.then().body("username", equalTo(username));
    }

    @Then("The email should be {string}")
    public void the_email_should_be(String email) {
        request.then().body("email", equalTo(email));
    }
}

