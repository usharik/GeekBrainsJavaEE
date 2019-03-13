package ru.geekbrains;

import java.util.Properties;
import java.util.Scanner;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;

public class JMSClient {

    private static String CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
    private static String DESTINATION = "jms/queue/TestQueue";

    public static void main(String[] args) throws Exception {
        final Properties env = new Properties();
        env.load(JMSClient.class.getClassLoader().getResourceAsStream("wildfly-jndi.properties"));
        Context namingContext = new InitialContext(env);

        ConnectionFactory connectionFactory = (ConnectionFactory) namingContext
                .lookup(CONNECTION_FACTORY);
        System.out.println("Got ConnectionFactory " + CONNECTION_FACTORY);

        Destination destination = (Destination) namingContext
                .lookup(DESTINATION);
        System.out.println("Got JMS Endpoint " + DESTINATION);

        JMSContext context = connectionFactory.createContext("testUser", "password");

        JMSProducer producer = context.createProducer();

        System.out.print("Enter message: ");
        try (Scanner sc = new Scanner(System.in)) {
            while (sc.hasNextLine()) {
                String msg = sc.nextLine();
                producer.send(destination, msg);
                System.out.println("Sent message: " + msg);
                System.out.print("Enter message: ");
            }
        }

//
//        // Create the JMS consumer
//        JMSConsumer consumer = context.createConsumer(destination);
//        // Then receive the same number of messages that were sent
//
//        String text = consumer.receiveBody(String.class, 5000);
//        if (text == null)
//            System.out.println("No message Received! Maybe another Consumer listening on the Queue ??");
//        System.out.println("Received message with content " + text);

    }
}
