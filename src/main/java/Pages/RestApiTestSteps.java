package Pages;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import java.util.ArrayList;
import java.util.Map;
import static org.junit.Assert.*;

public class RestApiTestSteps {

    private String baseUri;
    private Response response;
    private ScenarioContext scenarioContext = new ScenarioContext();

    @Given("Base URI is {string}")
    public void setBaseUri(String uri) {
        baseUri = uri;
    }

    @When("I send a POST request to {string}")
    public void setRequestBody(String adress, DataTable dataTable) {
        Map<String, String> userData = dataTable.asMap(String.class, String.class);

        String requestBody = "{\n" +
                "  \"İl\": \"" + userData.get("İl") + "\",\n" +
                "  \"Bina\": \"" + userData.get("Bina") + "\",\n" +
                "  \"Daire\": \"" + userData.get("Daire") + "\",\n" +
                "  \"Sokak\": \"" + userData.get("Sokak") + "\",\n" +
                "  \"İlçe\": \"" + userData.get("İlçe") + "\",\n" +
                "  \"Mahalle\": \"" + userData.get("Mahalle") + "\"\n" +
                "}";

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(baseUri + adress);
    }
    @When("I send a Get request to {string}")
    public void getAdressBody(String adress) throws Exception{
        response= null;
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .get(baseUri + adress+"?id="+scenarioContext.getId());
    }

    @Then("The response status code should be {int}")
    public void verifyStatusCode(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Then("The response content type should be {string}")
    public void verifyContentType(String contentType) {
        String actualContentType = response.getContentType();
        assertEquals(contentType, actualContentType);
    }

    @Then("The response body should be {string} and {string}")
    public void verifyResponseBody(String il, String ilce) {
        JsonPath jsonPath = response.getBody().jsonPath();

        String actualIl = jsonPath.get("İl");
        String actualIlce = jsonPath.get("İlçe");
        Assert.assertEquals(il, actualIl);
        Assert.assertEquals(ilce, actualIlce);
    }

    @Then("The response Adress body should be")
    public void verifyResponseBody2(DataTable dataTable) {
        Map<String, String> userData = dataTable.asMap(String.class, String.class);

        String jsonPath = String.valueOf(response.getBody().asString());
        Gson gson = new Gson();
        ArrayList<JsonObject> list = gson.fromJson(jsonPath, new TypeToken<ArrayList<JsonObject>>(){}.getType());

        if (!list.isEmpty()) {
            JsonObject firstObject = list.get(0);
            Assert.assertEquals(userData.get("İl"),firstObject.get("İl").getAsString());
            Assert.assertEquals(userData.get("Bina"),firstObject.get("Bina").getAsString());
            Assert.assertEquals(userData.get("Daire"),firstObject.get("Daire").getAsString());
            Assert.assertEquals(userData.get("Sokak"),firstObject.get("Sokak").getAsString());
            Assert.assertEquals(userData.get("İlçe"),firstObject.get("İlçe").getAsString());
            Assert.assertEquals(userData.get("Mahalle"),firstObject.get("Mahalle").getAsString());
        }
    }

    @Then("The response body in the id should be not null and integer type")
    public void verifyResponseBody() throws Exception{
        JsonPath jsonPath = response.getBody().jsonPath();

        Integer actualId = jsonPath.get("id");

        assertNotNull(actualId);
        assertTrue(actualId instanceof Integer);

        scenarioContext.setId(actualId);
    }

    @Then("The response should contain {string}")
    public void verifyResponseContains(String expectedResponse) {
        String responseBody = response.getBody().asString();
        assertTrue(responseBody.contains(expectedResponse));
    }
}