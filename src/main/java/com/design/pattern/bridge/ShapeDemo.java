package com.design.pattern.bridge;

import com.design.pattern.bridge.color.Blue;
import com.design.pattern.bridge.color.Color;
import com.design.pattern.bridge.color.Green;
import com.design.pattern.bridge.color.Red;
import com.design.pattern.bridge.shape.Circle;
import com.design.pattern.bridge.shape.Shape;
import com.design.pattern.bridge.shape.Square;
import com.design.pattern.bridge.shape.Triangle;

/**
 * Created by Sujeet on 11/08/18.
 */
public class ShapeDemo {

    public static void main(String[] args) {
        Color red = new Red();
        Color green = new Green();
        Color blue = new Blue();

        Shape redTriangle = new Triangle(red, 3.0, 8.0);
        redTriangle.drawShape();

        Shape greenTriangle = new Triangle(green, 5.0, 6.0);
        greenTriangle.drawShape();


        Shape redSquare = new Square(red, 5.0);
        redSquare.drawShape();

        Shape blueCircle = new Circle(blue, 5.0);
        blueCircle.drawShape();
    }

}
