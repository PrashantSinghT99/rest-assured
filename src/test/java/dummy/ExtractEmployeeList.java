package dummy;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class ExtractEmployeeList {
	public static void main(String[] args) {
		RestAssured.baseURI = "http://localhost:4000/user"; // Set your API base URL
		String bearerToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY1YTZhZDg0NDNlYTYwMzlhY2VmOTIxZiIsImlhdCI6MTcxMTQ3MTUxOSwiZXhwIjoxNzExNDc4NzE5fQ.Qhtvmttg0OOQhzlZWCfBl5bLXAzrSNjdw-vcL2r2JaI";
		// Send a GET request to retrieve the JSON
		String response = RestAssured.given().header("Authorization", "Bearer " + bearerToken).when().get("/employees")
				.then().extract().response().asString();

		// Parse the JSON response using JsonPath
		JsonPath jsonPath = new JsonPath(response);
		List<String> employees = jsonPath.getList("customer.employee");

		// Print the list of employees
		System.out.println("List of Employees:");
		for (String employee : employees) {
			System.out.println(employee);
		}
	}

	@Test
	public static void extractList() {
		RestAssured.baseURI = "http://localhost:3000";
		String response = RestAssured.given().accept(ContentType.JSON).get("/employees").then().extract().response()
				.asString();
		JsonPath jsonPath = new JsonPath(response);
		List<Map<String, Object>> employees = jsonPath.getList("company.employees");
		System.out.println("List of Employees:" + employees);
		for (Map<String, Object> employee : employees) {
			Integer id = (Integer) employee.get("id");
			String name = (String) employee.get("name");
			System.out.println("ID: " + id + ", Name: " + name);
		}

	}

//urlEncodingEnabled(false)
//relaxedHTTPSValidation
	@Test
	public static void matchJson() {

		RestAssured.baseURI = "http://localhost:3000";
		RestAssured.given().accept(ContentType.JSON).get("/employees").then().body("company.employees[0].name",
				Matchers.containsString("John Doe"));
	}

	@Test
	public static void extractIds() {
		RestAssured.baseURI = "http://localhost:3000";
		String response = RestAssured.given().accept(ContentType.JSON).log().all().get("/employees").then().log()
				.ifError().statusCode(200).extract().response().asString();
		JsonPath jsonPath = new JsonPath(response);
		List<Integer> employees = jsonPath.getList("company.employees.id");
		List<Integer> filteredEmployees = employees.stream().filter(employee -> employee > 15 && employee < 30)
				.collect(Collectors.toList());

		System.out.println(filteredEmployees);
	}

	@Test
	public static void extractFile() {
		RestAssured.baseURI = "http://localhost:3000";
		byte[] arr = RestAssured.given().log().all().get("/file").then().log().ifError().statusCode(200).extract()
				.response().asByteArray();
//		String arr = RestAssured.given().log().all().get("/file").then().log().ifError().statusCode(200).extract()
//				.response().asString();

		System.out.println(arr.length);

	}
}

//ALSO WRITE CODE TO GET JUST THE 2ND PARAMETER EVERYTIME.