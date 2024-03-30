package com.targetindia.models;

import java.util.Arrays;

public class BubbleSort implements Sortable{
    @Override
    public void sort(int[] arr) {
        //implement the sorting algorithm

        int n = arr.length;
        int temp = 0;
        for(int i=0; i < n; i++){
            for(int j=1; j < (n-i); j++){
                if(arr[j-1] > arr[j]){
                    //swap elements
                    temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                }

            }
        }

        //print the sorted array
        System.out.println("Sorted Array :" + Arrays.toString(arr));
    }
}

