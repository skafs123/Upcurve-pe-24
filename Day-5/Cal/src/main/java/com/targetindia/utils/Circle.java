package com.targetindia.utils;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString


public class Circle {
    protected    double radius = 1.0;
    protected    String color ="red";

    public Circle(double rad)
    {
        radius = rad;
    }
    public Circle(double rad,String col)
    {
        radius = rad;
        color = col;
    }
    public double getArea()
    {
        //pi*r*r
        var v = 3.142 * radius * radius;
        return v;
                       
    }

}
