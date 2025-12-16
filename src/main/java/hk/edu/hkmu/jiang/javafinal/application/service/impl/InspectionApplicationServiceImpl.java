package hk.edu.hkmu.jiang.javafinal.application.service.impl;

import hk.edu.hkmu.jiang.javafinal.application.dto.ReplenishmentDTO;
import hk.edu.hkmu.jiang.javafinal.application.proxy.MessageSendingProxy;
import hk.edu.hkmu.jiang.javafinal.application.proxy.SmsSendingProxy;
import hk.edu.hkmu.jiang.javafinal.application.proxy.SupplierProxy;
import hk.edu.hkmu.jiang.javafinal.application.service.InspectionApplicationService;
import hk.edu.hkmu.jiang.javafinal.common.util.GsonUtil;
import hk.edu.hkmu.jiang.javafinal.domain.inventory.aggregate.Inventory;
import hk.edu.hkmu.jiang.javafinal.domain.inventory.repository.InventoryRepository;
import hk.edu.hkmu.jiang.javafinal.domain.shaired.aggregate.Basic;
import hk.edu.hkmu.jiang.javafinal.domain.shaired.enums.InventoryType;
import hk.edu.hkmu.jiang.javafinal.domain.shaired.service.InspectionDomainService;
import hk.edu.hkmu.jiang.javafinal.domain.sku.aggregate.Sku;
import hk.edu.hkmu.jiang.javafinal.domain.sku.repository.SkuRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InspectionApplicationServiceImpl implements InspectionApplicationService {

    @NonNull
    private SkuRepository skuRepository;
    @NonNull
    private InventoryRepository inventoryRepository;
    @NonNull
    private InspectionDomainService inspectionDomainService;
    @NonNull
    private MessageSendingProxy messageSendingProxy;
    @Value("${app.properties.topic.replenishment}")
    private String topicReplenishment;
    @NonNull
    private SmsSendingProxy smsSendingProxy;
    @NonNull
    private SupplierProxy supplierProxy;

    @Override
    @Transactional
    public void inspect(InventoryType type) {
        List<Sku> skus = skuRepository.queryAll();
        List<Long> skuIds = skus.stream()
                .map(Basic::getId)
                .toList();
        Map<Long, Inventory> skuIdInventoryMap = inventoryRepository.queryBySkuIdsAndType(skuIds, type)
                .stream()
                .collect(Collectors.toMap(Inventory::getSkuId, inventory -> inventory));

        skus.forEach(sku -> {
            Long skuId = sku.getId();
            Inventory inventory = skuIdInventoryMap.get(skuId);
            int replenishmentQuantity = inspectionDomainService.calcReplenishment(sku, inventory);
            if (replenishmentQuantity > 0) {
                messageSendingProxy.sendMessage(topicReplenishment,
                        GsonUtil.getGson().toJson(ReplenishmentDTO.builder()
                                .skuId(sku.getId())
                                .skuCode(sku.getCode())
                                .skuName(sku.getName())
                                .currentQuantity(inventory.getQuantity())
                                .needQuantity(replenishmentQuantity)
                                .type(type)
                                .build()));
            }
        });

    }

    @Override
    public void notifyReplenishment(ReplenishmentDTO replenishmentDTO) {
        if (replenishmentDTO == null) {
            return;
        }
        if (InventoryType.SHELF.equals(replenishmentDTO.getType())) {
            smsSendingProxy.sendSms(replenishmentDTO);
        } else {
            supplierProxy.orderGoods(replenishmentDTO);
        }
    }
}
