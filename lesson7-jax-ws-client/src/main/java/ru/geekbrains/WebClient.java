package ru.geekbrains;

import java.net.MalformedURLException;
import java.net.URL;

public class WebClient {

    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8080/lesson7jaxws/SampleServiceImpl?WSDL");
        SampleServiceImplService sampleServiceImplService = new SampleServiceImplService(url);
        SampleService sampleService = sampleServiceImplService.getSampleServiceImplPort();

        sampleService.addString("111");
        sampleService.addString("222");
        sampleService.addString("333");
        System.out.println("String count " + sampleService.getCount());
        System.out.println("Strings " + sampleService.getStrings());
    }
}
