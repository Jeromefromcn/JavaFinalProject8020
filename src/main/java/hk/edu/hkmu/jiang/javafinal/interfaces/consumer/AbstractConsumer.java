package hk.edu.hkmu.jiang.javafinal.interfaces.consumer;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

@Slf4j
public abstract class AbstractConsumer implements MessageListener {

    public void onMessage(Message message, byte @NotNull [] pattern) {
        String body = new String(message.getBody());
        receiveMessage(body);
    }

    protected abstract void receiveMessage(String message);

    public abstract String getTopic();
}
