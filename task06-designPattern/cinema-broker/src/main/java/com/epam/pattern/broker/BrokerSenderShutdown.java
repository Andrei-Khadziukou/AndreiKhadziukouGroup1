package com.epam.pattern.broker;

import com.epam.pattern.core.util.SocketUtil;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import org.apache.log4j.Logger;

/**
 * Created by Aliaksandr_Shynkevich on 2/21/15
 */
public class BrokerSenderShutdown implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(BrokerSenderShutdown.class);

    private Socket socket;

    public BrokerSenderShutdown(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            inputStream.read();
            inputStream.close();
            LOGGER.info("Broker has got shutdown impulse...");
        } catch (IOException e) {
            LOGGER.info("Shutdown stream...");
        }
        SocketUtil.close(socket);
    }
}
