package ru.geekbrains.persistance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persistance.entity.Category;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.io.Serializable;
import java.util.Collection;


@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class CategoryRepository extends AbstractRepository<Category> implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(CategoryRepository.class);

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Category> getAll() {
        logger.info("Get all categories");
        return entityManager.createQuery("select c from Category c").getResultList();
    }

    @Override
    public Category getById(long id) {
        logger.info("Get category with id {}", id);
        return entityManager.find(Category.class, id);
    }
}
