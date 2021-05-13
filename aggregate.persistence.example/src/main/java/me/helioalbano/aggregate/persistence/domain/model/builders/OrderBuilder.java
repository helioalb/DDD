package me.helioalbano.aggregate.persistence.domain.model.builders;

import java.util.ArrayList;

import me.helioalbano.aggregate.persistence.domain.model.Currency;
import me.helioalbano.aggregate.persistence.domain.model.Money;
import me.helioalbano.aggregate.persistence.domain.model.Order;
import me.helioalbano.aggregate.persistence.domain.model.OrderItem;
import me.helioalbano.aggregate.persistence.domain.model.Product;

public class OrderBuilder {
	private Order order;

	public OrderBuilder() {
		order = new Order(1, new ArrayList<OrderItem>());
	}

	public OrderBuilder withOrderNumber(Integer orderNumber) {
		order.setNumber(orderNumber);
		return this;
	}

	public OrderBuilder addProduct(String name, Integer price, Integer qtd) {
		Product product = new Product(name, new Money(Currency.BRL, price));

		OrderItem orderItem = new OrderItem(product, qtd);

		order.addItem(orderItem);

		return this;
	}

	public Order build() {
		return order;
	}

}
