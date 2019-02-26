package ru.geekbrains.servlet;

import ru.geekbrains.servlet.entity.Product;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Класс-заглушка для репозитория
 * через несколько уроков мы его заменим на
 * полноценный JPA репозиторий
 */
public class ProductRepository {

    private Map<String, Product> productMap = new LinkedHashMap<>();

    public ProductRepository() {
        this.add(new Product("1", "Pen", 50));
        this.add(new Product("2", "Pencil", 150));
        this.add(new Product("3", "Textbook", 200));
        this.add(new Product("4", "Paper", 500));
    }

    public Collection<Product> getAll() {
        return productMap.values();
    }

    public Product getById(String id) {
        return productMap.get(id);
    }

    public void add(Product product) {
        productMap.put(product.getId(), product);
    }
}
