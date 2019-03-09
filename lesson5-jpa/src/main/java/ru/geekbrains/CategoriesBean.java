package ru.geekbrains;

import org.primefaces.event.MenuActionEvent;
import org.primefaces.model.menu.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persistance.entity.Category;
import ru.geekbrains.persistance.CategoryRepository;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import java.util.Collection;

@ManagedBean(name = "categories")
@SessionScoped
public class CategoriesBean {

    private static Logger logger = LoggerFactory.getLogger(CategoriesBean.class);

    private static String CATEGORY_ID ="categoryId";

    private DynamicMenuModel model;

    @Inject
    private CategoryRepository categoryRepository;

    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Collection<Category> getAll() {
        return categoryRepository.getAll();
    }

    @PostConstruct
    private void init() {
        model = new DynamicMenuModel();
    }

    public MenuModel getModel() {

        model.getElements().clear();

        model.getElements().clear();

        DefaultSubMenu categoriesSubmenu = new DefaultSubMenu("Categories");
        categoriesSubmenu.setExpanded(true);

        for (Category category : categoryRepository.getAll()) {
            categoriesSubmenu.addElement(createMenuItem(String.valueOf(category.getId()), category.getName()));
        }
        model.addElement(categoriesSubmenu);

        model.generateUniqueIds();

        return model;
    }

    public String actionSelectCategory(ActionEvent event) {
        logger.info("Selecting category with");

        MenuItem menuItem = ((MenuActionEvent) event).getMenuItem();
        String id = menuItem.getParams().get(CATEGORY_ID).get(0);

        this.category = categoryRepository.getById(Long.valueOf(id));
        return "/index.xhtml?faces-redirect=true";
    }

    private String getItemSelectedStyle(String id) {
        return category != null && category.getId() == Long.valueOf(id) ? "ui-state-active" : "";
    }

    public void actionEmpty() {

    }

    private MenuItem createMenuItem(String id, String name) {
        DefaultMenuItem item = new DefaultMenuItem(name);
        item.setId(id);
        item.setCommand("#{categories.actionSelectCategory}");
        item.setParam(CATEGORY_ID, id);
        item.setStyleClass(getItemSelectedStyle(id));
        return item;
    }
}
