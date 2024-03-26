package com.targetIndia.models;

import lombok.*;

@NoArgsConstructor
@ToString(callSuper = true)


public class Staff extends Person {
    private String school;
    private double pay;

    public Staff(String name, String address, String school, double pay) {
        super(name, address);
        this.school = school;
        this.pay = pay;
    }


}
