package ru.geekbrains.model;

import javax.annotation.security.DenyAll;
import javax.ejb.Stateless;

@Stateless
public class Service {

    @DenyAll
    public void action() {

    }
}
