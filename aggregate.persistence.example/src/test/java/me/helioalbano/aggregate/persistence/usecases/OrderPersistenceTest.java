package me.helioalbano.aggregate.persistence.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import me.helioalbano.aggregate.persistence.domain.model.Currency;
import me.helioalbano.aggregate.persistence.domain.model.Money;
import me.helioalbano.aggregate.persistence.domain.model.Order;
import me.helioalbano.aggregate.persistence.domain.model.OrderItem;
import me.helioalbano.aggregate.persistence.domain.model.Product;
import me.helioalbano.aggregate.persistence.usecases.port.OrderRepository;

public class OrderPersistenceTest {

	@Test
	public void testPersistenceAndFindOfOrder() {
		Integer orderNumber = 1234;

		Product book = new Product("Livro", new Money(Currency.BRL, 25000));
		Product pen = new Product("Caneta", new Money(Currency.BRL, 300));

		OrderItem twoBooks = new OrderItem(book, 2);
		OrderItem onePen = new OrderItem(pen, 1);

		Order order = new Order(orderNumber, new ArrayList<OrderItem>());

		order.addItem(twoBooks);
		order.addItem(onePen);

		OrderRepository repo = new InMemoryOrderRepository();

		assertTrue(repo.save(order));


		Order savedOrder = repo.findByNumber(order.getNumber());

		assertEquals(orderNumber, savedOrder.getNumber());
	}
}
