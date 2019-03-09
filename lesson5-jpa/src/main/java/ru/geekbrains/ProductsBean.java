package ru.geekbrains;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persistance.entity.Product;
import ru.geekbrains.persistance.ProductRepository;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Collection;


@ManagedBean(name = "products")
@SessionScoped // будьте осторожны с бинами Scoped. Они есть как в JSF так и в CDI
public class ProductsBean {

    private static final Logger logger = LoggerFactory.getLogger(ProductsBean.class);

    @Inject
    private ProductRepository productRepository;

    @ManagedProperty(value="#{categories}")
    private CategoriesBean categoriesBean;

    // наличие такого поля для хранения текущего элемента является стандартным для JSF
    private Product product;

    public String getId() {
        return String.valueOf(product.getId());
    }

    public void setId(String id) {
        product.setId(Long.valueOf(id));
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

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Collection<Product> getProductList() {
        if (categoriesBean.getCategory() != null) {
            return productRepository.getByCategory(Long.valueOf(categoriesBean.getCategory().getId()));
        }
        return productRepository.getAll();
    }

    public void deleteAction(Product product) {
        productRepository.remove(product);
    }

    @Transactional
    public String saveProduct() {
        productRepository.merge(product);
        return "/index.xhtml?faces-redirect=true"; // после сохранения продукта возвращаемся на главную страницу
    }

    public void setCategoriesBean(CategoriesBean categoriesBean) {
        this.categoriesBean = categoriesBean;
    }
}
