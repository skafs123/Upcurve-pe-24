package com.targetindia.models;

import java.util.Arrays;

public class SelectionSort implements Sortable{
    @Override
    public void sort(int[] arr) {
        //implement the sorting algorithm
        for (int i = 0; i < arr.length - 1; i++)
        {
            int index = i;
            for (int j = i + 1; j < arr.length; j++){
                if (arr[j] < arr[index]){
                    index = j;//searching for lowest index
                }
            }
            int smallerNumber = arr[index];
            arr[index] = arr[i];
            arr[i] = smallerNumber;
        }

        //print the sorted array
        System.out.println("Sorted Array :" + Arrays.toString(arr));
    }
}
