package ru.geekbrains;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.*;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.jms.*;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jboss/exported/jms/queue/TestQueue"),
        @ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "source = 'client'")})
public class JmsReceiver implements MessageListener {

    private static Logger logger = LoggerFactory.getLogger(JmsReceiver.class);

    @Inject
    private BeanManager beanManager;

    @Override
    public void onMessage(javax.jms.Message message) {
        try {
            if (message instanceof TextMessage) {
                String text = ((TextMessage) message).getText();
                logger.info("Got message {}", text);
                beanManager.fireEvent(text);
            }
        } catch (JMSException ex) {
            logger.error("JMS Exception", ex);
        }
    }
}