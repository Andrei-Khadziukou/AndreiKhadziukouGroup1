package com.epam.pattern.broker;

import com.epam.pattern.broker.storage.MessagesStorage;
import com.epam.pattern.core.util.SocketUtil;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * Created by Aliaksandr_Shynkevich on 2/20/15
 */
public class BrokerSender extends AbstractSocketBroker {
    private static final Logger LOGGER = Logger.getLogger(BrokerStarter.class);

    private MessagesStorage storage = MessagesStorage.getInstance();
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private Socket acceptedSocket = null;

    public BrokerSender(int port) {
        super(port);
    }

    @Override
    public void run() {
        LOGGER.info(String.format("Broker sender has started on %d port...",
                getSocket().getLocalPort()));
        try {
            while (true) {
                acceptedSocket = getSocket().accept();
                LOGGER.info(String.format("Broker accepted a dealer from %s",
                        acceptedSocket.getRemoteSocketAddress().toString()));
                executor.execute(new BrokerSenderShutdown(acceptedSocket));
                OutputStream outputStream = acceptedSocket.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                while (!acceptedSocket.isClosed()) {
                    String message = storage.getMessage();
                    if (StringUtils.isNotBlank(message)) {
                        dataOutputStream.writeUTF(message);
                        dataOutputStream.flush();
                    }
                    Thread.sleep(2000);
                }
                LOGGER.info(String.format("Broker has disconnected from %s",
                        acceptedSocket.getRemoteSocketAddress().toString()));
            }
        } catch (IOException | InterruptedException e) {
            closeSocket();
            SocketUtil.close(acceptedSocket);
            LOGGER.info("Broker sender has finished...");
        }
        executor.shutdown();
    }

    @Override
    public void finish() {
        SocketUtil.close(acceptedSocket);
        super.finish();
    }
}
