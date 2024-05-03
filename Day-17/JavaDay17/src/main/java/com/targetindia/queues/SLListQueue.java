package com.targetindia.queues;
import java.util.NoSuchElementException;

public class SLListQueue<T> implements Queue<T> {
    //In a traditional singly linked list
    // you have only front/head to traverse the list.
    //But for a queue you can have a variation of SLL
    //that contains both front and rear. Since queues are FIFO
    //you do not need to delete the node referenced by rear.

    class Node {
        T data;
        Node next;

        public Node(T data) {
            this.data = data;
            this.next = null; // not needed
        }
    }

    private Node front; // = null
    private Node rear; // = null
    private int size = 0;

    @Override
    public void enqueue(T value) {
        Node newNode = new Node(value); // O(1)
        if (isEmpty()) { // O(1)
            front = newNode; // O(1)
        } else {
            rear.next = newNode; // O(1)

        }
        rear = newNode; // O(1)
        size++; // O(1)
    } // O(1)

    @Override
    public T peek() throws NoSuchElementException {
        if (isEmpty()) { // O(1)
            throw new NoSuchElementException("Queue is empty; nothing to peek"); // O(1)
        }

        return front.data; // O(1)
    } // O(1)

    @Override
    public T dequeue() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty; nothing to peek");
        } // O(1)
        size--; // O(1)
        var curr = front; // O(1)
        if (front == rear) { // exactly 1 element in the queue
            front = rear = null; // O(1)
        } else {
            front = curr.next; // O(1)
            curr.next = null; // O(1)
        }
        return curr.data; // O(1)
    }

    @Override
    public boolean isEmpty() {
        return front == null && rear == null; // O(1)
    } // O(1)

    @Override
    public int size() {
        return this.size; // O(1)
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Head <-> Tail";
        }

        StringBuilder sb = new StringBuilder(); // O(1)
        sb.append("Head <-> "); // O(M)
        Node curr;
        for (curr = front; curr != null; curr = curr.next) { // O(N)
            sb.append(curr.data); // O(M)
            sb.append(" <-> "); // O(M)
        }
        sb.append("Tail"); // O(M)
        return sb.toString(); // O(1)
    } // O(N)
}
