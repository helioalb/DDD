package me.helioalbano.aggregate.persistence.domain.model.exceptions;

public class ProductMissingNameException extends RuntimeException {
	public ProductMissingNameException(String message) {
		super(message);
	}
}
