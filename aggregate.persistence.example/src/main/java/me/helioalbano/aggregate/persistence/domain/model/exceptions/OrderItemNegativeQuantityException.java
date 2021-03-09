package me.helioalbano.aggregate.persistence.domain.model.exceptions;

public class OrderItemNegativeQuantityException extends RuntimeException {
	public OrderItemNegativeQuantityException(String message) {
		super(message);
	}
}
