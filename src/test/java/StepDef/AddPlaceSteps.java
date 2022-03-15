package StepDef;

import POJO.AddPlace.AddPlace;
import Resources.APIResources;
import Resources.TestDataBuild;
import Resources.Utils;
import io.cucumber.java.en.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;

import java.io.IOException;

public class AddPlaceSteps extends Utils {
    static RequestSpecification res;
    static ResponseSpecification resSpec;
    static Response response;
    static String placeId;
    TestDataBuild data = new TestDataBuild();

    @Given("Add place payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {
        res = given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));
        //System.out.println(data.addPlacePayload(data.addPlacePayload(name, language, address));
    }

    @Given("Delete place payload")
    public void delete_place_payload() throws IOException {
        res= given().spec(requestSpecification()).body(data.deletePlacePayload(placeId));
    }

    @When("User calls {string} with {string} request")
    public void user_calls_with_request(String resource, String httpMethod) {
        resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        APIResources resourceApi = APIResources.valueOf(resource);
        System.out.println(resourceApi.getResource());

        if(httpMethod.equalsIgnoreCase("POST")){
            response = res.when().post(resourceApi.getResource());
        } else if (httpMethod.equalsIgnoreCase("GET")){
            response = res.when().get(resourceApi.getResource());
        }

    }
    @Then("User got a response with StatusCode {int}")
    public void user_got_a_response_with_status_code(Integer int1) {
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(200, actualStatusCode);
    }
    @Then("{string} in the response is {string}")
    public void in_the_response_is(String key, String value) {
        String actualValue = getJsonPath(response, key);
        Assert.assertEquals(value,actualValue);
    }

    @Then("Verify placeID created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
        placeId = getJsonPath(response, "place_id");
        System.out.println("actualPlaceId = " + placeId);
//        request spec
        res = given().spec(requestSpecification()).queryParam("place_id", placeId);
        user_calls_with_request(resource, "GET");
        String actualName = getJsonPath(response, "name");
        Assert.assertEquals(expectedName, actualName);
    }
}
