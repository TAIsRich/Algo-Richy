package com.chuwa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @author b1go
 * @date 10/20/22 11:01 PM
 */
public class MyArrayList<E> implements Iterable<E> {

    /**
     * 真正存数据的底层数组
     */
    private E[] data;

    /**
     * 记录当前元素个数
     */
    private int size;

    /**
     * 默认初始容量
     */
    private static final int INIT_CAP = 1;

    public MyArrayList() {
        this(INIT_CAP);
    }

    public MyArrayList(int initCapacity) {
        data = (E[]) new Object[initCapacity];
        size = 0;
    }

    /**
     * 增: 尾部
     */
    public void add(E e) {
        int cap = data.length;
        // 看data 数组容量够不够
        if (size == cap) {
            resize(2 * cap);
        }

        // 在尾部插入元素
        data[size] = e;
        size++;
    }

    /**
     * 增: index
     */
    public void add(int index, E e) {
        // 检查索引越界
        checkPositionIndex(index);

        int cap = data.length;
        // 看data 数组容量够不够
        if (size == cap) {
            resize(2 * cap);
        }

        // 搬移数据 data[index..] -> data[index+1..]
        System.arraycopy(data, index,
                data, index + 1,
                size - index);

        // 在尾部插入元素
        data[index] = e;
        size++;
    }

    /**
     * Remove: index
     * @param index
     * @return
     */
    public E remove(int index) {
        // 检查索引越界
        checkElementIndex(index);

        int cap = data.length;
        // 可以缩容，节约空间
        if (size == cap / 4) {
            resize(cap / 2);
        }

        E deletedVal = data[index];
        // 搬移数据 data[index+1..] -> data[index..]
        System.arraycopy(data, index + 1,
                data, index,
                size - index - 1);

        data[size - 1] = null;
        size--;

        return deletedVal;
    }

    /**
     * Remove: Element
     * @param e
     * @return
     */
    public boolean remove(E e) {

        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
                remove(i);
                return true;
            }
        }

        return false;
    }

    /**
     * 查
     * @param index
     * @return
     */
    public E get(int index) {
        // 检查索引越界
        checkElementIndex(index);

        return data[index];
    }

    /**
     * 改
     * @param index
     * @param element
     * @return
     */
    public E set(int index, E element) {
        // 检查索引越界
        checkElementIndex(index);
        // 修改数据
        E oldVal = data[index];
        data[index] = element;
        return oldVal;
    }

    /**
     * 工具方法
     * @return
     */
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 将 data 的容量改为 newCap
     * @param newCap
     */
    private void resize(int newCap) {
        if (size > newCap) {
            return;
        }
        E[] temp = (E[]) new Object[newCap];

        for (int i = 0; i < size; i++) {
            temp[i] = data[i];
        }

        data = temp;
    }

    /**
     * 检查 index 索引位置是否可以添加元素
     */
    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    /**
     * 检查 index 索引位置是否可以存在元素
     */
    private void checkElementIndex(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }


    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int p = 0;

            public boolean hasNext() {
                return p != size;
            }

            public E next() {
                return data[p++];
            }
        };
    }
}
