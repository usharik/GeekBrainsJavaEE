package ru.geekbrains.persistance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persistance.entity.EntityId;
import ru.geekbrains.persistance.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.*;
import java.util.Collection;
import java.util.List;


public abstract class AbstractRepository<T extends EntityId> {

    private static Logger logger = LoggerFactory.getLogger(AbstractRepository.class);

    @PersistenceContext(unitName = "ds")
    protected EntityManager entityManager;

    protected T getEntity(TypedQuery<T> query) {
        List<T> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null;
        }
        return resultList.get(0);
    }

    public abstract T getById(long id);

    public abstract Collection<T> getAll();

    protected abstract void beginTran() throws SystemException, NotSupportedException;

    protected abstract void commitTran() throws HeuristicRollbackException, RollbackException, HeuristicMixedException, SystemException;

    public void merge(T entity) {
        if (entity == null) {
            return;
        }
        try {
            beginTran();
            entityManager.merge(entity);
            commitTran();
        } catch (Exception ex) {
            logger.error("Error with entity class {}" , entity.getClass().getSimpleName(), ex);
            throw new IllegalStateException(ex);
        }
    }

    public void remove(T entity) {
        if (entity == null) {
            return;
        }
        try {
            beginTran();
            Product attached = entityManager.find(Product.class, entity.getId());
            if (attached != null) {
                entityManager.remove(attached);
            }
            commitTran();
        } catch (Exception ex) {
            logger.error("Error with entity class {}" , entity.getClass().getSimpleName(), ex);
            throw new IllegalStateException(ex);
        }
    }
}
