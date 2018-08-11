package com.design.pattern.command;

/**
 * Created by Sujeet on 01/07/18.
 */
public class CommandDemo {

    public static void main(String[] args) {
        Light kitchenLight = new Light("kitchen1");
        Light bedroomLight = new Light("bedroom1");


        Command lightOnCommand = new OnCommand(kitchenLight);
        Command lightOffCommand = new OffCommand(kitchenLight);

        System.out.println("--- 1 way -----------------");
        Switch kitchenSwitch = new OneWaySwitch();
        kitchenSwitch.execute(lightOnCommand);
        kitchenSwitch.execute(lightOnCommand);
        kitchenSwitch.execute(lightOffCommand);

        Command toggleCommand = new ToggleCommand(bedroomLight);

        Switch twoWaySwitch1 = new TwoWaySwitch();
        Switch twoWaySwitch2 = new TwoWaySwitch();

        System.out.println("--- 2 way -----------------");
        twoWaySwitch1.execute(toggleCommand);
        twoWaySwitch1.execute(toggleCommand);
        twoWaySwitch1.execute(toggleCommand);
        twoWaySwitch2.execute(toggleCommand);
        twoWaySwitch2.execute(toggleCommand);
        twoWaySwitch1.execute(toggleCommand);

    }

}
