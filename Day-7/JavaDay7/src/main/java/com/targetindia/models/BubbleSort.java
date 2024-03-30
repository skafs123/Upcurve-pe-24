package com.targetindia.models;

import java.util.Arrays;

public class BubbleSort implements Sortable{
    @Override
    public void sort(int[] arr) {
        //implement the sorting algorithm

        //print the sorted array
        System.out.println("Sorted Array :" + Arrays.toString(arr));
    }
}

