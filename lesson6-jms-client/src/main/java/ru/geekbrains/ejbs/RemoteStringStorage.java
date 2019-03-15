package ru.geekbrains.ejbs;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface RemoteStringStorage {

    void putString(String str);

    List<String> getAllStrings();
}
