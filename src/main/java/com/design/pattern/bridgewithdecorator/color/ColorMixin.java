package com.design.pattern.bridgewithdecorator.color;

import com.design.pattern.bridge.color.Color;

/**
 * Created by Sujeet on 11/08/18.
 */
public abstract class ColorMixin implements Color {

    private final Color color;

    protected ColorMixin(Color color) {
        this.color = color;
    }

    @Override
    public String getColor() {
        return color.getColor();
    }
}
