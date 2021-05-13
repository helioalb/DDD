package me.helioalbano.aggregate.persistence.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import me.helioalbano.aggregate.persistence.domain.model.Order;
import me.helioalbano.aggregate.persistence.domain.model.builders.OrderBuilder;
import me.helioalbano.aggregate.persistence.usecases.port.OrderRepository;

public class OrderPersistenceTest {
	private OrderRepository repo;

	@BeforeEach
	public void setUp() {
		repo = new InMemoryOrderRepository();
	}

	@Test
	public void testPersistenceAndFindOfOrder() {
		Integer orderNumber = 123;

		// Given
		Order order = new OrderBuilder().withOrderNumber(orderNumber)
				.addProduct("Livro", 25000, 2)
				.addProduct("Caneta", 300, 1)
				.build();

		// When
		repo.save(order);

		Order savedOrder = repo.findByNumber(order.getNumber());

		// Then
		assertEquals(orderNumber, savedOrder.getNumber());
	}
}
