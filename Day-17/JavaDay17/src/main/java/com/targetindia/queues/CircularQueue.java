package com.targetindia.queues;

import java.util.NoSuchElementException;

public class CircularQueue<T> implements Queue<T>{

    private int capacity = 5;
    private Object[] elements;
    private int front = -1;

    private int rear = -1;

    private int size = 0;

    public CircularQueue() {
        elements = new Object[capacity]; // O(1)
    } // O(1)

    public CircularQueue(int capacity) {
        this.capacity = capacity; // O(1)
        elements = new Object[capacity]; // O(1)
    } // O(1)

    @Override
    public void enqueue(T item) {
        if(size==capacity) // O(1)
        {
            grow();  // O(n)
        }
        if(size==0) {
            front=0;
        }
        if (rear == capacity - 1) { //has reached the end,so reset
            rear = -1; // O(1)
        }
        rear++; // O(1)
        elements[rear] = item; // O(1)
        size++; // O(1)
    } // O(n) <-- worst case time complexity

    private void grow() {

            //grow the array
            // make room for new elements or increase (usually double) the capacity
            capacity *= 2; // O(1)
            Object[] newElements = new Object[capacity]; // O(1)
            // copy values from `elements` to `newElements`
            if(front < rear)
            {
                for (int i = 0; i < size; i++) { // O(n)
                    newElements[i] = elements[i]; // O(1)
                }
            }
            else {
                //copy from front till size-1
                //copy from 0 till rear
                int j=0;
                for (int i = front; i < size; i++) {
                    newElements[j++] =elements[i] ; // O(1)
                }
                for (int i = 0; i <= rear; i++) {
                    newElements[j++] =elements[i] ; // O(1)
                }
                front =0; //O(1)
                rear = size-1; //O(1)

            }//O(n)

            // using the builtin native function
            // System.arraycopy(elements, 0, newElements, 0, elements.length);
            elements = newElements; // O(1)

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
            front = rear = -1; // O(1)
        }
        else {
            if(front == capacity-1)
              front = 0;
            else front++;
        } // O(1)
        size--; // O(1)
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

        if(front < rear) {
            for (int i = front; i < rear; i++) { // O(N)
                sb.append(elements[i]); // O(M)
                sb.append(", "); // O(M)
            }
            sb.append(elements[rear]); // O(M)
        } else if  (front==rear) { 
            sb.append(elements[front]); // O(M)
        }
        else
        {
                for (int i = front; i < capacity; i++) { // O(N)
                    sb.append(elements[i]); // O(M)
                    sb.append(", "); // O(M)
                }
                for (int i = 0; i < rear; i++) { // O(N)
                    sb.append(elements[i]); // O(M)
                    sb.append(", "); // O(M)
                }
                sb.append(elements[rear]); // O(M)
        }
        sb.append("]"); // O(M)
        return sb.toString(); // O(1)
    } // O(N)
}

