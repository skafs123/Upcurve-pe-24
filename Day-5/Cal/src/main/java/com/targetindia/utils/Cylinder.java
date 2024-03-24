package com.targetindia.utils;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString

public class Cylinder extends Circle {
    double height = 1.0;
    public Cylinder(double rad)
    {
        super(rad);
    }
    public Cylinder(double rad,double ht)
    {
        super(rad);
        height = ht;

    }
    public Cylinder(double rad,double ht,String col)
    {
        super(rad,col);
        height = ht;
    }
    public double getVolume()
    {
        //pi * r* r * h
            double vol =  getArea()*height;
            return vol ;

    }


}
