package com.chuwa;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class MyHashMap<K, V> {
    private int size;
    private LinkedList<Node>[] myMap;
    private final static int DEFAULT_SIZE = 8;
    private final static double LOAD_FACTOR = 2;
    private final static double THRESHOLD = 0.75;
    private boolean isResizeable;
    private final static int MAX_CAPACITY = 128;


    class Node<K, V>{
        K key;
        V val;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    /**
     * 1. capacity
     * 2. resize(No capacity para)
     * 3. load factor 0.75 , size >= 0.75 * default -> double capacity
     * 4. 0.75 threshold
     * 5. capacity can not add node if the size is
     * 6. 128 max capacity
     */
    public MyHashMap() {
        size = 0;
        myMap = new LinkedList[DEFAULT_SIZE];
        isResizeable = true;
    }
    public MyHashMap(int capacity) {
        size = 0;
        myMap = new LinkedList[capacity];
        isResizeable = false;
    }

    /**
     *  CRUD
     *  1. Create
     *  2. Read
     *  3. Update
     *  4. Delete(by key)
     *  5. ContainsKey()
     *  6. size()
     *  7. entrySet()
     *
     */

    /**
     * get method
     *  retrieve value by key
     */
    public V get(K key) {
        // find bucket
        int bucket_idx = getHashCode(key);

        // find the chain
        LinkedList<Node> chain = findChain(bucket_idx);

        if (chain == null) {
            return null;
        }

        // find value in the chain
        Node value = findNode(key, chain);

        return value == null ? null : (V) value.val;
    }

    /**
     * put method
     *  put key-value pair into map
     */
    public boolean put(K key, V val) {
        int bucket_idx = getHashCode(key);
        LinkedList<Node> chain = findChain(bucket_idx);

        // 如果还没有chain，则创建chain。（意味着该bucket还未被使用过）
        if (chain == null) {
            myMap[bucket_idx] = new LinkedList<>();
            myMap[bucket_idx].add(new Node(key, val));
            size++;
            return true;
        }

        Node node = findNode(key, chain);
        // 如果这是个新元素，不在chain里
        if (node == null) {
            if (!resize()) {
                return false;
            }
            // 在chain里添加新元素
            chain.add(new Node(key, val));
            size++;
        } else {
            // 如果不是新元素，则更新value即可
            node.val = val;
        }

        return true;
    }

    public boolean delete(K key) {
        int bucket_idx = getHashCode(key);

        LinkedList<Node> chain = findChain(bucket_idx);

        if (chain == null) {
            return true;
        }

        for (int i = 0; i < chain.size(); i++) {
            if (chain.get(i).key.equals(key)) {
                chain.remove(i);
                size--;
                return true;
            }
        }

        return false;
    }

    public boolean containsKey(K key) {
        int bucket_idx = getHashCode(key);
        LinkedList<Node> chain = myMap[bucket_idx];

        // 没有chain，则一定無该元素
        if (chain == null) {
            return false;
        }

        // 有chain，则看chain里是否有该元素key
        Node node = findNode(key, chain);
        return node == null ? false : true;
    }

    public int size() {
        return size;
    }

    public Set<Node> entrySet() {
        Set<Node> entrySet = new HashSet<>();
        // loop all buckets
        for (int i = 0; i < myMap.length; i++) {
            LinkedList<Node> chain = myMap[i];
            if (chain != null) {
                // loop all elements in the chain
                for (Node node : chain) {
                    entrySet.add(node);
                }
            }
        }

        return entrySet;
    }

    /**
     * 读取input key对应的hash code
     * @param key
     * @return
     */
    private int getHashCode(K key) {
        // 取余数
        if (isResizeable) {
            return (Integer) key % DEFAULT_SIZE;
        }

        return (Integer) key % myMap.length;
    }

    private LinkedList<Node> findChain(int idx) {
        return myMap[idx];
    }

    private Node findNode(K key, LinkedList<Node> target) {
        for (Node node : target) {
            if (node.key.equals(key)) {
                return node;
            }
        }
        return null;
    }

    private boolean resize() {
        if (isResizeable) {
            if (1.0 * size >= THRESHOLD * myMap.length) {
                increaseCapacity((int) (myMap.length * LOAD_FACTOR));
            }
            return true;
        }
        return size >= myMap.length ? false : true;
    }

    public void increaseCapacity(int length) {
        if (length >= MAX_CAPACITY) {
            return;
        }

        final LinkedList<Node>[] oldMap = myMap;
        myMap = new LinkedList[length];
        size = 0;
        for (LinkedList<Node> list : oldMap) {
            if (list != null) {
                for (Node node : list) {
                    if (node != null) {
                        put((K) node.key, (V)node.val);
                    }
                }
            }
        }
    }
}
