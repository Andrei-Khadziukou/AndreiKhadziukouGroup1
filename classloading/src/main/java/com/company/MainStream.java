package com.company;

import com.company.api.Command;
import com.company.loader.CommandsLoader;
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
    private CommandsLoader commandsLoader;

    public MainStream() {
        LOGGER.addAppender(new ConsoleAppender(new SimpleLayout()));
        commandsLoader = new CommandsLoader();
        init();
    }

    private void init() {
        try {
            while (true) {
                LOGGER.info("Hello, user. I wanna play in game!!!");
                LOGGER.info("Choose your destiny!!!\n");
                LOGGER.info("(0) Exit");
                showMenu();
                int choice = inputValue();
                if (choice == 0) {
                    break;
                }
                execute(choice);
                LOGGER.info("... Press enter ...");
                System.in.read();
                LOGGER.info("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int inputValue() throws IOException {
        while (true) {
            LOGGER.info("Your number: ");
            String line = reader.readLine();
            if (!pattern.matcher(line).matches()) {
                LOGGER.info("BAD BOY!!! Let's again...");
                continue;
            }
            return Integer.valueOf(line);
        }
    }

    private void showMenu() {
        int i = 0;
        for (Command command : commandsLoader.getCommands()) {
            LOGGER.info(String.format("(%d) %s", ++i, command.getCommandName()));
        }
    }

    private void execute(int commandIndex) {
        Command command = commandsLoader.getCommand(commandIndex - 1);
        if (command != null) {
            LOGGER.info("Executing... \n\n\n");
            command.execute();
            return;
        }
        LOGGER.info("Entered command index hasn't been founded!");
    }
}
