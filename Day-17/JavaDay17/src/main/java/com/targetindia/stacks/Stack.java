package com.targetindia.stacks;
public interface Stack<T> {
    public void push(T item);

    public T peek();

    public T pop();

    public boolean isEmpty();

}
