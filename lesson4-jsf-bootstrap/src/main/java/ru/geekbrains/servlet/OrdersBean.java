package ru.geekbrains.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.servlet.entity.Order;
import ru.geekbrains.servlet.repository.OrderRepository;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;

@Named("orders")
@SessionScoped
public class OrdersBean {

    private static final Logger logger = LoggerFactory.getLogger(OrdersBean.class);

    private Order order;

    @Inject
    private OrderRepository orderRepository;

    public String getId() {
        return order.getId();
    }

    public void setId(String id) {
        order.setId(id);
    }

    public Collection<Order> getOrderList() {
        return orderRepository.getAll();
    }

    public void deleteAction(Order order) {
        logger.info("Delete order with id {} and name {}", order.getId());
        orderRepository.delete(order);
    }

    public String addAction() {
        logger.info("Add order action");

        orderRepository.merge(new Order());
        return "/orders.xhtml?faces-redirect=true";
    }

    public String editAction(Order order) {
        logger.info("Edit order with id {} and name {}", order.getId());

        this.order = order;
        return "/order.xhtml?faces-redirect=true";
    }

    public String saveOrder() {
        orderRepository.merge(order);
        return "/orders.xhtml?faces-redirect=true";
    }
}
