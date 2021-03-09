package me.helioalbano.aggregate.persistence.domain.model;

import me.helioalbano.aggregate.persistence.domain.model.exceptions.ProductMissingNameException;
import me.helioalbano.aggregate.persistence.domain.model.exceptions.ProductMissingPriceException;

public class Product {
    private String name;
    private Money price;

    public Product(String name, Money price) {
        this.setName(name);
        this.setPrice(price);
    }

    private void setName(String name) {
    	if (name == null) throw new ProductMissingNameException(
    			"O nome do produto deve ser informado");
    	this.name = name;
    }

    private void setPrice(Money price) {
        if (price == null) throw new ProductMissingPriceException(
        		"O preço do produto deve ser informado");
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }
}
