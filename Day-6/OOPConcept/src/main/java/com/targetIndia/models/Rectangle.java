package com.targetIndia.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Rectangle extends Shape{
    protected   double width = 1.0;
    protected   double length = 1.0;


    public Rectangle(double width,double length) {
        super();
        this.width = width;
        this.length = length;
    }
    public Rectangle(String color, boolean filled, double width,double length) {
        super(color, filled);
        this.width = width;
        this.length = length;
    }
    public double getArea()
    {
        //w * l
        var v = width * length;
        return v;

    }
    public double getPerimeter()
    {
        //2*(w + l))
        var v = 2 * (width + length);
        return v;

    }

}
