package api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import api.payloads.PlaceOrder;

public class StoreEndpoints {

	public static Response getPetInventoryStatus() {

		Response response = given().when().get(Routes.get_store_url);

		return response;
	}

	public static Response postPlaceOrder(PlaceOrder order) {

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(order).when()
				.post(Routes.create_order_url);

		return response;
	}

	public static Response getOrderById(int order) {
		Response response = given().pathParam("orderId", order).when().get(Routes.get_purchaseOrder_url);

		return response;
	}

	public static Response deleteOrderById(int order) {
		Response response = given().pathParam("orderId", order).when().delete(Routes.get_deleteOrder_url);
		return response;
	}

}
