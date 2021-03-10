package me.helioalbano.aggregate.persistence.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import me.helioalbano.aggregate.persistence.domain.model.exceptions.ProductMissingNameException;
import me.helioalbano.aggregate.persistence.domain.model.exceptions.ProductMissingPriceException;

public class ProductTest {

    @Test
    public void testConstructionOfProduct() {
        Money price = new Money(Currency.BRL, 10000);

        Product book = new Product("Domain-Driven Design Destiled", price);

        assertEquals("Domain-Driven Design Destiled", book.getName());
        assertEquals(10000, book.getPrice().getAmount());
    }

    @Test
    public void testConstructionOfProductWithoutPrice() {
        Exception exception =
        		assertThrows(ProductMissingPriceException.class, () -> {
        			new Product("Clean code", null);
        		});

        String expectedMessage = "O preço do produto deve ser informado";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testConstructionOfProductWithoutName() {
        Exception exception =
        		assertThrows(ProductMissingNameException.class, () -> {
        			Money price = new Money(Currency.BRL, 10000);
        			new Product(null, price);
        		});

        String expectedMessage = "O nome do produto deve ser informado";
        assertEquals(expectedMessage, exception.getMessage());
    }
}
