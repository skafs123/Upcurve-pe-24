package com.targetindia.bst;

import com.targetindia.utils.KeyboardUtil;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.RandomAccess;

public class Main {
    public static void main(String[] args) {
        int choice;
        int data;
        BinarySearchTree bst = new BinarySearchTree();

        /*Random r = new Random();
        for (int i = 0; i < 15; i++) {
            bst.insert(r.nextInt(500));
        }*/
        bst.insert(100);
        bst.insert(50);
        bst.insert(40);
        bst.insert(55);
        bst.insert(60);
        bst.insert(200);
        bst.insert(150);
        bst.insert(300);
        bst.insert(250);
        bst.insert(310);

        while ((choice = menu()) != 0) {

            switch (choice) {
                case 1:
                    data = KeyboardUtil.getInt("Enter data to insert: ");
                    bst.insert(data);
                    break;
                case 2:
                    bst.display();
                    break;
                case 3:
                    bst.preorderDisplay();
                    break;
                case 4:
                    bst.inorderDisplay();
                    break;
                case 5:
                    bst.postorderDisplay();
                    break;
                case 6:
                    try {
                        data = KeyboardUtil.getInt("Enter data to delete: ");
                        bst.delete(data);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid type for data. Please try with integers only.");
                    }
                    break;
                case 7:int smallest = bst.findSmallestValue();
                    System.out.println("Smallest Value in the tree: " + smallest);
                    break;
                case 8: int largest = bst.findLargestValue();
                    System.out.println("Largest Value in the tree: " + largest);
                    break;
                case 9:
                    try {
                        data = KeyboardUtil.getInt("Enter data : ");
                        int depth = bst.findDepth(data);
                        System.out.println("Depth of the node in the tree: " + depth);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid type for data. Please try with integers only.");
                    }
                    break;
                case 10:
                    int length = bst.findTreeLength();
                    System.out.println("Length of the tree: " + length);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        }

        System.out.println("Exiting...");
    }

    private static int menu() {
        System.out.println("*** Main menu ***");
        System.out.println("0. Exit");
        System.out.println("1, Insert");
        System.out.println("2. Display");
        System.out.println("3. Preorder traversal");
        System.out.println("4. Inorder traversal");
        System.out.println("5. Postorder traversal");
        System.out.println("6. Delete");
        // TODO: tasks for assignment
        System.out.println("7. Find the smallest value");
        System.out.println("8. Find the largest value");
        System.out.println("9. Find the depth of a value");
        System.out.println("10. Find the length of the tree");


        try {
            return KeyboardUtil.getInt("Enter your choice: ");
        } catch (InputMismatchException e) {
            return -1;
        }
    }
}
