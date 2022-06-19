package dev.ra.ds.lrucache;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinkedList<T> {

    private Integer size;
    private Node<T> head;
    private Node<T> pointer;

    public LinkedList() {
        size = 0;
    }

    public Node<T> add(T value) {
        Node<T> node = new Node<T>(value);
        if (head == null) {
            head = node;
        } else {
            node.setPrevious(pointer);
            pointer.setNext(node);
        }
        pointer = node;
        size++;
        return node;
    }

    public void remove(T value) {
        Node<T> node = get(value);
        node.getPrevious()
                .setNext(node.getNext());
        node.getNext()
                .setPrevious(node.getPrevious());
        node = null;
        size--;
    }

    public Node<T> get(T value) {
        if (head == null)
            return null;
        Node<T> node = head;
        while (node.getNext() != null) {
            if (node.getData() == value) {
                return node;
            }
            node = node.getNext();
        }
        return null;
    }

}
