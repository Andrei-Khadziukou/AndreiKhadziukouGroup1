package com.epam.pattern.core.util;

import java.io.Closeable;
import java.io.IOException;
import org.apache.log4j.Logger;

/**
 * Created by Aliaksandr_Shynkevich on 2/21/15
 */
public final class SocketUtil {
    private static final Logger LOGGER = Logger.getLogger(SocketUtil.class);

    private SocketUtil() {
    }

    public static void close(Closeable closer) {
        if (closer != null) {
            try {
                closer.close();
            } catch (IOException e) {
                LOGGER.error("Instance close error: ", e);
            }
        }
    }
}
