package com.design.pattern.bridge.shape;

import com.design.pattern.bridge.color.Color;

/**
 * Created by Sujeet on 11/08/18.
 */
public class Square extends ColoredShape {

    private final double length;

    public Square(Color color, double length) {
        super(color);
        this.length = length;
    }

    @Override
    public String getShape() {
        return "Square";
    }

    @Override
    public void drawShape() {
        System.out.println(color.getColor() + " " + getShape() + " : Total Area " + length * length);
    }
}
