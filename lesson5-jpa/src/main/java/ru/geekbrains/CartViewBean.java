package ru.geekbrains;

import ru.geekbrains.persistance.entity.Product;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("cart")
@SessionScoped
public class CartViewBean implements Serializable {

    @EJB
    private CartBean cartBean;

    public void addToCard(Product product) {
        cartBean.getProducts().add(product);
    }

    public List<Product> getProducts() {
        return cartBean.getProducts();
    }
}
