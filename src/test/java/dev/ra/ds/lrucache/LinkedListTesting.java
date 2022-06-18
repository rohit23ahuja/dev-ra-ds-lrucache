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
		Assert.assertEquals(Integer.valueOf(7), linkedList.getPointer().getData());
		Assert.assertEquals(Integer.valueOf(3), linkedList.getHead().getData());
	}
	
	public void givenLinkedListOfStrings_checkSizePointerHead() {
		LinkedList<String> linkedList = new LinkedList<String>();
		
		linkedList.add("deepika");
		linkedList.add("rohit");
		linkedList.add("anhad");
		linkedList.add("mummy");
		
		Assert.assertEquals(Integer.valueOf(4), linkedList.getSize());
		Assert.assertEquals("deepika", linkedList.getHead().getData());
		Assert.assertEquals("mummy", linkedList.getPointer().getData());
	}
	
	public void performedMultipleOperationsOnList_checkSizeAndItems() {
		LinkedList<Integer> linkedList = new LinkedList<Integer>();
		linkedList.add(3);
		linkedList.remove(3);
		Assert.assertEquals(Integer.valueOf(0), linkedList.getSize());
		linkedList.add(3);
		linkedList.add(5);
		linkedList.add(7);
		Assert.assertNull(linkedList.get(9));
		linkedList.remove(5);
	    Assert.assertEquals(Integer.valueOf(7), linkedList.getPointer().getData());
	    Assert.assertEquals(Integer.valueOf(3), linkedList.getPointer().getPrevious().getData());
	}
}
