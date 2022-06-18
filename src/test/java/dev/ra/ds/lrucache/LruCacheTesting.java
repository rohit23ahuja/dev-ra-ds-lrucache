package dev.ra.ds.lrucache;

import org.junit.Assert;
import org.junit.Test;

public class LruCacheTesting {

	@Test
	public void addedItemsInCache_CheckSize() {
		LruCache<Integer> lruCache = new LruCache<Integer>(3);
		lruCache.add(23);
		lruCache.add(46);
		Assert.assertEquals("23-->46", lruCache.peekItems());
	}
	
	@Test
	public void addedMoreItemsThanCapacity_ThenCheckLruEviction() {
		LruCache<Integer> lruCache = new LruCache<Integer>(3);
		lruCache.add(23);
		lruCache.add(46);
		lruCache.add(69);
		lruCache.add(92);
		Assert.assertEquals("46-->69-->92", lruCache.peekItems());
	}
	
	@Test
	public void fetchingItemsFromCache_ThenCheckLruRearrange() {
		LruCache<Integer> lruCache = new LruCache<Integer>(3);
		lruCache.add(46);
		lruCache.add(69);
		lruCache.add(92);
		Assert.assertEquals("46-->69-->92", lruCache.peekItems());
		lruCache.get(69);
		Assert.assertEquals("46-->92-->69", lruCache.peekItems());
	}
}
