package ru.geekbrains;

import ru.geekbrains.ejbs.AsyncStateless;
import ru.geekbrains.ejbs.RemoteStateless;
import ru.geekbrains.ejbs.RemoteStringStorage;

import javax.naming.Context;

public class EJBClient {

    public static void main(String[] args) throws Exception{
        Context context = Utils.createInitialContext();

        System.out.println("\nGetting first instance of Stateful bean");
        RemoteStringStorage storage1 = (RemoteStringStorage)
                context.lookup("java:/lesson6jms//RemoteStringStorageImpl!ru.geekbrains.ejbs.RemoteStringStorage");

        System.out.println("Adding data and looking for result");
        storage1.putString("First");
        storage1.putString("Second");
        storage1.putString("Third");

        System.out.println(storage1.getAllStrings());

        System.out.println("\nGetting several new instances of stateful bean");
        RemoteStringStorage storage2 = (RemoteStringStorage)
                context.lookup("java:/lesson6jms//RemoteStringStorageImpl!" + RemoteStringStorage.class.getName());

        System.out.println(storage2.getAllStrings());

        RemoteStringStorage storage3 = (RemoteStringStorage)
                context.lookup("java:/lesson6jms//RemoteStringStorageImpl!" + RemoteStringStorage.class.getName());

        System.out.println(storage3.getAllStrings());

        System.out.println("\nGetting first instance of stateless bean and calling methods of it");
        RemoteStateless stateless1 = (RemoteStateless)
                context.lookup("java:/lesson6jms//RemoteStatelessBean!" + RemoteStateless.class.getName());

        System.out.println(stateless1.getInfo());
        System.out.println(stateless1.getInfo());
        System.out.println(stateless1.getInfo());
        System.out.println(stateless1.getInfo());

        System.out.println("\nTrying to get instance of the same stateless bean several times");

        RemoteStateless stateless2 = (RemoteStateless)
                context.lookup("java:/lesson6jms//RemoteStatelessBean!" + RemoteStateless.class.getName());

        System.out.println(stateless2.getInfo());

        RemoteStateless stateless3 = (RemoteStateless)
                context.lookup("java:/lesson6jms//RemoteStatelessBean!" + RemoteStateless.class.getName());

        System.out.println(stateless3.getInfo());

        System.out.println("\nEJB async example");

        AsyncStateless bean = (AsyncStateless)
                context.lookup("java:/lesson6jms//AsyncStatelessImpl!" + AsyncStateless.class.getName());

        bean.doSomeWork();
        System.out.println("Making call to future");
        System.out.println("Result from future " + bean.getFutureResult().get());
    }
}
