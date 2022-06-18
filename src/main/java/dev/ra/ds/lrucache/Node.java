package dev.ra.ds.lrucache;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Node<T> {
	private Node<T> previous;
	private Node<T> next;
    private T data;
	
	
	public Node(T data) {
		this.data = data;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this  != obj) return false;
		if (!(obj instanceof Node)) return false;
        Node<T> node = (Node<T>) obj;
		if ((this.getData() == null && node.getData() == null) || (this.getData().equals(node.getData()))) return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		return data!=null?data.hashCode()+31*7:31*7;
	}
}