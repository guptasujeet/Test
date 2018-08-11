package com.design.pattern.command;

/**
 * Created by Sujeet on 01/07/18.
 */
public class OneWaySwitch implements Switch {

    public void execute(Command command) {
        command.execute();
    }

}
