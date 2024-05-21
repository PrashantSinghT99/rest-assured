package api.test;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import api.endpoints.Routes;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class newendpointtest {

	public static void main(String args[]) {
		postRequest();
	}

	@Test
	public static void postRequest() {
		Response res = given().contentType(ContentType.JSON).accept(ContentType.JSON).log().all()
				.body("{\r\n" + "  \"id\": 0,\r\n" + "  \"petId\": 0,\r\n" + "  \"quantity\": 0,\r\n"
						+ "  \"shipDate\": \"2024-05-13T11:01:45.912Z\",\r\n" + "  \"status\": \"placed\",\r\n"
						+ "  \"complete\": true\r\n" + "}")
				.when().post("https://petstore.swagger.io/v2/store/order").then().statusCode(200).extract().response();

		System.out.println(res.asString());
	}

}