package com.epam.pattern.broker;

import com.epam.pattern.broker.storage.MessagesStorage;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

/**
 * Created by Aliaksandr_Shynkevich on 2/20/15
 */
public class BrokerReceiver extends AbstractSocketBroker {
    private static final Logger LOGGER = Logger.getLogger(BrokerReceiver.class);

    private MessagesStorage storage = MessagesStorage.getInstance();

    public BrokerReceiver(int port) {
        super(port);
    }

    @Override
    public void run() {
        LOGGER.info(String.format("Broker sender has started on %d port...",
                getSocket().getLocalPort()));
        try {
            while (true) {
                Socket socket = getSocket().accept();
                LOGGER.info(String.format("Broker accepted application from %s",
                        socket.getRemoteSocketAddress().toString()));
                InputStream inputStream = socket.getInputStream();
                String message = IOUtils.toString(inputStream);
                storage.putMessage(message);
                LOGGER.info(String.format("Broker has got message from %s",
                        socket.getRemoteSocketAddress().toString()));
            }
        } catch (IOException e) {
            LOGGER.info("Broker sender finished...");
        }
    }
}
