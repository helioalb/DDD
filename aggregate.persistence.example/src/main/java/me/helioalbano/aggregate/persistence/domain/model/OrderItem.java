package me.helioalbano.aggregate.persistence.domain.model;

import me.helioalbano.aggregate.persistence.domain.model.exceptions.OrderItemMissingProductException;
import me.helioalbano.aggregate.persistence.domain.model.exceptions.OrderItemNegativeQuantityException;
import me.helioalbano.aggregate.persistence.domain.model.exceptions.OrderItemZeroQuantityException;

public class OrderItem {
	private Product product;
	private Integer quantity;

	public OrderItem(Product product, Integer quantity) {
		this.setProduct(product);
		this.setQuantity(quantity);
	}

	private void setProduct(Product product) {
		if (product == null) throw new OrderItemMissingProductException(
					"O item está sem um produto associado");

		this.product = product;
	}

	public void setQuantity(Integer quantity) {
		if (quantity < 0) throw new OrderItemNegativeQuantityException(
				"Quantidade de items não pode ser negativa");

		if (quantity == 0) throw new OrderItemZeroQuantityException(
				"Quantidade de items não pode ser igual a zero");

		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public Money cost() {
		return product.getPrice().multiply(quantity);
	}
}
