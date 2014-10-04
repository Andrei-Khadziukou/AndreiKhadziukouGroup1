package com.company.api;

/**
 * Created by sanko on 10/4/14.
 */
public interface Command {

    String getCommandName();

    void execute();
}
