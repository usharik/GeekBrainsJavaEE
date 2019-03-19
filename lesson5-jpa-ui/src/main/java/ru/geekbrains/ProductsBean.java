package ru.geekbrains;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persistance.CategoryRepository;
import ru.geekbrains.persistance.entity.Category;
import ru.geekbrains.persistance.entity.Product;
import ru.geekbrains.persistance.ProductRepository;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;


@Named("products")
@SessionScoped
public class ProductsBean implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(ProductsBean.class);

    @Inject
    private ProductRepository productRepository;

    @Inject
    private CategoryRepository categoryRepository;

    @Inject
    private CategoriesBean categoriesBean;

    @Inject
    private CartViewBean cartViewBean;

    // наличие такого поля для хранения текущего элемента является стандартным для JSF
    private Product product;

    private Collection<Product> productList;

    public void preloadProductList(ComponentSystemEvent event) throws AbortProcessingException {
        boolean postback = FacesContext.getCurrentInstance().isPostback();
        boolean ajaxRequest = FacesContext.getCurrentInstance().getPartialViewContext().isAjaxRequest();

        logger.info("Preloading products from database postback={}, ajaxrequest={}", postback, ajaxRequest);

        if (categoriesBean.getCategory() != null) {
            logger.info("Category id {}", categoriesBean.getCategory().getId());
            productList = productRepository.getByCategory(categoriesBean.getCategory().getId());
            return;
        }
        productList = productRepository.getAll();
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getCategoryId() {
        if (product == null || product.getCategory() == null) {
            logger.warn("Product is null or empty");
            return "-1L";
        }
        return String.valueOf(product.getCategory().getId());
    }

    public void setCategoryId(String id) {
        if (id == null || id.isEmpty()) {
            logger.warn("Set category id is null or empty");
            return;
        }
        Category newCategory = categoryRepository.getById(Long.valueOf(id));
        if (newCategory != null) {
            product.setCategory(newCategory);
        }
    }

    public Collection<Product> getProductList() {
        logger.info("Get product list");

        return productList;
    }

    public void newProductAction() {
        this.product = new Product();
        this.product.setCategory(categoriesBean.getCategory());
    }

    public void deleteAction(Product product) {
        logger.info("Delete product");

        productRepository.remove(product);
    }

    public void addToCart(Product product) {
        cartViewBean.addToCard(product);
    }

    public String logout() {
        logger.info("Logging out");
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }


    public void saveProduct() {
        productRepository.merge(product);
    }

    public void setCategoriesBean(CategoriesBean categoriesBean) {
        this.categoriesBean = categoriesBean;
    }
}
