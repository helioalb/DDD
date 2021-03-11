package me.helioalbano.aggregate.persistence.usecases.port;

import me.helioalbano.aggregate.persistence.domain.model.Order;

public interface OrderRepository {
	public Boolean save(Order order);
	public Order findByNumber(Integer number);
}
