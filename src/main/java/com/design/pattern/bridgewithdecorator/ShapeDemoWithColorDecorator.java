package com.design.pattern.bridgewithdecorator;

import com.design.pattern.bridge.color.Blue;
import com.design.pattern.bridge.color.Color;
import com.design.pattern.bridge.color.Green;
import com.design.pattern.bridge.color.Red;
import com.design.pattern.bridge.shape.Circle;
import com.design.pattern.bridge.shape.Shape;
import com.design.pattern.bridge.shape.Square;
import com.design.pattern.bridge.shape.Triangle;
import com.design.pattern.bridgewithdecorator.color.DistemperColor;
import com.design.pattern.bridgewithdecorator.color.EmulsionColor;

/**
 * Created by Sujeet on 11/08/18.
 */
public class ShapeDemoWithColorDecorator {

    public static void main(String[] args) {
        Color red = new Red();
        Color green = new Green();
        Color blue = new Blue();

        Shape redTriangle = new Triangle(red, 3.0, 8.0);
        redTriangle.drawShape();

        Shape greenDistemperTriangle = new Triangle(new DistemperColor(green), 5.0, 6.0);
        greenDistemperTriangle.drawShape();


        Shape redEmulsionSquare = new Square(new EmulsionColor(red), 5.0);
        redEmulsionSquare.drawShape();

        Shape blueDistemperPlusEmulsionCircle = new Circle(new DistemperColor(new EmulsionColor(blue)), 5.0);
        blueDistemperPlusEmulsionCircle.drawShape();
    }


}
