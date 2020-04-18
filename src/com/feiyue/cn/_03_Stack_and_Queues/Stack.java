package com.feiyue.cn._03_Stack_and_Queues;

public interface Stack<E> {
    int getSize();
    boolean isEmpty();
    void push(E e);
    E pop();
    E peek();

}
