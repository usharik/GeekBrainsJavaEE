package ru.geekbrains;


import ru.geekbrains.persistance.CategoryRepository;
import ru.geekbrains.persistance.ProductRepository;
import ru.geekbrains.persistance.entity.Category;
import ru.geekbrains.persistance.entity.Product;
import ru.geekbrains.representation.ProductRepr;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Промежуточный сервис для перекладки данных из JPA сущностей в классы представления.
 * JPA сущности в состоянии attached крайне не желательно использовать для описания
 * возвращаемых конечной точкой значений в REST сервисах
 *
 * Также это хороший пример Stateless сервиса
 */
@Stateless
public class ProductService {

    @Inject
    private ProductRepository productRepository;

    @Inject
    private CategoryRepository categoryRepository;

    public List<ProductRepr> getByCategory(long id) {
        return productRepository.getByCategory(id)
                .stream()
                .map(ProductRepr::new)
                .collect(Collectors.toList());
    }

    public List<ProductRepr> getAll() {
        return productRepository.getAll()
                .stream()
                .map(ProductRepr::new)
                .collect(Collectors.toList());
    }

    public ProductRepr getById(long id) {
        Product product = productRepository.getById(id);
        if (product == null) {
            throw new NotFoundException(String.format("Product with id %d not found", id));
        }
        return new ProductRepr(product);
    }

    public long addProduct(ProductRepr productRepr) {
        Category category = categoryRepository.getById(productRepr.getCategory().getId());
        Product product = new Product(productRepr.getName(), productRepr.getPrice(), category);
        product = productRepository.merge(product);
        return product.getId();
    }

    public void removeProduct(long id) {
        Product product = productRepository.getById(id);
        if (product == null) {
            throw new NotFoundException(String.format("Product with id %d not found", id));
        }
        productRepository.remove(product);
    }

    public long count() {
        return productRepository.count();
    }
}
