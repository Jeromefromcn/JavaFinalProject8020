package hk.edu.hkmu.jiang.javafinal.interfaces.consumer;

import hk.edu.hkmu.jiang.javafinal.application.service.InventoryApplicationService;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Getter
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SkuCreatedMessageConsumer extends AbstractConsumer implements MessageListener {

    @Value("${app.properties.topic.sku-created}")
    private String topic;

    @NonNull
    private InventoryApplicationService inventoryApplicationService;

    @Override
    public void receiveMessage(String message) {
        log.info("receive topic: [{}] message : {}", getTopic(), message);
    }
}