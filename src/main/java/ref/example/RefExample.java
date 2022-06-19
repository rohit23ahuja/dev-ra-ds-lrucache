package ref.example;

import java.util.HashMap;
import java.util.Map;

public class RefExample<T> {
    // Own version of DLL
    private class Node {
        int key;
        T value;
        Node next;
        Node prev;
    }

    private Map<Integer, Node> map = new HashMap<>();
    private Node head, tail;
    private int totalItemsInCache;
    private int maxCapacity;

    public RefExample(int maxCapacity) {
        totalItemsInCache = 0;
        this.maxCapacity = maxCapacity;

        head = new Node();
        head.prev = null;

        tail = new Node();
        tail.next = null;

        // wire the head and tail together
        head.next = tail;
        tail.prev = head;
    }

    /**
     * If key not present in hash map, add node to hash map and to front of list. if key present then update value and move to front of list
     */
    public void put(int key, T value) {
        Node node = map.get(key);
        if (node == null) {
            Node newNode = new Node();
            newNode.key = key;
            newNode.value = value;

            map.put(key, newNode);
            addNode(newNode);
            totalItemsInCache++;
            if (totalItemsInCache > maxCapacity) {
                removeLRUEntryFromStructure();
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addNode(node);
    }

    private void removeLRUEntryFromStructure() {
        Node tail = popTail();
        map.remove(tail.key);
        --totalItemsInCache;
    }

    private Node popTail() {
        Node itemBeingRemoved = tail.prev;
        removeNode(itemBeingRemoved);
        return itemBeingRemoved;
    }

    private void removeNode(Node node) {
        Node savedPrev = node.prev;
        Node savedNext = node.next;

        savedPrev.next = savedNext;
        savedNext.prev = savedPrev;
    }

    // new node previous should be head
    // new node next should head next
    // head current next prev should new node
    // head new next should be new node
    private void addNode(Node newNode) {
        newNode.prev = head;
        newNode.next = head.next;
        head.next.prev = newNode;
        head.next = newNode;

    }

    public T get(int key) {
        Node node = map.get(key);
        if (node == null) {
            String s = "-1";
            return (T) s;
        }
        moveToHead(node);
        return node.value;
    }

    @Override
    public String toString() {
        Node current = head;
        StringBuilder result = new StringBuilder();
        while (current != null) {
            result.append(current.value + "-->");
            current = current.next;
        }
        return result.toString();
    }
}
