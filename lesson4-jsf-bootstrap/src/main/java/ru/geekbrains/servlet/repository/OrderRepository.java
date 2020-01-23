package ru.geekbrains.servlet.repository;

import ru.geekbrains.servlet.entity.Order;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Класс-заглушка для репозитория
 * через несколько уроков мы его заменим на
 * полноценный JPA репозиторий
 */
@ApplicationScoped
public class OrderRepository {

    private AtomicInteger sequence = new AtomicInteger();

    private Map<String, Order> orderMap = Collections.synchronizedMap(new LinkedHashMap<>());

    public OrderRepository() {
        this.merge(new Order("1"));
        this.merge(new Order("2"));
        this.merge(new Order("3"));
        this.merge(new Order("4"));
        sequence.set(orderMap.size());
    }

    public Collection<Order> getAll() {
        return orderMap.values();
    }

    public Order getById(String id) {
        return orderMap.get(id);
    }

    public void merge(Order order) {
        if (order.getId() == null || !orderMap.containsKey(order.getId())) {
            order.setId(String.valueOf(sequence.incrementAndGet()));
        }
        orderMap.put(order.getId(), order);
    }

    public void delete(Order order) {
        orderMap.remove(order.getId());
    }
}
