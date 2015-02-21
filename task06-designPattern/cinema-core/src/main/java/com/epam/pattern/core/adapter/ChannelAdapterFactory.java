package com.epam.pattern.core.adapter;

/**
 * task06-designPattern class
 * Date: Sep 02, 2015
 *
 * @author Aliaksandr_Shynkevich
 */
public class ChannelAdapterFactory {
    private ChannelAdapterFactory() {
    }

    private static ChannelAdapter channelAdapter = new ChannelAdapterImpl();

    public synchronized static ChannelAdapter getAdapter() {
        return channelAdapter;
    }
}
