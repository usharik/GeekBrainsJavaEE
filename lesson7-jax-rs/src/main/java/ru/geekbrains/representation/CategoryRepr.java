package ru.geekbrains.representation;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.geekbrains.persistance.entity.Category;

public class CategoryRepr {

    private long id;

    @JsonProperty(required = false)
    private String name;

    public CategoryRepr() {
    }

    public CategoryRepr(Category category) {
        id = category.getId();
        name = category.getName();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
