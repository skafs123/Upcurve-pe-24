package com.targetindia.search;

import com.targetindia.model.Customer;
import com.targetindia.utils.KeyboardUtil;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Customer[] customers = Customer.getCustomers();
        Arrays.sort(customers, (c1, c2) -> Integer.compare(c1.id(), c2.id()));

        while (true) {
            System.out.println("*** Main Menu ***");
            System.out.println("0. Exit");
            System.out.println("1. Display customers");
            System.out.println("2. Linear search");
            System.out.println("3. Binary search");
            System.out.println("4. Jump search");
            System.out.println("5. Fibonacci search");
            int choice = KeyboardUtil.getInt("Enter your choice: ");

            if (choice == 0) {
                System.out.println("Exiting...");
                break;
            }
            if (choice < 1 || choice > 5) {
                System.out.println("Invalid choice. Please try again");
                continue;
            }


            if (choice == 1) {
                for (var c : customers) {
                    System.out.println(c);
                }
                continue;
            }

            int id = KeyboardUtil.getInt("Enter customer id to search: ");
            int index = -1;

            if (choice == 2) {
                index = linearSearch(customers, id);
            } else if (choice == 3) {
                index = binarySearch(customers, id);
            } else if (choice == 4) {
                index = jumpSearch(customers, id);
            } else {
                index = fibonacciSearch(customers, id);
            }


            if (index == -1) {
                System.out.println("No customer found with id " + id);
            } else {
                var c = customers[index];
                System.out.printf("Customer data found at index %d:\n", index);
                System.out.println("Name    : " + c.name());
                System.out.println("City    : " + c.city());
                System.out.println("Email   : " + c.email());
                System.out.println("Phone   : " + c.phone());
            }
            System.out.println();
        }
    }

    private static int linearSearch(Customer[] customers, int id) {
        for (int index = 0; index < customers.length; index++) { // O(n)
            if (customers[index].id() == id) { // O(1)
                return index; // O(1)
            }
        }

        return -1; // O(1)
    } // O(n)


    private static int binarySearch(Customer[] customers, int id) {
        // prerequisite for this search --> customers is in ascending order of their ids
        int low = 0;
        int high = customers.length - 1;

        if (customers[low].id() == id) {
            return 0;
        }
        if (customers[high].id() == id) {
            return high;
        }
        if (id < customers[low].id() || id > customers[high].id()) {
            return -1;
        }

        int pass = 0;
        while (low <= high) {
            pass++;
            int mid = (low + high) / 2;
            if (id == customers[mid].id()) {
                // found the element
                System.out.println("pass=" + pass);
                return mid;
            } else if (id < customers[mid].id()) {
                // the target element may exist on the left side of `mid`
                high = mid - 1;
            } else {
                // the target element may exist on the right side of `mid`
                low = mid + 1;
            }
        }
        System.out.println("pass=" + pass);
        return -1;
    }

    private static int jumpSearch(Customer[] customers, int id) {

        int blockSize = (int) Math.sqrt(customers.length);
        int prev = 0;
        int curr = blockSize;
        int loopCount = 0;

        while (curr < customers.length && customers[curr].id() <= id) {
            prev = curr;
            curr += blockSize;
            loopCount++;
        }

        for (int i = prev; i < Math.min(curr, customers.length); i++) {
            loopCount++;
            if (customers[i].id() == id) {
                System.out.println("loopCount is " + loopCount);
                return i;
            }
        }

        System.out.println("loopCount is " + loopCount);
        return -1;
    }

    public static int fibonacciSearch(Customer[] customers, int id)
    {
        int size = customers.length;

        /* Initialize fibonacci numbers */
        int fibMMm2 = 0; // (m-2)'th Fibonacci No.
        int fibMMm1 = 1; // (m-1)'th Fibonacci No.
        int fibM = fibMMm2 + fibMMm1; // m'th Fibonacci

        /* fibM is going to store the smallest
        Fibonacci Number greater than or equal to size */
        while (fibM < size) {
            fibMMm2 = fibMMm1;
            fibMMm1 = fibM;
            fibM = fibMMm2 + fibMMm1;
        }

        // Marks the eliminated range from front
        int offset = -1;

        /* while there are elements to be inspected.
        Note that we compare customers[fibMm2] with id.
        When fibM becomes 1, fibMm2 becomes 0 */
        while (fibM > 1) {
            // Check if fibMm2 is a valid location
            int i = Math.min(offset + fibMMm2, size - 1);

            /* If id is greater than the value at
            index fibMm2 move one position back*/
            if (customers[i].id() < id) {
                fibM = fibMMm1;
                fibMMm1 = fibMMm2;
                fibMMm2 = fibM - fibMMm1;
                offset = i;
            }

            /* If id is less than the value at index
            fibMm2 move 2 positions back*/
            else if (customers[i].id() > id) {
                fibM = fibMMm2;
                fibMMm1 = fibMMm1 - fibMMm2;
                fibMMm2 = fibM - fibMMm1;
            }

            /* element found. return index */
            else
                return i;
        }

        /* comparing the last element with id */
        if (fibMMm1 == 1 && customers[size-1].id() == id)
            return size-1;

        /*element not found. return -1 */
        return -1;
    }

}
