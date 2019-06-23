/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avijit.together.core.data.cache;

/**
 *
 * @author avijit
 */
public class CentralCache extends AbstractCache<String, Object>{

    private static CentralCache instance = null;

    private CentralCache() {
    }

    public static CentralCache getInstance() {
        synchronized (CentralCache.class) {
            if (instance == null) {
                instance = new CentralCache();
            }
        }
        return instance;
    }
}
