package com.design.pattern.bridgewithdecorator.color;

import com.design.pattern.bridge.color.Color;

/**
 * Created by Sujeet on 11/08/18.
 */
public class EmulsionColor extends ColorMixin {

    public EmulsionColor(Color color) {
        super(color);
    }

    @Override
    public String getColor() {
        return "Emulsion , " + super.getColor();
    }
}
