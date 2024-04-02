package com.targetindia.models;
import lombok.*;

@Getter
@Setter

public class Word {
    String str; // to store word itself
    int index; // index of the word in the
    // original array

    // constructor
    Word(String str, int index)
    {
        this.str = str;
        this.index = index;
    }
}
