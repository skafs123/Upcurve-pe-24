package com.targetindia.programs;
import com.targetindia.utils.Circle;
import com.targetindia.utils.Cylinder;



public class InheritPolyMorph {

    public static void main(String[] args) {
        Circle cir = new Circle(5,"blue");

        System.out.printf(" Details of Circle : %s ", cir.toString());
        System.out.println();

        Circle[] circles = {new Cylinder(12.34),
            new Cylinder(12.34,10.0),
                new Cylinder(12.34,10.0,"blue" ),
                new Cylinder(12.34,20.0,"green" )};

        for(int i=0;i<circles.length; i++)
        {
            Cylinder cyl = (Cylinder) circles[i];
            System.out.printf(" Radius = %.2f Height = %.2f Color = %s ,   Area = %.2f Volume =  %.2f", cyl.getRadius(),cyl.getHeight(), cyl.getColor(), cyl.getArea(),cyl.getVolume());
            System.out.println();


        }
    }


}
