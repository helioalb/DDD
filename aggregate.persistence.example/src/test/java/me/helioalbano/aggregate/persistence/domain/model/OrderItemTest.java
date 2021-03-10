package me.helioalbano.aggregate.persistence.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import me.helioalbano.aggregate.persistence.domain.model.exceptions.OrderItemMissingProductException;
import me.helioalbano.aggregate.persistence.domain.model.exceptions.OrderItemNegativeQuantityException;
import me.helioalbano.aggregate.persistence.domain.model.exceptions.OrderItemZeroQuantityException;

public class OrderItemTest {
	@Test
	public void testConstructionOfOrderItem() {
		Product book = new Product("Livro", new Money(Currency.BRL, 10000));

		OrderItem item = new OrderItem(book, 3);

		assertEquals("Livro", item.getProduct().getName());
		assertEquals(3, item.getQuantity());
	}

	@Test
	public void testConstructionOfOrderItemWithoutProduct() {
		Exception exception =
				assertThrows(OrderItemMissingProductException.class, () -> {
					new OrderItem(null, 2);
		});

		String expectedMessage = "O item está sem um produto associado";

		assertEquals(expectedMessage, exception.getMessage());
	}

	@Test
	public void testConstructionOfOrderItemWithNegativeQuantity() {
		Product book = new Product("Livro", new Money(Currency.BRL, 10000000));

		Exception exception =
				assertThrows(OrderItemNegativeQuantityException.class, () -> {
					new OrderItem(book, -2);
		});

		String expectedMessage = "Quantidade de items não pode ser negativa";
		assertEquals(expectedMessage, exception.getMessage());
	}

	@Test
	public void testConstructionOfOrderItemWithZeroQuantity() {
		Product book = new Product("Livro", new Money(Currency.BRL, 10000000));

		Exception exception =
				assertThrows(OrderItemZeroQuantityException.class, () -> {
					new OrderItem(book, 0);
		});

		String message = "Quantidade de items não pode ser igual a zero";
		assertEquals(message, exception.getMessage());
	}

	@Test
	public void testOrderItemCost() {
		Product book = new Product("Livro", new Money(Currency.BRL, 100));
		OrderItem item = new OrderItem(book, 2);
		assertEquals(200, item.cost().getAmount());
	}
}
