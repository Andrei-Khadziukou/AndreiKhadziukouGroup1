package com.company;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

/**
 * classloading class.
 * <p/>
 * Copyright (C) 2014 copyright.com
 * <p/>
 * Date: Sep 10, 2014
 *
 * @author Aliaksandr_Shynekvich
 */
public class MainStream {

    private static Logger LOGGER = Logger.getLogger(MainStream.class);
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private Pattern pattern = Pattern.compile("[0-9]+");

    public MainStream() {
        LOGGER.addAppender(new ConsoleAppender(new SimpleLayout()));
        init();
    }

    private void init() {
        try {
            while (true) {
                LOGGER.info("Hello, user. I wanna play in game!!!");
                LOGGER.info("Choose your destiny!!!\n");
                LOGGER.info("(0) Exit");
                int choice = inputValue();
                if (choice == 0) {
                    break;
                }
                LOGGER.info("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int inputValue() throws IOException {
        while (true) {
            String line = reader.readLine();
            if (!pattern.matcher(line).matches()) {
               LOGGER.info("BAD BOY!!! Let's again...");
               continue;
            }
            return Integer.valueOf(line);
        }
    }
}
