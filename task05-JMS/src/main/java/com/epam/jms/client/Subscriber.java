package com.epam.jms.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * Created by Aliaksandr_Shynkevich on 1/17/15
 */
public class Subscriber implements ExceptionListener {

    private static Logger LOGGER = Logger.getLogger(Subscriber.class);

    private Properties properties = new Properties();

    private String userName;
    private String password;
    private String brokerURL;

    {
        try {
            File file = new File("configuration.properties");
            InputStream inputStream = new FileInputStream(file);
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

    private void consume(String topicName) {
        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(userName, password, brokerURL);

            Connection connection = connectionFactory.createConnection();
            connection.start();
            connection.setExceptionListener(this);

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createTopic(topicName);

            MessageConsumer consumer = session.createConsumer(destination);
            Message message = consumer.receive();

            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                LOGGER.info("Received: " + text);
            } else {
                LOGGER.info("Received: " + message);
            }

            consumer.close();
            session.close();
            connection.close();
        } catch (Exception e) {
            LOGGER.error("Caught: ", e);
        }
    }

    @Override
    public void onException(JMSException e) {
        LOGGER.error("JMS error: ", e);
    }

    public static void main(String[] args) {
        String topicName = System.getProperty("topicName", args[0]);
        if (StringUtils.isNotEmpty(topicName)) {
            new Subscriber().consume(topicName);
        } else {
            LOGGER.info("You should set topic name");
        }
    }
}
