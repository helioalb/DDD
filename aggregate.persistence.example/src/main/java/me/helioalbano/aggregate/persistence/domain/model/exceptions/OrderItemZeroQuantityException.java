package me.helioalbano.aggregate.persistence.domain.model.exceptions;

public class OrderItemZeroQuantityException extends RuntimeException {
	public OrderItemZeroQuantityException(String message) {
		super(message);
	}
}
