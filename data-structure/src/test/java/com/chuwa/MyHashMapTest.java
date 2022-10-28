package com.chuwa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.powermock.reflect.Whitebox;

import java.util.Set;

class MyTest {

    @Test
    public void testPutAndGet() {
        MyHashMap<Integer, Integer> hmInteger = new MyHashMap<>();
        MyHashMap<Integer, String> hmString = new MyHashMap<>();

        hmInteger.put(1, 1);
        hmInteger.put(7, 7);
        hmInteger.put(321, 321);

        Assertions.assertEquals(1, hmInteger.get(1));
        Assertions.assertEquals(7, hmInteger.get(7));
        Assertions.assertEquals(321, hmInteger.get(321));

        hmString.put(1, "1");
        hmString.put(7, "7");
        hmString.put(321, "321");

        Assertions.assertEquals("1", hmString.get(1));
        Assertions.assertEquals("7", hmString.get(7));
        Assertions.assertEquals("321", hmString.get(321));
    }

    @Test
    void testGetHashCode() throws Exception {
        // init data
        MyHashMap<Integer, Integer> hmInteger = new MyHashMap<>();
        hmInteger.put(1, 1);
        hmInteger.put(9, 9);
        hmInteger.put(321, 321);


        int getHashCode = Whitebox.invokeMethod(hmInteger, "getHashCode", 1);
        int getHashCode2 = Whitebox.invokeMethod(hmInteger, "getHashCode", 9);
        System.out.println(getHashCode);
        System.out.println(getHashCode2);
        Assertions.assertEquals(getHashCode, getHashCode2);
    }

    public static void main(String[] args) {
        MyHashMap map = new MyHashMap();
        map.put(1, 2);
        map.put(2, 4);
        map.put(3, 4);
        map.put(4,6);

        map.put(5,8);
        map.put(6,8);
        map.put(7,8);
        map.put(8,8);
        map.put(9,8);
        map.put(10,8);
        map.put(11,8);
        map.put(12,8);
        map.put(13,8);

        map.put(14,8);
        map.put(15,8);
        map.put(16,8);

//
//        System.out.println(map.put(1, 6));
//        System.out.println(map.put(5, -5));
//        System.out.println(map.put(1, 7));
//
//        System.out.println(map.get(4));
//
//        map.delete(7);
//        System.out.println(map.get(4));

//        System.out.println(map.get(1));
//        System.out.println(map.get(2));
//          System.out.println(map.containsKey(7));
        System.out.println(map.size());

        Set<MyHashMap.Node> set = map.entrySet();
        set.stream().forEach(node -> System.out.println("key: " + node.key + " " +  "val: " + node.val));
    }
}
