package ru.geekbrains.persistance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persistance.entity.Product;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.Collection;


@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ProductRepository extends AbstractRepository<Product> implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @Override
    public Product getById(long id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    public Collection<Product> getAll() {
        logger.info("Fetching All Products");

        // Пример использования Criteria API
        // Эквивалентен JPQL запросу entityManager.createQuery("select p from Product p").getResultList();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> from = query.from(Product.class);
        query.select(from);
        return entityManager.createQuery(query).getResultList();
    }

    @SuppressWarnings("unchecked")
    public Collection<Product> getByCategory(long categoryId) {
        logger.info("Fetching Products by Category with id {}", categoryId);
        return entityManager.createQuery("select p from Product p where p.category.id = :id")
                .setParameter("id", categoryId)
                .getResultList();
    }
}
