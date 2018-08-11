package com.design.pattern.bridge.shape;

import com.design.pattern.bridge.color.Color;

/**
 * Created by Sujeet on 11/08/18.
 */
public interface Shape {

    String getShape();

    Color getColor();

    void drawShape();

}
