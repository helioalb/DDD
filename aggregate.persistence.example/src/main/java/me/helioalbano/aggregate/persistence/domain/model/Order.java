package me.helioalbano.aggregate.persistence.domain.model;

import java.util.Collections;
import java.util.List;

public class Order {
	private Integer number;
	private List<OrderItem> items;
	private Money totalCost;

	public Order(Integer number, List<OrderItem> items) {
		this.number = number;
		this.items = items;
		updateTotalCost(items);
	}

	private void updateTotalCost(List<OrderItem> items) {
		totalCost = new Money();

		for (OrderItem item : items) {
			totalCost = totalCost.plus(item.cost());
		}
	}

	public List<OrderItem> getItems() {
		return Collections.unmodifiableList(items);
	}

	public Integer numberOfItems() {
		return items.size();
	}

	public void addItem(OrderItem item) {
		items.add(item);
		totalCost = totalCost.plus(item.cost());
	}

	public Money totalCost() {
		return totalCost;
	}

	public void removeItem(OrderItem item) {
		items.remove(item);
		totalCost = totalCost.minus(item.cost());
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer orderNumber) {
		this.number = orderNumber;
	}
}
