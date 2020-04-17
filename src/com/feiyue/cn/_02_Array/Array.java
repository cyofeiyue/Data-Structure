package com.feiyue.cn._02_Array;

public class Array<E> {
    private E[] elements;
    private int size = 0;

    private static final int ELEMENT_NOT_FOUND = -1;
    private static final int DEFAULT_CAPACITY = 10;

    public Array(int capacity) {
        capacity = (capacity < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capacity;
        elements = (E[]) new Object[capacity];
    }

    public Array() {
        this(DEFAULT_CAPACITY);
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 在index索引的位置插入一个新元素e
    public void add(int index, E element) {
        //if (size == data.length) throw new IllegalArgumentException("Add failed. Array is full.");
        if (size == elements.length) resize(elements.length << 1);
        if (index < 0 || index > size) throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");

        for (int i = size - 1; i >= index; i--) {
            elements[i + 1] = elements[i];
        }
        elements[index] = element;
        size++;
    }

    public void addLast(E element) {
        add(size, element);

    }

    public void addFirst(E element) {
        add(0, element);

    }

    // 获取index索引位置的元素
    public E get(int index) {
        if (index < 0 || index >= size) throw new IllegalArgumentException("Get failed. Require index >= 0 and index <= size.");
        return elements[index];

    }

    public E set(int index, E element) {
        if (index < 0 || index >= size) throw new IllegalArgumentException("Get failed. Require index >= 0 and index <= size.");
        E old = elements[index];
        elements[index] = element;
        return old;
    }

    // 查找数组中是否有元素e
    public boolean contains(E element) {
        for (int i = 0; i < size; i++) {
            return elements[i].equals(element);
        }
        return false;
    }

    // 查找数组中元素e所在的索引，如果不存在元素e，则返回-1
    public int find(E element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) return i;
        }
        return ELEMENT_NOT_FOUND;
    }

    // 从数组中删除index位置的元素, 返回删除的元素
    public E remove(int index) {
        if (index < 0 || index >= size) throw new IllegalArgumentException("Remove failed. Index is illegal.");
        E old = elements[index];
        for (int i = index; i <= size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
        if (size == elements.length / 4 && elements.length != 0)
            resize(elements.length / 2);

        return old;
    }

    public void removeFirst() {
        remove(0);

    }

    public void removeLast() {
        remove(size -1);
    }

    // 从数组中删除元素e
    public void removeElement(E e) {
        int index = find(e);
        if (index != ELEMENT_NOT_FOUND) remove(index);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Array: size = %d , capacity = %d \n", size, elements.length));
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i != size -1) sb.append(",");
        }
        sb.append("]");

        return  sb.toString();
    }

    // 将数组空间的容量变成newCapacity大小
    private void resize(int newCapacity) {
        E[] newEles = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newEles[i] = elements[i];
        }
        elements = newEles;
    }
}
