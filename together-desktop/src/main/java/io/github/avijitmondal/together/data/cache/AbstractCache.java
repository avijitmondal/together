/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.avijitmondal.together.data.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author avijit
 * @param <K>
 * @param <V>
 */
public abstract class AbstractCache<K, V> {

    private final Map<K, V> cacheMap;
    
    protected AbstractCache(){
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
    
    public boolean containsKey(K key){
        return cacheMap.containsKey(key);
    }
}
