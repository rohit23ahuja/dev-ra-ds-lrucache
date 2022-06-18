package dev.ra.ds.lrucache;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LruCache<T> {

	private LinkedList<T> list;
	private Map<Integer, Node<T>> map;
	private Integer currentSize;
	private Integer size;

	public LruCache(Integer size) {
		this.list = new LinkedList<T>();
		this.map = new HashMap<Integer, Node<T>>();
		this.size = size;
		this.currentSize = 0;
	}

	public void add(T value) {
		boolean itemExists = true;
		try {
			get(value);
		} catch (RuntimeException e) {
			log.info("value does not exist");
			itemExists = false;
		}

		if (!itemExists) {
			if (currentSize == size) {
				Node<T> currentHead = list.getHead();
				map.remove(currentHead.getData().hashCode());
				Node<T> newHead = currentHead.getNext();
				newHead.setPrevious(null);
				list.setHead(newHead);
				--currentSize;
			}
			Node<T> node = list.add(value);
			map.put(value.hashCode(), node);
			currentSize++;
		}

	}

	public T get(T value) {
		Node<T> node = map.get(value.hashCode());
		if (node != null) {
			node.getPrevious().setNext(node.getNext());
			node.getNext().setPrevious(node.getPrevious());

			node.setNext(null);
			list.getPointer().setNext(node);
			node.setPrevious(list.getPointer());
			list.setPointer(node);
			return node.getData();
		} else {
			throw new IllegalArgumentException(String.format("[%d] does not exists in cache", value));
		}

	}
	
	public String peekItems() {
		String result = "";
		Node<T> node = list.getHead();
		while (node != null) {
			result = result.concat(node.getData().toString());
			if (node.getNext()!=null) {
				result = result.concat("-->");				
			}
			node = node.getNext();
		}
		return result;
	}

}
