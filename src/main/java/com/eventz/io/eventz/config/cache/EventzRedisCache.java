package com.eventz.io.eventz.config.cache;

import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * Created by Michael.Akobundu on 9/30/2019.
 */
@Service
public class EventzRedisCache implements MemoryCache {


        private RMap<String, Object> cache;
        private RedissonClient client;
        private final Logger logger = LoggerFactory.getLogger(this.getClass());

        public EventzRedisCache(){
            CacheConfig cacheConfig = new CacheConfig();
            Config config = cacheConfig.singleServerConfig();

            client = Redisson.create(config);
            cache = client.getMap("EventzCache");
        }


        @Override
        public void insertToCache(String key, Object item) {
            try {
                this.cache.fastPutAsync(key, item);
            } catch (Exception ex) {
                logger.error("insertToCache has exception", ex);
            }
        }

        @Override
        public void removeFromCache(String key) {
            try {
                cache.removeAsync(key);
            } catch (Exception ex) {
                logger.error("removeFromCache has exception", ex);
            }
        }

        @Override
        public Object getFromCache(String key) {
            Object returnObject = null;
            try {
                returnObject = cache.get(key);
            } catch (Exception ex) {
                logger.error("getFromCache has exception", ex);
            }
            return returnObject;
        }

        @Override
        public boolean ping() {
            boolean pong = false;
            pong = !(client.isShutdown());
            return pong;

        }


}
