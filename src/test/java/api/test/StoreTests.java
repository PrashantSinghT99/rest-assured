package api.test;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import api.payloads.PlaceOrder;
import com.github.javafaker.Faker;
import api.endpoints.StoreEndpoints;
import api.endpoints.UserEndpoints;
import io.restassured.response.Response;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class StoreTests {
	public Logger logger;
	PlaceOrder order;
	Faker faker;
	@BeforeClass
	public void setupData() {
		faker = new Faker();
		order = new PlaceOrder();
		order.setId(faker.number().randomDigitNotZero());
		order.setPetId(faker.number().randomDigitNotZero());
		order.setComplete(faker.bool().bool());
		order.setQuantity(faker.number().randomDigitNotZero());
		order.setStatus(faker.options().option(null, "placed", "approved", "delivered"));
		Date futureDate = faker.date().future(10, TimeUnit.DAYS);
		Instant instant = futureDate.toInstant();

		logger = LogManager.getLogger(this.getClass());
	}

	@Test
	public void testGetPetInventory() {
		logger.info("************** Reading Store Inventory Info ******************");
		Response response = StoreEndpoints.getPetInventoryStatus();
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("************** Store Inventory Info Displayed ******************");
	}

	@Test(priority=1)
	public void createPlaceOrder() {
		logger.info("************** Create a order Info ******************");
		Response response = StoreEndpoints.postPlaceOrder(order);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("************** Order created Info Displayed ******************");
	}

	@Test
	public void getOrderById() {
		logger.info("************** Get Order By Id  ******************");
		Response response = StoreEndpoints.getOrderById(order.getId());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("************** Order Info Displayed ******************");
	}
	@Test(priority=2)
	public void deleteOrderById() {
		logger.info("************** Delete Order By Id  ******************");
		Response response = StoreEndpoints.deleteOrderById(order.getId());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("************** Order Deleted Info Displayed ******************");
	}
	
	
	
	
}

