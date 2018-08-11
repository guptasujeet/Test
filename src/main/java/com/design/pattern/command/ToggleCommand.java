package com.design.pattern.command;

/**
 * Created by Sujeet on 01/07/18.
 */
public class ToggleCommand implements Command {


    private final Light light;

    public ToggleCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.toggle();
    }

}
