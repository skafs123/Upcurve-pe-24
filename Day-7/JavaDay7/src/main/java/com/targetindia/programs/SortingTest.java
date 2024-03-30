package com.targetindia.programs;
import com.targetindia.models.*;

import java.util.Arrays;

public class SortingTest {
    public static void main(String[] args) {

            BubbleSort bSort = new BubbleSort();
            SelectionSort sSort = new SelectionSort();
            MergeSort mSort = new MergeSort();
            int[] Arr1 = {5,2,9,1,7};
            int[] Arr2 = {8,3,6,4,2};
            int[] Arr3=  {10,6,3,8,1};

            Sorter sorter = new Sorter();
            System.out.println("Using Bubble sort");
            System.out.println("Original Array:" + Arrays.toString(Arr1));
            sorter.sort(bSort,Arr1);

            System.out.println("Using Selection sort");
            System.out.println("Original Array:" + Arrays.toString(Arr2));
            sorter.sort(sSort,Arr2);

            System.out.println("Using Merge sort");
            System.out.println("Original Array:" + Arrays.toString(Arr3));
            sorter.sort(mSort,Arr3);




    }
}
