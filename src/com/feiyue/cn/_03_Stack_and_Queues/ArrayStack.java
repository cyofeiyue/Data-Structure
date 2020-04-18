package com.feiyue.cn._03_Stack_and_Queues;

import com.feiyue.cn._02_Array.Student;

public class ArrayStack<E> implements Stack<E>{
    private Array<E> array;

    public ArrayStack() {
        array = new Array<>();
    }

    public ArrayStack(int capacity) {
        array = new Array<>(capacity);
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public void push(Object o) {
        array.addLast((E) o);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public E peek() {
        return array.get(getSize() - 1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ArrayStack: [");
        for (int i = 0; i < array.getSize(); i++) {
            sb.append(array.get(i));
            if (i != array.getSize() - 1) sb.append(",");
        }


        sb.append("] top");
        return sb.toString();
    }

    public static void main(String[] args) {

        ArrayStack<Student> arrayStack = new ArrayStack();
        arrayStack.push(new Student("Alice", 100));
        arrayStack.push(new Student("Bob", 66));
        arrayStack.push(new Student("Charlie", 88));

        System.out.println(arrayStack);
    }
}
