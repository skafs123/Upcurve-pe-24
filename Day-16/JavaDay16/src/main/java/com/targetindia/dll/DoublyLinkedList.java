package com.targetindia.dll;

import com.targetindia.sll.SinglyLinkedList;

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

}
