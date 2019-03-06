package ru.geekbrains.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.servlet.entity.Product;
import ru.geekbrains.servlet.repository.ProductRepository;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;

@ManagedBean(name = "products")
@SessionScoped
public class ProductsBean {

    private static final Logger logger = LoggerFactory.getLogger(ProductsBean.class);

    private Product product;

    @Inject
    private ProductRepository productRepository;

    public String getId() {
        return product.getId();
    }

    public void setId(String id) {
        product.setId(id);
    }

    public String getName() {
        return product.getName();
    }

    public void setName(String name) {
        product.setName(name);
    }

    public int getPrice() {
        return product.getPrice();
    }

    public void setPrice(int price) {
        product.setPrice(price);
    }

    public Collection<Product> getProductList() {
        return productRepository.getAll();
    }

    public void deleteAction(Product product) {
        logger.info("Delete product with id {} and name {}", product.getId(), product.getName());
        productRepository.delete(product);
    }

    public String addAction() {
        logger.info("Add product action");

        product = new Product();
        return "/product.xhtml?faces-redirect=true";
    }

    public String editAction(Product product) {
        logger.info("Edit product with id {} and name {}", product.getId(), product.getName());

        this.product = product;
        return "/product.xhtml?faces-redirect=true";
    }

    public String saveProduct() {
        productRepository.merge(product);
        return "/products.xhtml?faces-redirect=true";
    }
}
