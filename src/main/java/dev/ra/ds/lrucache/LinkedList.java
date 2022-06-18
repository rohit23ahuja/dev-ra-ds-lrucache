package dev.ra.ds.lrucache;

import lombok.Getter;

@Getter
public class LinkedList<T> {


	@Getter
	class Node<E> {

		private E value;
		private Node<E> prev;
		private Node<E> next;
		
		Node(E value){
			this.value = value;
		}
	}
	
	
	private Node<T> head;
	private Node<T> pointer;
	private Integer size;
	
	
	
	public LinkedList() {
		size = 0;
	}
	
	public void add(T value) {
		Node<T> node = new Node<T>(value);
		if (head==null) {
			head = node;
		} else {
			node.prev = pointer;			
			pointer.next = node;
		}
		pointer = node;
		size++;
	}
}
