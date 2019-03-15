package ru.geekbrains.ejbs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import java.util.UUID;

@Stateless
public class RemoteStatelessBean implements RemoteStateless {

    private static Logger logger = LoggerFactory.getLogger(RemoteStatelessBean.class);

    private final UUID id = UUID.randomUUID();

    public RemoteStatelessBean() {
        logger.info("New stateless bean with id " + id);
    }

    @Override
    public String getInfo() {
        return "From stateless class %d" + id;
    }
}
