package ru.geekbrains.persistance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        categoryRepository.init();
        productRepository.init();
    }
}
