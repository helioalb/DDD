package me.helioalbano.aggregate.persistence.example.usecases;

import java.util.HashMap;

import me.helioalbano.aggregate.persistence.domain.model.Order;
import me.helioalbano.aggregate.persistence.example.usecases.port.OrderRepository;

public class InMemoryOrderRepository implements OrderRepository {

	private HashMap<Integer, Order> db;

	public InMemoryOrderRepository() {
		db = new HashMap<Integer, Order>();
	}

	@Override
	public Boolean save(Order order) {
		if (!db.containsKey(order.getNumber())) {
			db.put(order.getNumber(), order);

			return true;
		}
		return false;
	}

	@Override
	public Order findByNumber(Integer number) {
		return db.get(number);
	}

}
