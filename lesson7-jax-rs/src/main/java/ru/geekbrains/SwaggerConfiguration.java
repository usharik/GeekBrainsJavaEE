package ru.geekbrains;

import io.swagger.jaxrs.config.BeanConfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class SwaggerConfiguration extends Application {

    public SwaggerConfiguration() {
        init();
    }

    private void init() {

        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/lesson7jaxrs/api");
        beanConfig.setResourcePackage(CrudResource.class.getPackage().getName());
        beanConfig.setTitle("Example of JAX-RS application");
        beanConfig.setDescription("Sample RESTful API built using RAX-RS, Swagger and Swagger UI");
        beanConfig.setScan(true);
    }
}
