package ru.geekbrains;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface = "ru.geekbrains.SampleService")
public class SampleServiceImpl implements SampleService {

    private static Logger logger = LoggerFactory.getLogger(SampleServiceImpl.class);

    private List<String> strings = new ArrayList<>();

    @Override
    public void addString(String str) {
        logger.info("Adding string {}", str);
        strings.add(str);
    }

    @Override
    public List<String> getStrings() {
        return strings;
    }

    @Override
    public int getCount() {
        return strings.size();
    }
}
