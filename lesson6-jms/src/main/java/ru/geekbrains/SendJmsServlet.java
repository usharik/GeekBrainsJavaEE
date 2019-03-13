package ru.geekbrains;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.jms.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet("/send_jms")
public class SendJmsServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(JmsReceiver.class);

    @Resource(lookup = "java:/ConnectionFactory")
    private ConnectionFactory cf;

    @Resource(lookup = "java:jboss/exported/jms/queue/TestQueue")
    private Queue queue;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String message = request.getParameter("message");
        try {
            try (Connection connection = cf.createConnection()) {
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                MessageProducer producer = session.createProducer(queue);

                connection.start();

                TextMessage textMessage = session.createTextMessage(message);
                producer.send(textMessage);
            }
            logger.info("Message sent: {}", message);

            response.sendRedirect("index.jsp");
        } catch (Exception ex) {
            logger.error("Error", ex);
        }
    }
}