package me.helioalbano.aggregate.persistence.domain.model.exceptions;

public class ProductMissingPriceException extends RuntimeException {
    public ProductMissingPriceException(String message) {
        super(message);
    }
}
