package ru.geekbrains.persistance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persistance.entity.Category;
import ru.geekbrains.persistance.entity.Product;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
        List<Category> categories = new ArrayList<>();
        if (categoryRepository.getAll().isEmpty()) {
            categories.add(categoryRepository.merge(new Category("Calendars and Planners")));
            categories.add(categoryRepository.merge(new Category("Calculators")));
            categories.add(categoryRepository.merge(new Category("Scissors and Paper Trimmers")));
        }

        logger.info("Initializing product data");
        if (productRepository.getAll().isEmpty()) {
            productRepository.merge(new Product("Pen", new BigDecimal(50), categories.get(0)));
            productRepository.merge(new Product("Pencil", new BigDecimal(150), categories.get(0)));
            productRepository.merge(new Product("Textbook", new BigDecimal(201), categories.get(0)));
            productRepository.merge(new Product("Paper", new BigDecimal(500), categories.get(0)));
            productRepository.merge(new Product("Pen", new BigDecimal(50), categories.get(0)));
            productRepository.merge(new Product("Eraser", new BigDecimal(150), categories.get(0)));
            productRepository.merge(new Product("Marker", new BigDecimal(200), categories.get(1)));
            productRepository.merge(new Product("Sticks", new BigDecimal(500), categories.get(1)));
            productRepository.merge(new Product("Brash", new BigDecimal(50), categories.get(1)));
            productRepository.merge(new Product("Pencil", new BigDecimal(150), categories.get(1)));
            productRepository.merge(new Product("Textbook", new BigDecimal(200), categories.get(2)));
            productRepository.merge(new Product("Paper", new BigDecimal(500), categories.get(2)));
            productRepository.merge(new Product("Pen", new BigDecimal(50), categories.get(2)));
            productRepository.merge(new Product("Pencil", new BigDecimal(150), categories.get(2)));
            productRepository.merge(new Product("Textbook", new BigDecimal(200), categories.get(2)));
            productRepository.merge(new Product("Paper", new BigDecimal(500), categories.get(2)));
        }
    }
}
