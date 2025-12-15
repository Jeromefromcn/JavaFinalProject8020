package hk.edu.hkmu.jiang.javafinal.Infrastructure.config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.embedded.RedisServer;

import java.io.IOException;

@Configuration
public class EmbeddedRedisConfig {
    @Bean
    public RedisServer redisServer(@Value("${spring.embedded-redis.port}") int redisPort) throws IOException {
        RedisServer redisServer = new RedisServer(redisPort);
        redisServer.start();
        return redisServer;
    }
}