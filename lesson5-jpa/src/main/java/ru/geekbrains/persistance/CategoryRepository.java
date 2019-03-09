package ru.geekbrains.persistance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persistance.entity.Category;

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
public class CategoryRepository extends AbstractRepository {

    private static Logger logger = LoggerFactory.getLogger(CategoryRepository.class);

    @Resource
    protected UserTransaction tran;

    public void init() {
        logger.info("Initializing product data");
        try {
            if (getAll().isEmpty()) {
                tran.begin();
                entityManager.persist(new Category("Calendars and Planners"));
                entityManager.persist(new Category("Calculators"));
                entityManager.persist(new Category("Scissors and Paper Trimmers"));
                tran.commit();
            }
        } catch (Throwable thr) {
            logger.error("Error", thr);
        }
    }

    @Override
    public Collection<Category> getAll() {
        return entityManager.createQuery("select c from Category c").getResultList();
    }

    @Override
    protected void beginTran() throws SystemException, NotSupportedException {
        tran.begin();
    }

    @Override
    protected void commitTran() throws HeuristicRollbackException, RollbackException, HeuristicMixedException, SystemException {
        tran.commit();
    }

    @Override
    public Category getById(long id) {
        return entityManager.find(Category.class, id);
    }
}
