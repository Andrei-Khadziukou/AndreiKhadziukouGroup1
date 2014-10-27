package com.epam.mentor;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

/**
 * Created by sanko on 10/25/14.
 */
public class DeadLockMain {

    private static Logger LOG = Logger.getLogger(DeadLockMain.class);

    public static Object Lock1 = new Object();
    public static Object Lock2 = new Object();

    public static void main(String args[]) {
        LOG.addAppender(new ConsoleAppender(new SimpleLayout()));

        ThreadDemo1 t1 = new ThreadDemo1();
        ThreadDemo2 t2 = new ThreadDemo2();
        t1.start();
        t2.start();
    }

    private static class ThreadDemo1 extends Thread {
        public void run() {
            synchronized (Lock1) {
                LOG.info("Thread 1: Holding lock 1...");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {}
                LOG.info("Thread 1: Waiting for lock 2...");
                synchronized (Lock2) {
                    LOG.info("Thread 1: Holding lock 1 & 2...");
                }
            }
        }
    }
    private static class ThreadDemo2 extends Thread {
        public void run() {
            synchronized (Lock2) {
                LOG.info("Thread 2: Holding lock 2...");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {}
                LOG.info("Thread 2: Waiting for lock 1...");
                synchronized (Lock1) {
                    LOG.info("Thread 2: Holding lock 1 & 2...");
                }
            }
        }
    }
}
