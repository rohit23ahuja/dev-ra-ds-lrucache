LRUCache - This Page is WIP
===========================

Cache is a data structure that is used when we need to satisfy that future requests of a particular set of data is served at a faster rate.

It can be on the basis of frequent access or for data which requires heavy compute.
LRU Cache follows cache eviction policy in which oldest accessed item is removed.

Pros of using lru cache - accesses and updates are in O(1) time complexity
Cons - we need to store items in two data structures - linked list and map. Each require O(n) space complexity.
We need linked list for lru cache because we need to maintain insert and update order. also since frequent removals and insertions are required both from front and tail doubly linked list is preffered.
Hash map provides quick access to items of linked list in O(1) constant time.

LRU Cache working 
   * Get api - let's say resource r is request : - 
	 * if r exists it is returned and moved to front. 
	 * if r does not exist then -1 is returned.
   * Put api - let's say resource r is being put : -
     * if r does not exists r is added to front of list and if cache is maxed out lru entry is removed.
     * if r does exists value is updated. r is moved to front of list.
	
Linked hash map can be used as well instead linked list and hash map combination. LHM can order the elements in terms of insertion order as well as access order.
LHM has removeEldestEntry method as well.