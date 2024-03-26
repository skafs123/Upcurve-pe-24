package com.targetIndia.programs;

import com.targetIndia.models.Square;
import com.targetIndia.models.Rectangle;
import com.targetIndia.models.Circle;
import com.targetIndia.models.Shape;



public class Assignment2 {
    public static void main(String[] args) {
        Shape[] shapes = {
                new Circle("red",true,2.5),
                new Circle("green",false,3),
                new Circle("blue",true,3.5),
                new Circle("yellow",true,4),
                new Rectangle("blue",true,2,6),
                new Rectangle("red",false,4,5),
                new Rectangle("green",false,3,6),
                new Rectangle("white",true,2.5,3.5),
                new Square("green",true,8),
                new Square("red",false,4)
        };

        for(int i=0;i<shapes.length;i++)
        {
            System.out.printf(shapes[i].toString());
            System.out.printf(" area = %.2f perimeter = %.2f", shapes[i].getArea(),shapes[i].getPerimeter());
            System.out.println();
        }
    }
}
