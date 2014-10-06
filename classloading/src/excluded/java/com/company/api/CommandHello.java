package com.company.api;

import com.company.api.Command;
import org.apache.log4j.Logger;

/**
 * Created by sanko on 10/4/14.
 */
public class CommandHello implements Command {

    @Override
    public String getCommandName() {
        return "Print \"Hello\" ";
    }

    @Override
    public void execute() {
        System.out.println("Hello!!!");
    }
}
