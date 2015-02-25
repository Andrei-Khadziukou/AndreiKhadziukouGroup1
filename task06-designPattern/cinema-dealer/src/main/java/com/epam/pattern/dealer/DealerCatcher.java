package com.epam.pattern.dealer;

import com.epam.pattern.core.util.SocketUtil;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import org.apache.log4j.Logger;

/**
 * Created by Aliaksandr_Shynkevich on 2/21/15
 */
public class DealerCatcher implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(DealerCatcher.class);

    private Socket socket;

    public DealerCatcher(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            while (true) {
                String message = dataInputStream.readUTF();
                LOGGER.info(String.format("Dealer's received from broker: [%s]", message));
                Thread.sleep(2000);
            }
        } catch (EOFException e) {
            LOGGER.info("Broker has closed connection");
            SocketUtil.close(socket);
            System.exit(0);
        } catch (IOException | InterruptedException e) {
            LOGGER.info("Dealer catcher has finished");
        }
    }
}
