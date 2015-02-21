package com.epam.pattern.broker.storage;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Aliaksandr_Shynkevich on 2/21/15
 */
public class MessagesStorage {
    
    private static MessagesStorage instance = new MessagesStorage();

    public static MessagesStorage getInstance() {
        return instance;
    }

    private ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

    private MessagesStorage() {
    }

    public void setMessage(String message) {
        queue.add(message);
    }
}
