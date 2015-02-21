package com.epam.pattern.core.adapter;

import com.epam.pattern.core.domain.Messageable;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import org.apache.log4j.Logger;
import org.boon.json.JsonFactory;
import org.boon.json.ObjectMapper;

/**
 * task06-designPattern class
 * Date: Sep 02, 2015
 *
 * @author Aliaksandr_Shynkevich
 */
public class ChannelAdapterImpl implements ChannelAdapter {
    private static final Logger LOGGER = Logger.getLogger(ChannelAdapterImpl.class);

    private ObjectMapper mapper = JsonFactory.create();

    @Override
    public void sendMessage(Messageable message) {
        OutputStream stream = null;
        try (Socket socket = new Socket("localhost", 9898)) {
            String jsonMessage = mapper.toJson(message);
            stream = socket.getOutputStream();
            stream.write(jsonMessage.getBytes());
            stream.flush();
        } catch (IOException e) {
            LOGGER.error("Send message error: ", e);
        } finally {
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (IOException e) {
                LOGGER.error("Stream closing error: ", e);
            }
        }
    }
}
