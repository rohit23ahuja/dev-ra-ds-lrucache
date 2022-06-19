package dev.ra.ds.lrucache;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LruCacheTesting {

	@Test
	public void addedItemsInCache_CheckSize() {
		LruCache<Integer> lruCache = new LruCache<Integer>(3);
		lruCache.add(23);
		lruCache.add(46);
		Assert.assertEquals("23-->46", lruCache.toString());
	}

	@Test
	public void addedMoreItemsThanCapacity_ThenCheckLruEviction() {
		LruCache<Integer> lruCache = new LruCache<Integer>(3);
		lruCache.add(23);
		lruCache.add(46);
		lruCache.add(69);
		lruCache.add(92);
		Assert.assertEquals("46-->69-->92", lruCache.toString());
	}

	@Test
	public void fetchingItemsFromCache_ThenCheckLruRearrange() {
		LruCache<Integer> lruCache = new LruCache<Integer>(3);
		lruCache.add(46);
		lruCache.add(69);
		lruCache.add(92);
		Assert.assertEquals("46-->69-->92", lruCache.toString());
		lruCache.get(69);
		Assert.assertEquals("46-->92-->69", lruCache.toString());
	}

	@Test
	public void runMultiThreadTask_WhenPutDataInConcurrentToCache_ThenNoDataLost() throws Exception {
		final int size = 50;
		final LruCache<Integer> lruCache = new LruCache<Integer>(size);


		final ExecutorService executorService = Executors.newFixedThreadPool(5);
		CountDownLatch countDownLatch = new CountDownLatch(size);

		try {
			IntStream.range(0, size).<Runnable>mapToObj(key -> () -> {
				lruCache.add(key);
				countDownLatch.countDown();
			}).forEach(executorService::submit);
			countDownLatch.await();
		} finally {
			executorService.shutdown();
		}
		Assert.assertEquals(lruCache.getCurrentSize(), Integer.valueOf(size));
		log.info("{}", lruCache.toString());
	}
}
