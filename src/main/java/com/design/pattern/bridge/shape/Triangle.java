package com.design.pattern.bridge.shape;

import com.design.pattern.bridge.color.Color;

/**
 * Created by Sujeet on 11/08/18.
 */
public class Triangle extends ColoredShape {

    private final double base;
    private final double height;

    public Triangle(Color color, double base, double height) {
        super(color);
        this.base = base;
        this.height = height;
    }

    @Override
    public String getShape() {
        return "Triangle";
    }

    @Override
    public void drawShape() {
        System.out.println(color.getColor() + " " + getShape() + " : Total Area " + (base * height) / 2);
    }
}
