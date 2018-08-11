package com.design.pattern.bridge.shape;

import com.design.pattern.bridge.color.Color;

/**
 * Created by Sujeet on 11/08/18.
 */
public class Circle extends ColoredShape {

    private final double radius;

    public Circle(Color color, double radius) {
        super(color);
        this.radius = radius;
    }

    @Override
    public String getShape() {
        return "Circle";
    }

    @Override
    public void drawShape() {
        System.out.println(color.getColor() + " " + getShape() + " : Total Area " + (2 * Math.PI * radius));
    }
}
