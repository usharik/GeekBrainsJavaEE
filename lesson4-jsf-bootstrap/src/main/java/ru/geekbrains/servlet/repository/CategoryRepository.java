package ru.geekbrains.servlet.repository;

import ru.geekbrains.servlet.entity.Category;

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
public class CategoryRepository {

    private AtomicInteger sequence = new AtomicInteger();

    private Map<String, Category> categoryMap = Collections.synchronizedMap(new LinkedHashMap<>());

    public CategoryRepository() {
        this.merge(new Category("1", "Books"));
        this.merge(new Category("2", "Planes"));
        this.merge(new Category("3", "Fruits"));
        this.merge(new Category("4", "Clothes"));
        sequence.set(categoryMap.size());
    }

    public Collection<Category> getAll() {
        return categoryMap.values();
    }

    public Category getById(String id) {
        return categoryMap.get(id);
    }

    public void merge(Category category) {
        if (category.getId() == null || !categoryMap.containsKey(category.getId())) {
            category.setId(String.valueOf(sequence.incrementAndGet()));
        }
        categoryMap.put(category.getId(), category);
    }

    public void delete(Category category) {
        categoryMap.remove(category.getId());
    }
}
