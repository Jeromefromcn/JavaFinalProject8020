package hk.edu.hkmu.jiang.javafinal.Infrastructure.proxy.impl;

import hk.edu.hkmu.jiang.javafinal.application.proxy.MessageSendingProxy;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageSendingProxyImpl implements MessageSendingProxy {

    @NonNull
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void sendMessage(String topic, String message) {
        redisTemplate.convertAndSend(topic, message);
        log.info("Sent Message: {} to topic: [{}]", message, topic);
    }
}
