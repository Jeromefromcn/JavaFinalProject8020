package hk.edu.hkmu.jiang.javafinal.Infrastructure.proxy.impl;

import hk.edu.hkmu.jiang.javafinal.application.dto.ReplenishmentDTO;
import hk.edu.hkmu.jiang.javafinal.application.proxy.SupplierProxy;
import hk.edu.hkmu.jiang.javafinal.common.util.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SupplierProxyImpl implements SupplierProxy {

    @Override
    public void orderGoods(ReplenishmentDTO replenishmentDTO) {
        log.info("ordered goods from supplier for replenishment: {}",
                GsonUtil.getGson().toJson(replenishmentDTO));
    }
}
