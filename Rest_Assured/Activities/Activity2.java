package Activity;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Activity2 {
    // Set base URL
    final static String ROOT_URI = "https://petstore.swagger.io/v2/user";

    @Test(priority = 1)
    public void addNewUserFromFile() throws IOException {
        // Import JSON file
        FileInputStream inputJSON = new FileInputStream("src/test/java/activities/userinfo.json");
        // Read JSON file as String
        String reqBody = new String(inputJSON.readAllBytes());

        Response response =
                given().contentType(ContentType.JSON) // Set headers
                        .body(reqBody) // Pass request body from file
                        .when().post(ROOT_URI); // Send POST request

        inputJSON.close();

        // Assertion
        response.then().body("code", equalTo(200));
        response.then().body("message", equalTo("8454"));
    }

    @Test(priority = 2)
    public void getUserInfo() {

        File outputJSON = new File("src/test/java/activities/userGETResponse.json");

        Response response =
                given().contentType(ContentType.JSON)
                        .pathParam("username", "Pavankalyan")
                        .when().get(ROOT_URI + "/{username}");

        String resBody = response.getBody().asPrettyString();

        try {
           outputJSON.createNewFile();
            FileWriter writer = new FileWriter(outputJSON.getPath());
            writer.write(resBody);
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Assertion
        response.then().body("id", equalTo(1999));
        response.then().body("username", equalTo("pavankalyan"));
        response.then().body("firstName", equalTo("Pavan"));
        response.then().body("lastName", equalTo("Kalyan"));
        response.then().body("email", equalTo("kalyanp@.com"));
        response.then().body("password", equalTo("password123"));
        response.then().body("phone", equalTo("8247060137"));
    }

    @Test(priority = 3)
    public void deleteUser() throws IOException {
        Response response =
                given().contentType(ContentType.JSON) // Set headers
                        .pathParam("username", "pavankalyan") // Add path parameter
                        .when().delete(ROOT_URI + "/{username}"); // Send POST request

        // Assertion
        response.then().body("code", equalTo(200));
        response.then().body("message", equalTo("pavankalyan"));
    }
}
