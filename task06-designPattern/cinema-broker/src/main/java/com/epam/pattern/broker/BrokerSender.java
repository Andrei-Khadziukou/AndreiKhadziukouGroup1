package com.epam.pattern.broker;

import java.io.IOException;
import org.apache.log4j.Logger;

/**
 * Created by Aliaksandr_Shynkevich on 2/20/15
 */
public class BrokerSender extends AbstractSocketBroker {
    private static final Logger LOGGER = Logger.getLogger(BrokerStarter.class);

    public BrokerSender(int port) {
        super(port);
    }

    @Override
    public void run() {
        LOGGER.info(String.format("Broker sender has started on %d port...",
                getSocket().getLocalPort()));
        try {
            while (true) {
                getSocket().accept();
            }
        } catch (IOException e) {
            closeSocket();
            LOGGER.info("Broker sender has finished...");
        }
    }
}
