package ru.geekbrains;

import java.util.Scanner;

import javax.jms.*;
import javax.naming.Context;

/**
 * Пример клиентского приложения, которое отправляет JMS сообщения
 * через встроенный в Wildfly JMS брокер
 */
public class JMSClient {

    private static String CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
    private static String DESTINATION = "jms/queue/TestQueue";

    public static void main(String[] args) throws Exception {
        // Получаем JNDI-контекст для поиска бинов на сервере приложений
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

        // Обратите внимание, что этот тот же самый интерфейс-listner, который используется в MDB
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
                // параметр, который используется для фильтрации сообщений получателем
                producer.setProperty("source", "client").send(destination, msg);
                System.out.print("Enter message: ");
            }
        }
    }
}
