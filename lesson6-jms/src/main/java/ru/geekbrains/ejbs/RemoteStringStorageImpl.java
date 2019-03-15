package ru.geekbrains.ejbs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PreDestroy;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Stateful
@StatefulTimeout(value = 1, unit = TimeUnit.MINUTES)
public class RemoteStringStorageImpl implements RemoteStringStorage, Serializable {

    private static Logger logger = LoggerFactory.getLogger(RemoteStringStorageImpl.class);

    private List<String> strings = new ArrayList<>();

    private final UUID id = UUID.randomUUID();

    public RemoteStringStorageImpl() {
        logger.info("New Stateful bean with id {}", id);
    }

    @Override
    public void putString(String str) {
        logger.info("New string {} into bean id {}", str, id);
        strings.add(str);
    }

    @Override
    public List<String> getAllStrings() {
        return strings;
    }

    @PreDestroy
    public void preDestroy() {
        logger.info("Stateful bean {} is destroyed.", id);
    }
}
