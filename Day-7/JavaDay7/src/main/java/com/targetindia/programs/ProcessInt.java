package com.targetindia.programs;
import java.util.Scanner;
import java.util.ArrayList;

public class ProcessInt {
    public static void main(String[] args) {

        try {


            boolean goAhead = true;
            int totalNumInts = 0;
            int totalNumStr = 0;
            int currInt = 0;
            int totalIntVal = 0;
            String currStr = "";
            ArrayList<Integer> arr1 = new ArrayList<Integer>(5);
            ArrayList<String> arr2 = new ArrayList<String>(5);

            while (goAhead) {
                System.out.println("Please provide integer number");
                Scanner sc = new Scanner(System.in);
                if (sc.hasNextInt()) {
                    currInt = sc.nextInt();
                    totalNumInts++;
                    totalIntVal += currInt;
                    arr1.add(currInt);

                } else {
                    currStr = sc.nextLine();
                    totalNumStr++;
                    arr2.add(currStr);

                }
                System.out.println("Do you want to proceed? if YES type \"YES\", else type \"NO\" ");
                Scanner sc1 = new Scanner(System.in);
                String str = sc1.nextLine();
                if (str.equals("NO"))
                    goAhead = false;

            }
            System.out.printf("Numer of inputs %d\n", totalNumInts + totalNumStr);
            System.out.printf("Numer of integer inputs %d\n", totalNumInts);
            System.out.printf("Numer of Non inter inputs  %d\n", totalNumStr);
            System.out.printf("Total sum of integer inputs %d\n", totalIntVal);

            System.out.println("The list of integer inputs:" + arr1);
            System.out.println("The list of Non-integer inputs:" + arr2);

            System.out.println();
        } catch (Exception e) {
            System.out.println("Something went wrong");
            System.out.println(e.getMessage());
        }
    }
}
