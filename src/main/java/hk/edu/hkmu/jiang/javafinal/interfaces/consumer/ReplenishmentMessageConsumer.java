package hk.edu.hkmu.jiang.javafinal.interfaces.consumer;

import hk.edu.hkmu.jiang.javafinal.application.dto.ReplenishmentDTO;
import hk.edu.hkmu.jiang.javafinal.application.service.InspectionApplicationService;
import hk.edu.hkmu.jiang.javafinal.common.util.GsonUtil;
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
public class ReplenishmentMessageConsumer extends AbstractConsumer implements MessageListener {

    @Value("${app.properties.topic.replenishment}")
    private String topic;

    @NonNull
    private InspectionApplicationService inspectionApplicationService;

    @Override
    public void receiveMessage(String message) {
        log.info("receive topic: [{}] message : {}", getTopic(), message);
        ReplenishmentDTO replenishmentDTO = GsonUtil.getGson().fromJson(message, ReplenishmentDTO.class);
        inspectionApplicationService.notifyReplenishment(replenishmentDTO);
    }
}