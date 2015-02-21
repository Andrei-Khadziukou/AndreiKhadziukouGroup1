package com.epam.pattern.broker;

import java.io.IOException;
import org.apache.log4j.Logger;

/**
 * Created by Aliaksandr_Shynkevich on 2/20/15
 */
public class BrokerReceiver extends AbstractSocketBroker {
    private static final Logger LOGGER = Logger.getLogger(BrokerReceiver.class);

    public BrokerReceiver(int port) {
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
            LOGGER.info("Broker sender finished...");
        }
    }
}
