package ru.geekbrains.servlet.repository;

import ru.geekbrains.servlet.entity.Product;

import javax.enterprise.context.ApplicationScoped;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Класс-заглушка для репозитория
 * через несколько уроков мы его заменим на
 * полноценный JPA репозиторий
 */
@ApplicationScoped
public class ProductRepository {

    private AtomicInteger sequence = new AtomicInteger();

    private Map<String, Product> productMap = Collections.synchronizedMap(new LinkedHashMap<>());

    public ProductRepository() {
        this.merge(new Product("1", "Pen", 50));
        this.merge(new Product("2", "Pencil", 150));
        this.merge(new Product("3", "Textbook", 200));
        this.merge(new Product("4", "Paper", 500));
        sequence.set(productMap.size());
    }

    public Collection<Product> getAll() {
        return productMap.values();
    }

    public Product getById(String id) {
        return productMap.get(id);
    }

    public void merge(Product product) {
        if (product.getId() == null || !productMap.containsKey(product.getId())) {
            product.setId(String.valueOf(sequence.incrementAndGet()));
        }
        productMap.put(product.getId(), product);
    }

    public void delete(Product product) {
        productMap.remove(product.getId());
    }
}
