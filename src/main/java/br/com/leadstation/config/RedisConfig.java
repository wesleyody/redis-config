package br.com.leadstation.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Configuration
@ComponentScan
public class RedisConfig {

    @Resource
    private Environment environment;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory () {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(
            environment.getRequiredProperty( "spring.redis.host" )
        );
        return new JedisConnectionFactory( redisStandaloneConfiguration );
    }

}
