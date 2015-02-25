package com.epam.pattern.dealer;

import com.epam.pattern.core.util.SocketUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.log4j.Logger;

/**
 * Created by Aliaksandr_Shynkevich on 2/21/15
 */
public class DealerReceiver extends Thread {
    private static final Logger LOGGER = Logger.getLogger(DealerReceiver.class);
    public static final String END_CMD = "end";

    private Socket socket = new Socket();
    private ExecutorService executor = Executors.newSingleThreadExecutor();
//    private Thread thread;

    private String host;
    private int port;

    public DealerReceiver(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            socket.connect(new InetSocketAddress(host, port));
            LOGGER.info(String.format("Dealer sender has started and connected to %s ...",
                    socket.getRemoteSocketAddress().toString()));
            executor.execute(new DealerCatcher(socket));
            OutputStream outputStream = socket.getOutputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String cmd = bufferedReader.readLine();
                if (END_CMD.equals(cmd)) {
                    outputStream.write(1);
                    outputStream.flush();
                    outputStream.close();
                    break;
                }
            }
            executor.shutdown();
            LOGGER.info("Broker has closed connection...");
        } catch (IOException e) {
            LOGGER.info("Dealer receiver has finished...");
        } finally {
            SocketUtil.close(socket);
        }
    }
}
