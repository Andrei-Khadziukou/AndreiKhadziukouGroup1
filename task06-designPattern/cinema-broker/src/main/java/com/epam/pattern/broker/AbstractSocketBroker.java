package com.epam.pattern.broker;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;
import org.apache.log4j.Logger;

/**
 * Created by Aliaksandr_Shynkevich on 2/21/15
 */
public abstract class AbstractSocketBroker extends Thread {
    private static final Logger LOGGER = Logger.getLogger(BrokerStarter.class);
    private ServerSocket socket;

    public AbstractSocketBroker(int port) {
        try {
            socket = new ServerSocket(port);
        } catch (IOException e) {
            LOGGER.error("Broker start error: ", e);
            closeSocket();
            System.exit(1);
        }
    }

    public void finish() {
        try {
            socket.setSoTimeout(2000);
        } catch (SocketException e) {
            LOGGER.error("Finish broker error: ", e);
        }
    }

    protected ServerSocket getSocket() {
        return socket;
    }

    protected void closeSocket() {
       closeSocket();
    }
}
