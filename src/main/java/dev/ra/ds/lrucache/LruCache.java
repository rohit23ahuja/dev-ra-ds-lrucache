package dev.ra.ds.lrucache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import lombok.Getter;

public class LruCache<T> {

    private LinkedList<T> list;
    private Map<Integer, Node<T>> map;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    @Getter
    private Integer currentSize;

    @Getter
    private Integer capacity;

    public LruCache(Integer size) {
        this.list = new LinkedList<T>();
        this.map = new ConcurrentHashMap<Integer, Node<T>>();
        this.capacity = size;
        this.currentSize = 0;
    }

    /**
     * @param value
     */
    public void add(T value) {
        this.lock.writeLock()
                .lock();
        try {
            Node<T> node = map.get(value.hashCode());
            if (node == null) {
                if (currentSize == capacity) {
                    Node<T> currentHead = list.getHead();
                    map.remove(currentHead.getData()
                            .hashCode());
                    Node<T> newHead = currentHead.getNext();
                    newHead.setPrevious(null);
                    list.setHead(newHead);
                    --currentSize;
                }
                map.put(value.hashCode(), list.add(value));
                currentSize++;
            }
        } finally {
            this.lock.writeLock()
                    .unlock();
        }
    }

    public T get(T value) {
        this.lock.readLock()
                .lock();
        try {
            Node<T> node = map.get(value.hashCode());
            if (node != null) {
                removeNode(node);
                moveToTail(node);
                return node.getData();
            } else {
                throw new IllegalArgumentException(String.format("[%d] does not exists in cache", value));
            }
        } finally {
            this.lock.readLock()
                    .unlock();
        }
    }

    private void moveToTail(Node<T> node) {
        node.setNext(null);
        list.getPointer()
                .setNext(node);
        node.setPrevious(list.getPointer());
        list.setPointer(node);
    }

    private void removeNode(Node<T> node) {
        node.getPrevious()
                .setNext(node.getNext());
        node.getNext()
                .setPrevious(node.getPrevious());
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node<T> node = list.getHead();
        while (true) {
            result.append(node.getData()
                    .toString());
            node = node.getNext();
            if (node != null) {
                result.append("-->");
            } else {
                break;
            }
        }
        return result.toString();
    }

}
