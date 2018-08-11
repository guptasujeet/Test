package com.design.pattern.bridge.shape;

import com.design.pattern.bridge.color.Color;

/**
 * Created by Sujeet on 11/08/18.
 */
public abstract class ColoredShape implements Shape {

    protected final Color color;


    protected ColoredShape(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
