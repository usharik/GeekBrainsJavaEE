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

    public void init() {
        logger.info("Initializing product data");
        try {
            if (getAll().isEmpty()) {
                tran.begin();
                entityManager.merge(new Product("Pen", 50, 1));
                entityManager.merge(new Product("Pencil", 150, 1));
                entityManager.merge(new Product("Textbook", 201, 1));
                entityManager.merge(new Product("Paper", 500,1));
                entityManager.merge(new Product("Pen", 50, 1));
                entityManager.merge(new Product("Eraser", 150, 1));
                entityManager.merge(new Product("Marker", 200, 1));
                entityManager.merge(new Product("Sticks", 500, 2));
                entityManager.merge(new Product("Brash", 50, 2));
                entityManager.merge(new Product("Pencil", 150, 2));
                entityManager.merge(new Product("Textbook", 200, 2));
                entityManager.merge(new Product("Paper", 500, 3));
                entityManager.merge(new Product("Pen", 50, 3));
                entityManager.merge(new Product("Pencil", 150, 3));
                entityManager.merge(new Product("Textbook", 200, 3));
                entityManager.merge(new Product("Paper", 500, 3));
                tran.commit();
            }
        } catch (Throwable thr) {
            logger.error("Error", thr);
        }
    }

    @Override
    public Product getById(long id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    public Collection<Product> getAll() {
        return entityManager.createQuery("select p from Product p").getResultList();
    }

    public Collection<Product> getByCategory(long categoryId) {
        return entityManager.createQuery("select p from Product p where p.categoryId = :categoryId")
                .setParameter("categoryId", categoryId)
                .getResultList();
    }

    public Product getById(Long id) {
        return (Product) entityManager.createQuery("select p from Product p where p.id = :id")
                .setParameter("id", id)
                .getSingleResult();
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
