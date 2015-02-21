package com.epam.pattern.broker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.log4j.Logger;

/**
 * Created by Aliaksandr_Shynkevich on 2/20/15
 */
public class BrokerStarter {
    private static final Logger LOGGER = Logger.getLogger(BrokerStarter.class);
    private static final String END_CMD = "end";

    private BrokerReceiver receiver;
    private BrokerSender sender;

    private BrokerStarter() {
        receiver = new BrokerReceiver(9898);
        receiver.start();
        sender = new BrokerSender(8989);
        sender.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            LOGGER.info("Sleep thread error: ", e);
        }
    }

    private void waitFinish() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                LOGGER.info("Wait 'end' command...");
                String cmd = bufferedReader.readLine();
                if (END_CMD.equals(cmd)) {
                    receiver.finish();
                    sender.finish();
                    return;
                }
            } catch (IOException e) {
                LOGGER.error("Keyboard reading error: ", e);
            }
        }

    }

    public static void main(String[] args) {
        new BrokerStarter().waitFinish();
        System.exit(0);
    }
}
