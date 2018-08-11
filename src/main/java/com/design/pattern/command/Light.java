package com.design.pattern.command;

/**
 * Created by Sujeet on 01/07/18.
 */

//receiver
public class Light {


    private boolean isOn;

    private final String area;


    public Light(String area) {
        this.area = area;
    }

    public void toggle() {
        if (isOn) {
            off();
        } else {
            on();
        }
    }


    public void on() {
        System.out.println(area + " : lights switched on");
        isOn = true;
    }

    public void off() {
        System.out.println(area + " : lights switched off");
        isOn = false;
    }
}
