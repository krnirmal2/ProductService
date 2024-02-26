package com.productservice.productservice.controllers.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RadisConfig {
  /*   @Bean
      JedisConnectionFactory jedisConnectionFactory() {
          return new JedisConnectionFactory();
      }
  */
  @Bean
  public RedisTemplate<String, Object> redisTemplate(
      RedisConnectionFactory redisConnectionFactory) {
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    //        template.setConnectionFactory(jedisConnectionFactory());
    template.setConnectionFactory(redisConnectionFactory);
    return template;
  }
}
