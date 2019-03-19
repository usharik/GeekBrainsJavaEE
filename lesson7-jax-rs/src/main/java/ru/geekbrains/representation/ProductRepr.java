package ru.geekbrains.representation;

import ru.geekbrains.persistance.entity.Product;

import java.math.BigDecimal;

public class ProductRepr {

    private long id;

    private String name;

    private BigDecimal price;

    private CategoryRepr category;

    public ProductRepr() {
    }

    public ProductRepr(Product product) {
        id = product.getId();
        name = product.getName();
        price = product.getPrice();
        category = new CategoryRepr(product.getCategory());
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CategoryRepr getCategory() {
        return category;
    }

    public void setCategory(CategoryRepr category) {
        this.category = category;
    }
}
