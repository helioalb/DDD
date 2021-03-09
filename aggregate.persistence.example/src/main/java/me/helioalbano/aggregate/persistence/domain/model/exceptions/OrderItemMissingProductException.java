package me.helioalbano.aggregate.persistence.domain.model.exceptions;

public class OrderItemMissingProductException extends RuntimeException {
	public OrderItemMissingProductException(String message) {
		super(message);
	}
}
