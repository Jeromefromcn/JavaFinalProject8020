package hk.edu.hkmu.jiang.javafinal.Infrastructure.proxy.impl;

import hk.edu.hkmu.jiang.javafinal.application.dto.ReplenishmentDTO;
import hk.edu.hkmu.jiang.javafinal.application.proxy.SmsSendingProxy;
import hk.edu.hkmu.jiang.javafinal.common.util.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SmsSendingProxyImpl implements SmsSendingProxy {
    @Override
    public void sendSms(ReplenishmentDTO replenishmentDTO) {
        log.info("sent sms message to crews for replenishment: {}",
                GsonUtil.getGson().toJson(replenishmentDTO));
    }
}
