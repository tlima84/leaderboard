package com.tlima84.leaderboards.config

import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate

@Configuration
@EnableCaching
class RedisConfig {

    @Bean
    fun cacheManager(connectionFactory: RedisConnectionFactory): RedisCacheManager? {
        //creates bean for redis cacheManager
        return RedisCacheManager.create(connectionFactory)
    }

    @Bean
    fun redisTemplate(connectionFactory: RedisConnectionFactory): RedisTemplate<String, Any> {
        //instantiate a redis template to add a cache model
        val redisTemplate = RedisTemplate<String, Any>()
        //sets connection factory into redisTemplate
        redisTemplate.setConnectionFactory(connectionFactory)
        return redisTemplate
    }
}