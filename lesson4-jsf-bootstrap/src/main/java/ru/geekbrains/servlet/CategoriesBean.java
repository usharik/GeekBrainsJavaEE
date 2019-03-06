package ru.geekbrains.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.servlet.entity.Category;
import ru.geekbrains.servlet.entity.Category;
import ru.geekbrains.servlet.repository.CategoryRepository;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;

@Named("categories")
@SessionScoped
public class CategoriesBean {

    private static final Logger logger = LoggerFactory.getLogger(CategoriesBean.class);

    private Category category;

    @Inject
    private CategoryRepository categoryRepository;

    public String getId() {
        return category.getId();
    }

    public void setId(String id) {
        category.setId(id);
    }

    public String getName() {
        return category.getName();
    }

    public void setName(String name) {
        category.setName(name);
    }

    public Collection<Category> getCategoryList() {
        return categoryRepository.getAll();
    }

    public void deleteAction(Category category) {
        logger.info("Delete category with id {} and name {}", category.getId(), category.getName());
        categoryRepository.delete(category);
    }

    public String addAction() {
        logger.info("Add category action");

        category = new Category();
        return "/category.xhtml?faces-redirect=true";
    }

    public String editAction(Category category) {
        logger.info("Edit category with id {} and name {}", category.getId(), category.getName());

        this.category = category;
        return "/category.xhtml?faces-redirect=true";
    }

    public String saveCategory() {
        categoryRepository.merge(category);
        return "/categories.xhtml?faces-redirect=true";
    }
}
