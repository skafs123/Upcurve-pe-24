package com.targetindia.models;
import lombok.*;

@Getter
@Setter
public class DupArray {

    Word[] array; // Array of words
    int size; // Size of array

    // constructor
    public DupArray(String str[], int size)
    {
        this.size = size;
        array = new Word[size];

        // One by one copy words from the
        // given wordArray to dupArray
        int i;
        for (i = 0; i < size; ++i) {
            // create a word Object with the
            // str[i] as str and index as i
            array[i] = new Word(str[i], i);
        }
    }

}
