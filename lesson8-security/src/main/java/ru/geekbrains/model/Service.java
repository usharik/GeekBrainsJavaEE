package ru.geekbrains.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;

@Stateless
public class Service {

    private static final Logger logger = LoggerFactory.getLogger(Service.class);

    @RolesAllowed("admin")
    public void action() {
        logger.info("Service action call");
    }
}
