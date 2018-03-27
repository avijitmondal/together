/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avijit.together.server.data.cache;

/**
 *
 * @author avijit
 */
public class CentralCache extends AbstractCache<String, Object>{

    private static CentralCache INSTANCE = null;

    private CentralCache() {
    }

    public static CentralCache getInstance() {
        synchronized (CentralCache.class) {
            if (INSTANCE == null) {
                INSTANCE = new CentralCache();
            }
        }
        return INSTANCE;
    }
}
