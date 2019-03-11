package ru.geekbrains.persistance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persistance.entity.Category;
import ru.geekbrains.persistance.entity.Product;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class StartupInitializer {

    private static Logger logger = LoggerFactory.getLogger(StartupInitializer.class);

    @Inject
    private ProductRepository productRepository;

    @Inject
    private CategoryRepository categoryRepository;

    @PostConstruct
    public void init() {
        logger.info("Initializing category data");
        if (categoryRepository.getAll().isEmpty()) {
            categoryRepository.merge(new Category("Calendars and Planners"));
            categoryRepository.merge(new Category("Calculators"));
            categoryRepository.merge(new Category("Scissors and Paper Trimmers"));
        }

        logger.info("Initializing product data");
        if (productRepository.getAll().isEmpty()) {
            productRepository.merge(new Product("Pen", 50, categoryRepository.getById(1)));
            productRepository.merge(new Product("Pencil", 150, categoryRepository.getById(1)));
            productRepository.merge(new Product("Textbook", 201, categoryRepository.getById(1)));
            productRepository.merge(new Product("Paper", 500, categoryRepository.getById(1)));
            productRepository.merge(new Product("Pen", 50, categoryRepository.getById(1)));
            productRepository.merge(new Product("Eraser", 150, categoryRepository.getById(1)));
            productRepository.merge(new Product("Marker", 200, categoryRepository.getById(2)));
            productRepository.merge(new Product("Sticks", 500, categoryRepository.getById(2)));
            productRepository.merge(new Product("Brash", 50, categoryRepository.getById(2)));
            productRepository.merge(new Product("Pencil", 150, categoryRepository.getById(2)));
            productRepository.merge(new Product("Textbook", 200, categoryRepository.getById(3)));
            productRepository.merge(new Product("Paper", 500, categoryRepository.getById(3)));
            productRepository.merge(new Product("Pen", 50, categoryRepository.getById(3)));
            productRepository.merge(new Product("Pencil", 150, categoryRepository.getById(3)));
            productRepository.merge(new Product("Textbook", 200, categoryRepository.getById(3)));
            productRepository.merge(new Product("Paper", 500, categoryRepository.getById(3)));
        }
    }
}
