package com.epam.pattern.core.adapter;

import com.epam.pattern.core.domain.Messageable;

/**
 * task06-designPattern class
 * Date: Sep 02, 2015
 *
 * @author Aliaksandr_Shynkevich
 */
public interface ChannelAdapter {

    void sendMessage(Messageable message);
}
