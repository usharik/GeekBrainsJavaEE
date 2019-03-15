package ru.geekbrains.ejbs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import java.util.concurrent.Future;

@Stateless
@Asynchronous
public class AsyncStatelessImpl implements AsyncStateless {

    private static Logger logger = LoggerFactory.getLogger(AsyncStatelessImpl.class);

    @Override
    public void doSomeWork() {
        logger.info("Do work begin");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("Do work completed");
    }

    @Override
    public Future<String> getFutureResult() {
        logger.info("Get future result begin");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("Get future result completed");
        return new AsyncResult<>("Result");
    }
}
