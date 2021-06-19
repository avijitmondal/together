/*****************************************************************************
 * FILE NAME   : AbstractCache.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Mar 27, 2018
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijitmondal.together.core.data.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author avijit
 *
 */
public abstract class AbstractCache<K, V> {
	private final Map<K, V> cacheMap;

	protected AbstractCache() {
		cacheMap = new ConcurrentHashMap<>();
	}

	public void addOrUpdate(K key, V value) {
		cacheMap.put(key, value);
	}

	public V get(K key) {
		return cacheMap.get(key);
	}

	public String getAsString(K key) {
		return cacheMap.get(key).toString();
	}

	public void remove(K key) {
		cacheMap.remove(key);
	}

	public boolean containsKey(K key) {
		return cacheMap.containsKey(key);
	}
}
