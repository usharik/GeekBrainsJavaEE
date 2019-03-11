package ru.geekbrains.persistance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persistance.entity.Product;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.transaction.*;
import java.util.Collection;

/**
 * Класс-заглушка для репозитория
 * через несколько уроков мы его заменим на
 * полноценный JPA репозиторий
 */
@Stateless
@TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class ProductRepository extends AbstractRepository<Product> {

    private static Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @Resource
    protected UserTransaction tran;

    @Override
    public Product getById(long id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Product> getAll() {
        logger.info("Fetching All Products");
        return entityManager.createQuery("select p from Product p").getResultList();
    }

    @SuppressWarnings("unchecked")
    public Collection<Product> getByCategory(long categoryId) {
        logger.info("Fetching Products by Category with id {}", categoryId);
        return entityManager.createQuery("select p from Product p where p.category.id = :id")
                .setParameter("id", categoryId)
                .getResultList();
    }

    @Override
    protected void beginTran() throws SystemException, NotSupportedException {
        tran.begin();
    }

    @Override
    protected void commitTran() throws HeuristicRollbackException, RollbackException, HeuristicMixedException, SystemException {
        tran.commit();
    }
}
