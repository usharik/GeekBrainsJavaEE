package ru.geekbrains.ejbs;

import javax.ejb.Remote;

@Remote
public interface RemoteStateless {
    String getInfo();
}
