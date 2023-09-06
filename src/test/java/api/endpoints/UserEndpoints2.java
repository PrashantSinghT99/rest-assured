package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import api.payloads.User;
import io.restassured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints2 {

	// fetching url from properties file routes is name of the property file
	static ResourceBundle getURL() {
		ResourceBundle routes = ResourceBundle.getBundle("routes");
		return routes;
	}

	public static Response createUser(User payload) {

		String posturl = getURL().getString("post_url");

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when()
				.post(posturl);

		return response;
	}

	public static Response readUser(String username) {
		String geturl = getURL().getString("get_url");

		Response response = given().pathParam("username", username).when().get(geturl);
		return response;
	}

	public static Response updateUser(String username, User payload) {

		String updateUrl = getURL().getString("update_url");
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.pathParam("username", username).body(payload).when().put(updateUrl);

		return response;
	}

	public static Response deleteUser(String username) {
		String deleteurl = getURL().getString("delete_url");
		Response response = given().pathParam("username", username).when().delete(deleteurl);
		return response;
	}
}
