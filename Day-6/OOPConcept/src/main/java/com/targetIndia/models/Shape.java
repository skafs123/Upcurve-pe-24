package com.targetIndia.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public abstract class  Shape {
    protected String color = "red";
    protected boolean filled = true;

    public abstract double getArea();
    public abstract double getPerimeter();

}
