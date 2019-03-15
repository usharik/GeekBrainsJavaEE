package ru.geekbrains;

import java.util.Scanner;

import javax.jms.*;
import javax.naming.Context;

public class JMSClient {

    private static String CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
    private static String DESTINATION = "jms/queue/TestQueue";

    public static void main(String[] args) throws Exception {
        Context namingContext = Utils.createInitialContext();

        ConnectionFactory connectionFactory = (ConnectionFactory) namingContext
                .lookup(CONNECTION_FACTORY);
        System.out.println("Got ConnectionFactory " + CONNECTION_FACTORY);

        Destination destination = (Destination) namingContext
                .lookup(DESTINATION);
        System.out.println("Got JMS Endpoint " + DESTINATION);

        JMSContext context = connectionFactory.createContext("testUser", "password");

        JMSProducer producer = context.createProducer();

        JMSConsumer consumer = context
                .createConsumer(destination, "source = 'server'");

        consumer.setMessageListener(message -> {
            if (message instanceof TextMessage) {
                try {
                    System.out.print(String.format("\nNew message from server: %s\nEnter message: ", ((TextMessage) message).getText()));
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.print("Enter message: ");
        try (Scanner sc = new Scanner(System.in)) {
            while (sc.hasNextLine()) {
                String msg = sc.nextLine();
                producer.setProperty("source", "client").send(destination, msg);
                System.out.print("Enter message: ");
            }
        }
    }
}
