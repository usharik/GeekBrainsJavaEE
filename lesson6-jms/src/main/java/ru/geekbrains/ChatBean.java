package ru.geekbrains;

import org.omnifaces.cdi.Push;
import org.omnifaces.cdi.PushContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class ChatBean implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(ChatBean.class);

    private List<Message> messages;

    private String textMsg = "";

    @Inject
    @Push
    private PushContext viewPush;

    @Resource(name = "DefaultJMSConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(name = "java:jboss/exported/jms/queue/TestQueue")
    private Destination queue;

    @PostConstruct
    public void load() {
        messages = new ArrayList<>();
    }

    public void onNewMessage(@Observes Message msg) {
        logger.info("Sending push notification");
        messages.add(msg);
        viewPush.send("newMsg");
    }

    public List<Message> getMessages() {
        return messages;
    }

    public String getTextMsg() {
        return textMsg;
    }

    public void setTextMsg(String textMsg) {
        this.textMsg = textMsg;
    }

    public void sendPush() {
        logger.info("Sending message {}", textMsg);

        messages.add(new Message("Server", textMsg));

        try (JMSContext context = connectionFactory.createContext()) {
            context.createProducer().setProperty("source", "server")
                    .send(queue, textMsg);
        }

        textMsg = "";
        logger.info("Done");
    }
}