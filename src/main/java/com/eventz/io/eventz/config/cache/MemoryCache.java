package com.eventz.io.eventz.config.cache;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by Michael.Akobundu on 9/30/2019.
 */
public interface MemoryCache {

    public void insertToCache(String key, Object item);

    public void removeFromCache(String key);

    public Object getFromCache(String key);

    public boolean ping();
}
