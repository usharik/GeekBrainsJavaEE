package ru.geekbrains;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface SampleService {

    @WebMethod
    void addString(String str);

    @WebMethod
    List<String> getStrings();

    @WebMethod
    int getCount();
}
