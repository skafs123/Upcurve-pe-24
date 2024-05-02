package com.targetindia.queues;

import java.util.NoSuchElementException;

public class ArrayQueue<T> implements Queue<T>{

    private int capacity = 5;
    private Object[] elements;
    private int front = -1;

    private int rear = -1;

    private int size = 0;

    public ArrayQueue() {
        elements = new Object[capacity]; // O(1)
    } // O(1)

    public ArrayQueue(int capacity) {
        this.capacity = capacity; // O(1)
        elements = new Object[capacity]; // O(1)
    } // O(1)

    @Override
    public void enqueue(T item) {
        if (rear == capacity - 1) { // O(1)
            grow();  // O(n)
        }
        if(size==0)
            front=0;
        rear++; // O(1)
        elements[rear] = item; // O(1)
        size++;
    } // O(n) <-- worst case time complexity

    private void grow() {
        if(size < capacity)
        {
            //slide the queue to the front of the array
            for (int i = 0; i < size; i++) { // O(n)
                 elements[i] =elements[front+i] ; // O(1)
            }
            front=0;// O(1)
            rear = size-1;// O(1)
        }
        else
        {
            //grow the array
            // make room for new elements or increase (usually double) the capacity
            capacity *= 2; // O(1)
            Object[] newElements = new Object[capacity]; // O(1)
            // copy values from `elements` to `newElements`
            for (int i = 0; i < elements.length; i++) { // O(n)
                newElements[i] = elements[i]; // O(1)
            }
            // using the builtin native function
            // System.arraycopy(elements, 0, newElements, 0, elements.length);
            elements = newElements; // O(1)
        }

    } // O(n)

    @Override
    public T peek() {
        if (size == 0) { // O(1)
            throw new NoSuchElementException("Stack is empty!"); // O(1)
        }
        return (T) elements[front]; // O(1)
    } // O(1)

    @Override
    public T dequeue() {
        if (size == 0) { // O(1)
            throw new NoSuchElementException("Stack is empty!"); // O(1)
        }
        var data = elements[front]; // O(1)
        elements[front] = null; // O(1)
        if(size == 1 )
        {
            front = rear = -1;
        }
        else {
            front++; // O(1)
        }
        size--;
        return (T) data; // O(1)
    } // O(1)

    @Override
    public boolean isEmpty() {
        return size == 0; // O(1)
    } // O(1)

    @Override
    public int size() {
        return this.size; // O(1)
    }

    @Override
    public String toString() {

        System.out.println("Queue size: " + size + " Queue capacity: " + capacity + " Front: " + front + " Rear: "+ rear);

        if (size == 0) { // O(1)
            return "[]"; // O(1)
        }

        StringBuilder sb = new StringBuilder(); // O(1)
        sb.append("["); // O(M)
        for (int i = front; i < rear; i++) { // O(N)
            sb.append(elements[i]); // O(M)
            sb.append(", "); // O(M)
        }
        sb.append(elements[rear]); // O(M)
        sb.append("]"); // O(M)
        return sb.toString(); // O(1)
    } // O(N)
}
