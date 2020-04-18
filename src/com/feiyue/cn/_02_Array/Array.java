package com.feiyue.cn._02_Array;

public class Array<E> {
    private E[] elements;

    /**
     * 元素数量
     */
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

    public int getCapacity() {
        return elements.length;
    }
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 清除所有元素
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * 在index索引的位置插入一个新元素e
     * @param index
     * @param element
     */
    public void add(int index, E element) {
        /*
         * 最好：O(1)
         * 最坏：O(n)
         * 平均：O(n)
         */
        if (index < 0 || index > size) throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");
        ensureCapacity(size + 1);
        for (int i = size - 1; i >= index; i--) {
            elements[i + 1] = elements[i];
        }
        elements[index] = element;
        size++;
    }

    /**
     * 从数组中删除index位置的元素, 返回删除的元素
     * @param index
     * @return
     */
    public E remove(int index) {
        /*
         * 最好：O(1)
         * 最坏：O(n)
         * 平均：O(n)
         */
        if (index < 0 || index >= size) throw new IllegalArgumentException("Remove failed. Index is illegal.");
        E old = elements[index];
        for (int i = index; i <= size - 1; i++) {
            elements[i] = elements[i + 1];
        }

        elements[--size] = null;
        if (size == elements.length / 4 && elements.length != 0)
            trim();

        return old;
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
        return  index(element) != ELEMENT_NOT_FOUND;
    }

    // 查找数组中元素e所在的索引，如果不存在元素e，则返回-1
    private int index(E element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (elements[i].equals(element)) return i;
            }
        }

        return ELEMENT_NOT_FOUND;
    }

    public void removeFirst() {
        remove(0);

    }

    public void removeLast() {
        remove(size -1);
    }

    // 从数组中删除元素e
    public void removeElement(E element) {
        int index = index(element);
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

    /**
     * 保证要有capacity的容量
     * @param capacity
     */
    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;
        //1.5倍
        int newCapacity = oldCapacity + oldCapacity << 1;
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
           newElements[i] = elements[i];
        }
        //elements 指向新数组
        elements = newElements;
        System.out.println(oldCapacity + "扩容为" + newCapacity);
    }


    private void trim() {

        //比如原来有30
        int oldCapacity = elements.length;
        //变为15
        int newCapacity = oldCapacity >> 1;

        //todo 极其重要
        if (size >= newCapacity && oldCapacity <= DEFAULT_CAPACITY) return;

        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }

        elements = newElements;

        System.out.println(oldCapacity + "缩容为" + newCapacity);
    }
}
