package ru.geekbrains;

import ru.geekbrains.entity.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Класс-заглушка для репозитория
 * через несколько уроков мы его заменим на
 * полноценный JPA репозиторий
 */
@Named
@ApplicationScoped
public class ProductRepository {

    private Map<String, Product> productMap = new LinkedHashMap<>();

    public ProductRepository() {
        this.add(new Product("1", "Pen", 50));
        this.add(new Product("2", "Pencil", 150));
        this.add(new Product("3", "Textbook", 200));
        this.add(new Product("4", "Paper", 500));
        this.add(new Product("5", "Pen", 50));
        this.add(new Product("6", "Eraser", 150));
        this.add(new Product("7", "Marker", 200));
        this.add(new Product("8", "Sticks", 500));
        this.add(new Product("9", "Brash", 50));
        this.add(new Product("10", "Pencil", 150));
        this.add(new Product("11", "Textbook", 200));
        this.add(new Product("12", "Paper", 500));
        this.add(new Product("13", "Pen", 50));
        this.add(new Product("14", "Pencil", 150));
        this.add(new Product("15", "Textbook", 200));
        this.add(new Product("16", "Paper", 500));
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

    public void save(Product product) {
        productMap.put(product.getId(), product);
    }

    public void delete(Product product) {
        productMap.remove(product.getId());
    }
}
