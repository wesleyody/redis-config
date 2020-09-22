package br.com.leadstation.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.session.web.http.CookieHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;

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

    @Bean
    public RedisTemplate< String, Object > redisTemplate () {
        final RedisTemplate< String, Object > template = new RedisTemplate<>();
        template.setConnectionFactory( jedisConnectionFactory() );
        template.setValueSerializer( new GenericToStringSerializer<>( Object.class ) );
        return template;
    }

    @Bean
    public HttpSessionIdResolver httpSessionStrategy () {
        return new CookieHttpSessionIdResolver();
    }

}
