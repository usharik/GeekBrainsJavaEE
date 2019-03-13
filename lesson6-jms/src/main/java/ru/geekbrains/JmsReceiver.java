package ru.geekbrains;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.*;
import javax.jms.*;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jboss/exported/jms/queue/TestQueue") })
public class JmsReceiver implements MessageListener {

    private static Logger logger = LoggerFactory.getLogger(JmsReceiver.class);

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                logger.info("Got Message {}", ((TextMessage) message).getText());
            }
        } catch (JMSException ex) {
            logger.error("JMS Exception", ex);
        }
    }
}