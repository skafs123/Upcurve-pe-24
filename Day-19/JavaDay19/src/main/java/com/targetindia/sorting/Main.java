package com.targetindia.sorting;

import com.targetindia.utils.KeyboardUtil;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

// Assignment tasks for day 19
// TODO: Implement which ever sorting techniques possible on a linked list
public class Main {

    public static void main(String[] args) {


        DoublyLinkedList list = new DoublyLinkedList();

        initialize(list);

        int choice;
        the_loop:
        while (true) {
            // display the list content (unsorted)
            list.displayForward();

            System.out.println("\n*** Main Menu ***");
            System.out.println("0. Exit");
            System.out.println("1. Re-initialize the array");
            System.out.println("2. Bubble sort");
            System.out.println("3. Selection sort");
            System.out.println("4. Insertion sort");
            System.out.println("5. Merge sort");

            try {
                choice = KeyboardUtil.getInt("Enter your choice: ");
            } catch (Exception e) {
                choice = -1;
            }

            switch (choice) {
                case 0:
                    System.out.println("Exiting...");
                    break the_loop;
                case 1:
                    list = new DoublyLinkedList();
                    initialize(list);
                    break;
                case 2:
                    list.bubbleSort();
                    break;
                case 3:
                   list.selectionSort();
                    break;
                case 4:
                   list.insertionSort();
                   break;
                case 5:
                    //mergeSort(array);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void initialize(DoublyLinkedList list)
    {

        // let's populate the list with some initial values
        // Stream.of(19, 49, 13, 59, 333, 283, 293, 100,44,67).forEach(list::append);

        int length = 10;
        Random r = new Random();
        for (int i = 0; i < length; i++) {
            list.append(r.nextInt(100)) ;
        }
    }


    public static void mergeSort(int[] array) {
        if (array.length > 1) { // terminal condition for recursion
            var mid = array.length / 2;
            int[] left = new int[mid];
            int[] right = new int[array.length - mid];
            System.arraycopy(array, 0, left, 0, mid);
            System.arraycopy(array, mid, right, 0, array.length - mid);


            mergeSort(left);
            mergeSort(right);

            // System.out.println(Arrays.toString(left));
            // System.out.println(Arrays.toString(right));

            int i = 0; // index for `left`
            int j = 0; // index for `right`
            int k = 0; // index for `array`, to which `left` and `right` are going to be merged
            while (i < left.length && j < right.length) {
                if (left[i] < right[j]) {
                    array[k++] = left[i++];
                } else {
                    array[k++] = right[j++];
                }
            }

            while (i < left.length) {
                array[k++] = left[i++];
            }
            while (j < right.length) {
                array[k++] = right[j++];
            }
            // System.out.println(Arrays.toString(array));

        }
    }

}
