package com.epam.pattern.dealer;

import com.epam.pattern.core.configuration.ChannelConfiguration;
import com.epam.pattern.core.configuration.CinemaConfigurator;
import org.apache.log4j.Logger;

/**
 * Created by Aliaksandr_Shynkevich on 2/21/15
 */
public class DealerStarter {
    private static final Logger LOGGER = Logger.getLogger(DealerStarter.class);

    private ChannelConfiguration configuration = new CinemaConfigurator().getConfiguration();

    public DealerStarter() {
        DealerReceiver receiver = new DealerReceiver(configuration.getBrokerSenderHost(),
                configuration.getBrokerSenderPort());
        receiver.start();
    }

    public static void main(String[] args) {
        new DealerStarter();
    }
}
