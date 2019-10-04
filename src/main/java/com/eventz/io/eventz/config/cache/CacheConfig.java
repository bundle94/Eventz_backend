package com.eventz.io.eventz.config.cache;

import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
;

/**
 * Created by Michael.Akobundu on 9/30/2019.
 */
@Configuration
public class CacheConfig {

    Config config = new Config();
    private RMap<String, Object> cache;
    private Redisson redisson;

    @Bean
    public void createCache() {
        Config config = singleServerConfig();

        RedissonClient client = Redisson.create(config);
        cache = client.getMap("EventzCache");
    }

    public Config singleServerConfig() {
        config.useSingleServer()
                .setPassword("bHy89onLN8IBU4vgaI0CTYGneehqlo3Dr5SicHHofWRTlLgF7BBz0ylJLmDDufArFLoAPoWTCuNcj5Fv")
                .setAddress("127.0.0.1:6379")
                .setDatabase(8)
                .setConnectionMinimumIdleSize(10)
                .setTimeout(5000)
                .setReconnectionTimeout(5000)
                .setConnectionMinimumIdleSize(10)
                .setConnectionPoolSize(20);
        return config;
    }

}
