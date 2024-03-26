package com.targetIndia.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Circle extends Shape{
    private double radius = 1.0;

    public Circle( double radius) {
        super();
        this.radius = radius;
    }
    public Circle(String color, boolean filled, double radius) {
        super(color, filled);
        this.radius = radius;
    }
    public  double getArea()
    {
        //pi*r*r
        var v = 3.142 * radius * radius;
        return v;

    }
    public double getPerimeter()
    {
        //2*pi*r
        var v = 2 * 3.142 * radius;
        return v;

    }
}
