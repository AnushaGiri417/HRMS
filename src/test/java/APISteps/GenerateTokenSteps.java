package APISteps;

import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.apiConstants;

import static io.restassured.RestAssured.given;

public class GenerateTokenSteps {

    static String token;
    @Given("a JWT is generated")
    public void a_JWT_is_generated() {
        RequestSpecification generateTokenRequest = given().header("Content-type", "application/json")
                .body("{\n" +
                        "    \"email\":\"anushamail@gmail.com\",\n" +
                        "    \"password\":\"postmanwilldelivermail\"\n" +
                        "}");
        Response generateTokenResponse = generateTokenRequest.when().post( apiConstants.GENERATE_TOKEN_URI);
        generateTokenResponse.prettyPrint();
        token="Bearer "+generateTokenResponse.jsonPath().getString("token");
    }
}
