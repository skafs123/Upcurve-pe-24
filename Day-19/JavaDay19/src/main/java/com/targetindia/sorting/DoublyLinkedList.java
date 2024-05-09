package com.targetindia.sorting;

import javax.swing.tree.TreeNode;
import java.util.NoSuchElementException;

public class DoublyLinkedList {

    class ListNode {
        ListNode prev;
        int data;
        ListNode next;

        public ListNode(int data) {
            this.data = data;
            this.prev = null; // this is the default behavior; no need to do this.
            this.next = null; // this is the default behavior; no need to do this.
        }

    }

    private ListNode head;
    private ListNode tail;
    private int length;


    public int size() {
        return length;
    }

    public void prepend(int data) {
        length++;
        var newNode = new ListNode(data);
        if (head == null) {
            head = tail = newNode;
            return;
        }

        newNode.next = head;
        head.prev = newNode;
        head = newNode;
    } //O(1)

    public void append(int data) {
        length++;
        var newNode = new ListNode(data);
        if (head == null) { // or tail==null
            head = tail = newNode;
            return;
        }

        newNode.prev = tail;
        tail = tail.next = newNode;
    } //O(1)

    public void displayForward() {
        var curr = head;
        System.out.print("HEAD <-> ");
        while (curr != null) {
            System.out.print(curr.data);
            System.out.print(" <-> ");
            curr = curr.next;
        }
        System.out.println("TAIL");
    }


    public void displayBackward() {
        var curr = tail;
        System.out.print("TAIL <-> ");
        while (curr != null) {
            System.out.print(curr.data);
            System.out.print(" <-> ");
            curr = curr.prev;
        }
        System.out.println("HEAD");
    }

    public int deleteFirst() throws NoSuchElementException {
        if (head == null) { // or tail==null
            throw new NoSuchElementException("List is empty!");
        }

        length--;

        if (head == tail) {
            // only element in the list
            int data = head.data;
            head = tail = null;
            return data;
        } //O(1)

        var nodeToDelete = head; //O(1)
        head = head.next; //O(1)
        head.prev = null; //O(1)
        nodeToDelete.next = null; //O(1)
        return nodeToDelete.data; //O(1)
    }
    public int deleteLast() throws NoSuchElementException {
        if (head == null) { // or tail==null
            throw new NoSuchElementException("List is empty!");
        }

        length--;

        if (head == tail) {
            // only element in the list
            int data = head.data;
            head = tail = null;
            return data;
        } //O(1)

        var nodeToDelete = tail; //O(1)
        tail = nodeToDelete.prev; //O(1)
        tail.next = null; //O(1)
        nodeToDelete.prev = null; //O(1)
        return nodeToDelete.data; //O(1)

    }

    public int deleteAt(int index)throws IndexOutOfBoundsException {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        if (index == 0) {
            return this.deleteFirst();//O(1)
        }
        if (index == length-1) {
            return this.deleteLast();//O(1)
        }

        var curr = head;
        for (int i = 0; i < index - 1; i++) {
            curr = curr.next;
        } //O(n)

        var nodeToDelete = curr.next; //O(1)
        curr.next = nodeToDelete.next; //O(1)
        nodeToDelete.next.prev = curr; //O(1)
        nodeToDelete.next = null; //O(1)
        nodeToDelete.prev = null; //O(1)
        length--; //O(1)
        return nodeToDelete.data; //O(1)


    }

    public void insert(int index, int data) throws IndexOutOfBoundsException {

        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        if (index == 0) {
            this.prepend(data);
            return;
        }//O(1)

        if (index == length) {
            this.append(data);
            return;
        } //O(n)

        var curr = head;
        for (int i = 0; i < index - 1; i++) {
            curr = curr.next;
        } //O(n)
        var newNode = new ListNode(data); //O(1)
        newNode.next = curr.next; //O(1)
        newNode.prev = curr; //O(1)
        curr.next = newNode; //O(1)
        newNode.next.prev = newNode; //O(1)
        length++; //O(1)
    }
    public int indexOf(int data) {
        int index = 0;
        for (var curr = head; curr != null; curr = curr.next) { // O(n)
            if (data == curr.data) {
                return index;
            }
            index++;
        }
        return -1;
    } // O(n)
    public int get(int index)
    {
        int i=0;
        for (var curr = head; curr != null; curr = curr.next) { // O(n)
            if (index == i) {
                return curr.data;
            }
            i++;
        }
        return -1;
    }


    public  void bubbleSort() {
        int len = size();

        displayForward();
        for (int i = 0; i < len - 1; i++) {
            //displayForward();
            var curr = head;
            for (int j = 0; j < len - 1 - i; j++,curr=curr.next) {
                if (curr.data > curr.next.data ){
                    var tmp = curr.data;
                    curr.data = curr.next.data;
                    curr.next.data = tmp;

                }
            }
        }
        // displayForward();
    }




    public  void selectionSort() {
        int len = size();
        var outerCurr=head;
        var innerCurr=head;

        displayForward();
        for (int i = 0; i < len - 1; i++,outerCurr=outerCurr.next) {
            var minNode = outerCurr;

            for (innerCurr=outerCurr.next; innerCurr != null; innerCurr=innerCurr.next) {
                if (innerCurr.data < minNode.data) {
                    minNode = innerCurr;
                }
            }

            if (outerCurr.data == minNode.data) continue; // there is no need to do swapping

            var tmp = outerCurr.data;
            outerCurr.data = minNode.data;
            minNode.data = tmp;
        }
    }

    public  void insertionSort() {
        var len = size();
        displayForward();
        for ( var outerCurr=head.next;outerCurr!=null; outerCurr=outerCurr.next) {
            var key = outerCurr.data;
            var innerCurr=outerCurr.prev;
            while(innerCurr!=null && innerCurr.data > key) {
                innerCurr.next.data = innerCurr.data;
                innerCurr = innerCurr.prev;
            }
            if(innerCurr == null )
                head.data = key;
            else
                innerCurr.next.data = key;

        }
    }

}
