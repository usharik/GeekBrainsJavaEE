package ru.geekbrains.servlet.entity;

import java.util.HashMap;
import java.util.Map;

public class Order {

    private String id;
    private Map<Product, Integer> orderMap;

    public Order() {}

    public Order(String id) {
        this.id = id;
        orderMap = new HashMap<>();
    }

    public void addProduct(Product product, int count) {
        if (orderMap.containsKey(product)) {
            orderMap.compute(product, (good, amount) -> amount + count);
        } else {
            orderMap.put(product, count);
        }
    }

    public void setProductCount(Product product, int count) {
        orderMap.put(product, count);
    }

    public void removeProduct(Product product) {
        orderMap.remove(product);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
