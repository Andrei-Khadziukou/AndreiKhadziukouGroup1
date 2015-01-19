package com.epam.jms.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;
import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

/**
 * Created by Aliaksandr_Shynkevich on 1/17/15
 */
public class PublisherServlet extends HttpServlet {

    private static Logger LOGGER = Logger.getLogger(PublisherServlet.class);
    private Properties properties = new Properties();

    private String userName;
    private String password;
    private String brokerURL;

    {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/configuration.properties");
            properties.load(inputStream);
            userName = properties.getProperty("jms.broker.user");
            password = properties.getProperty("jms.broker.password");
            brokerURL = properties.getProperty("jms.broker.url");
        } catch (Exception e) {
            LOGGER.error("Loading properties error: ", e);
            userName = "admin";
            password = "admin";
            brokerURL = "tcp://localhost:61616";
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String topic = req.getParameter("topic");
        String message = req.getParameter("message");
        try {
            publish(topic, message);
            req.setAttribute("success", String.format("Topic [%s] has been published successful", topic));
        } catch (Exception e) {
            req.setAttribute("errors", Arrays.asList(e.getMessage()));
            LOGGER.error("Publish error: ", e);
        }
        req.getRequestDispatcher("publish.jsp").forward(req, resp);
    }

    private void publish(String topic, String text) throws Exception {
        Connection connection = null;
        Session session = null;
        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(userName, password, brokerURL);

            connection = connectionFactory.createConnection();
            connection.start();

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createTopic(topic);
            MessageProducer producer = session.createProducer(destination);

            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            TextMessage message = session.createTextMessage(text);

            producer.send(message);
            LOGGER.info(String.format("Message sent [hashCode = %d]", message.hashCode()));
        } finally {
            try {
                session.close();
                connection.close();
            } catch (Exception e) {
                LOGGER.warn("Connections was closed");
            }
        }
    }
}
