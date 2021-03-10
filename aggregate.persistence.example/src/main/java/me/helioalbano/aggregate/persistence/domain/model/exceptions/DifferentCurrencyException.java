package me.helioalbano.aggregate.persistence.domain.model.exceptions;

public class DifferentCurrencyException extends RuntimeException {
	public DifferentCurrencyException(String message) {
		super(message);
	}
}
