package hk.edu.hkmu.jiang.javafinal.Infrastructure.config.mq;

import hk.edu.hkmu.jiang.javafinal.interfaces.consumer.AbstractConsumer;
import lombok.Setter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import java.util.Map;

@Setter
@Configuration
public class ConsumerConfig implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Bean
    public RedisMessageListenerContainer redisContainer(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);

        Map<String, AbstractConsumer> consumerMaps = applicationContext.getBeansOfType(AbstractConsumer.class);
        consumerMaps.values().forEach(consumer ->
                container.addMessageListener(consumer, new ChannelTopic(consumer.getTopic())));
        return container;
    }
}