package dummy;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AssertValue {
	public static void main(String[] args) {
		RestAssured.baseURI = "http://localhost:4000/user"; // Set your API base URL
		String bearerToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY1YTZhZDg0NDNlYTYwMzlhY2VmOTIxZiIsImlhdCI6MTcxMTQ3MTUxOSwiZXhwIjoxNzExNDc4NzE5fQ.Qhtvmttg0OOQhzlZWCfBl5bLXAzrSNjdw-vcL2r2JaI";

		ValidatableResponse response = RestAssured.given().header("Authorization", "Bearer " + bearerToken).when()
				.get("/employees").then().assertThat().statusCode(200).body("customer.instruction", equalTo("wait"));

		try {
			response.assertThat().statusCode(200);
			System.out.println("Assertion Passed: Status Code is 200");
		} catch (AssertionError e) {
			System.out.println("Assertion Failed: " + e.getMessage());
		}
	}
}
