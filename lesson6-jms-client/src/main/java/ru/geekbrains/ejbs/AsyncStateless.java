package ru.geekbrains.ejbs;

import javax.ejb.Remote;
import java.util.concurrent.Future;

@Remote
public interface AsyncStateless {

    void doSomeWork();

    Future<String> getFutureResult();

}
