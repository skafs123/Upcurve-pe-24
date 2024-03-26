package com.targetIndia.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)

public class Square extends Rectangle{

    public Square(double side) {
        super(side,side);

    }
    public Square(String color, boolean filled, double side) {
        super(color, filled,side,side);
    }

  /*  @Override
    public String toString() {
        return "Square{" +
                "  color='" + color + '\'' +
                ", filled=" + filled +
                ", side=" + width +
                '}';
    }*/
}
