package com.ybd.noname.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;


/**
 * @author yeah
 * @date 2020/8/7 22:31
 */
@Configuration
public class MyRedisConfig {
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {

        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<Object>(Object.class);
        //获取一个默认的cacheConfig
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
        /**
         * 不理解的代码
         */
        configuration = configuration.serializeValuesWith(RedisSerializationContext
                .SerializationPair
                .fromSerializer(fastJsonRedisSerializer))
                // 缓存过期时间
                .entryTtl(Duration.ofDays(7));
        return configuration;
    }
}
