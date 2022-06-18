package dev.ra.ds.lrucache;

import org.junit.Assert;
import org.junit.Test;

public class LinkedListTesting {

	@Test
	public void givenLinkedList_checkSizePointerHead() {
		LinkedList<Integer> linkedList = new LinkedList<Integer>();

		linkedList.add(3);
		linkedList.add(5);
		linkedList.add(7);
		
		Assert.assertEquals(Integer.valueOf(3), linkedList.getSize());
		Assert.assertEquals(Integer.valueOf(7), linkedList.getPointer().getValue());
		Assert.assertEquals(Integer.valueOf(3), linkedList.getHead().getValue());
	}
	
	public void givenLinkedListOfStrings_checkSizePointerHead() {
		LinkedList<String> linkedList = new LinkedList<String>();
		
		linkedList.add("deepika");
		linkedList.add("rohit");
		linkedList.add("anhad");
		linkedList.add("mummy");
		
		Assert.assertEquals(Integer.valueOf(4), linkedList.getSize());
		Assert.assertEquals("deepika", linkedList.getHead().getValue());
		Assert.assertEquals("mummy", linkedList.getPointer().getValue());
	}
}
