package com.epam.pattern.core.configuration;

/**
 * Created by Aliaksandr_Shynkevich on 2/21/15
 */
public class ChannelConfiguration {
    private String brokerReceiverHost;
    private int brokerReceiverPort;
    private String brokerSenderHost;
    private int brokerSenderPort;

    ChannelConfiguration() {
    }

    public String getBrokerReceiverHost() {
        return brokerReceiverHost;
    }

    void setBrokerReceiverHost(String brokerReceiverHost) {
        this.brokerReceiverHost = brokerReceiverHost;
    }

    public int getBrokerReceiverPort() {
        return brokerReceiverPort;
    }

    void setBrokerReceiverPort(int brokerReceiverPort) {
        this.brokerReceiverPort = brokerReceiverPort;
    }

    public String getBrokerSenderHost() {
        return brokerSenderHost;
    }

    void setBrokerSenderHost(String brokerSenderHost) {
        this.brokerSenderHost = brokerSenderHost;
    }

    public int getBrokerSenderPort() {
        return brokerSenderPort;
    }

    void setBrokerSenderPort(int brokerSenderPort) {
        this.brokerSenderPort = brokerSenderPort;
    }
}
