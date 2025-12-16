package hk.edu.hkmu.jiang.javafinal.application.proxy;

import hk.edu.hkmu.jiang.javafinal.application.dto.ReplenishmentDTO;

public interface SmsSendingProxy {

    void sendSms(ReplenishmentDTO replenishmentDTO);
}
