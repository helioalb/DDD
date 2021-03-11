package me.helioalbano.aggregate.persistence.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class OrderTest {

	@Test
	public void testConstructionOfOrder() {
		Order order = new Order(123, new ArrayList<OrderItem>());

		assertEquals(0, order.numberOfItems());
	}

	@Test
	public void testCounterOfOrder() {
		Order order = createOrderWithTwoItems();

		assertEquals(2, order.numberOfItems());
	}

	@Test
	public void testTotalCostOfOrder() {
		Order order = createOrderWithTwoItems();

		assertEquals(50300, order.totalCost().getAmount());
	}

	@Test
	public void testRemoveOfItemFromOrder() {
		Order order = createOrderWithTwoItems();

		// Before remove
		assertEquals(50300, order.totalCost().getAmount());
		assertEquals(2, order.numberOfItems());

		//After remove
		OrderItem firstItem = order.getItems().get(0);

		order.removeItem(firstItem);

		assertEquals(300, order.totalCost().getAmount());
		assertEquals(1, order.numberOfItems());

	}

	private Order createOrderWithTwoItems() {
		Product book = new Product("Livro", new Money(Currency.BRL, 25000));
		Product pen = new Product("Caneta", new Money(Currency.BRL, 300));

		OrderItem twoBooks = new OrderItem(book, 2);
		OrderItem onePen = new OrderItem(pen, 1);

		Order order = new Order(1234, new ArrayList<OrderItem>());

		order.addItem(twoBooks);
		order.addItem(onePen);

		return order;
	}
}
