package com.chuwa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author b1go
 * @date 10/20/22 11:42 PM
 */
class MyArrayListTest {

    MyArrayList<Integer> integers;
    MyArrayList<String> strings;

    @BeforeEach
    public void initData() {
        integers = new MyArrayList<>();
        integers.add(0);
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);

        strings = new MyArrayList<>();
        strings.add("Richard");
        strings.add("Richy");
    }

    @Test
    void add() {
        System.out.println(integers);

        System.out.println(strings);
    }

    @Test
    void testAdd() {
        MyArrayList<Integer> integers = new MyArrayList<Integer>();
        integers.add(2);
        integers.add(4);
        integers.add(5);
        integers.add(0, 1);
        integers.add(2, 3);
        System.out.println(integers);
    }

    @Test
    void testAdd_Exception() {
        MyArrayList<Integer> integers = new MyArrayList<Integer>();
        integers.add(2);
        integers.add(4);
        integers.add(5);
        integers.add(0, 1);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> integers.add(10, 3));
        System.out.println(integers);
    }

    @Test
    void remove() {
        System.out.println(integers.remove(1));
        System.out.println(integers);
    }

    @Test
    void remove_exception() {
        System.out.println(integers.remove(1));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> integers.remove(100));
        System.out.println(integers);
    }

    @Test
    void testRemove() {
        System.out.println(integers.remove(Integer.valueOf(1)));
        System.out.println(integers.remove(Integer.valueOf(100)));
        System.out.println(integers);
    }

    @Test
    void get() {
        System.out.println(integers.get(1));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> integers.get(100));
    }

    @Test
    void set() {
        integers.set(2, 200);
        System.out.println(integers);
        Assertions.assertEquals(200, integers.get(2));

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> integers.set(100, 100));
    }

    @Test
    void size() {
        System.out.println(integers.size());
    }

    @Test
    void isEmpty() {
        System.out.println(integers.isEmpty());
    }

    @Test
    void iterator() {
        Iterator<Integer> iterator = integers.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}