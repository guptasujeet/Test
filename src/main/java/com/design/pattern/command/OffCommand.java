package com.design.pattern.command;

/**
 * Created by Sujeet on 01/07/18.
 */
public class OffCommand implements Command {


    private final Light light;

    public OffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

}
