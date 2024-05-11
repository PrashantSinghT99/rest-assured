package api.payloads;

import java.time.LocalDateTime;

public class PlaceOrder {

	private int id;
	private int petId;
	private int quantity;
	private String status;
	private boolean complete;
//	private LocalDateTime shipDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPetId() {
		return petId;
	}

	public void setPetId(int petId) {
		this.petId = petId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

//	public LocalDateTime getShipDate() {
//		return shipDate;
//	}
//
//	public void setShipDate(LocalDateTime shipDate) {
//		this.shipDate = shipDate;
//	}
}