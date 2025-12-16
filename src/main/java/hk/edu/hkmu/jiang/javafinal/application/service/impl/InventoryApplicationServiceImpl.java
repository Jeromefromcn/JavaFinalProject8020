package hk.edu.hkmu.jiang.javafinal.application.service.impl;

import hk.edu.hkmu.jiang.javafinal.application.service.InventoryApplicationService;
import hk.edu.hkmu.jiang.javafinal.domain.exception.DomainException;
import hk.edu.hkmu.jiang.javafinal.domain.exception.ErrorCode;
import hk.edu.hkmu.jiang.javafinal.domain.inventory.aggregate.Inventory;
import hk.edu.hkmu.jiang.javafinal.domain.inventory.repository.InventoryRepository;
import hk.edu.hkmu.jiang.javafinal.domain.inventory.service.InventoryDomainService;
import hk.edu.hkmu.jiang.javafinal.domain.shaired.enums.InventoryType;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InventoryApplicationServiceImpl implements InventoryApplicationService {

    @NonNull
    private InventoryRepository inventoryRepository;
    @NonNull
    private InventoryDomainService inventoryDomainService;


    @Override
    @Transactional
    public void initInventory(Long skuId) {
        Optional<Inventory> shelfInventory =
                inventoryDomainService.initInventory(skuId, InventoryType.SHELF);
        shelfInventory.ifPresent(inventory -> inventoryRepository.create(inventory));

        Optional<Inventory> warehouseInventory =
                inventoryDomainService.initInventory(skuId, InventoryType.WAREHOUSE);
        warehouseInventory.ifPresent(inventory -> inventoryRepository.create(inventory));
    }

    @Override
    @Transactional
    public Boolean deductInventory(Map<Long, Integer> skuIdDeductionMap) {
        if (CollectionUtils.isEmpty(skuIdDeductionMap)) {
            return false;
        }

        // 1. get the inventories with record and shortage from shelf
        Map<Inventory, Integer> deductedShelfInventoryAndShortageMap =
                inventoryDomainService.deductShelfInventory(skuIdDeductionMap);

        // 2. calc the shortage for shelf
        Map<Inventory, Integer> shelfShortageMap = inventoryDomainService
                .filterInsufficientInventories(deductedShelfInventoryAndShortageMap);
        boolean shelfSufficient = inventoryDomainService.calcSufficiency(shelfShortageMap);

        if (shelfSufficient) {
            // update shelf inventories including records
            inventoryRepository.updateInventories(
                    deductedShelfInventoryAndShortageMap.keySet().stream().toList());
            return true;
        }

        // 3. get the inventories with record and shortage from warehouse
        Map<Long, Integer> skuIdWarehouseDeductionMap = shelfShortageMap.entrySet()
                .stream()
                .collect(Collectors.toMap(entry -> entry.getKey().getSkuId(),
                        Map.Entry::getValue));
        Map<Inventory, Integer> deductedWarehouseInventoryAndShortageMap =
                inventoryDomainService.deductWarehouseInventory(skuIdWarehouseDeductionMap);

        // 4. calc the shortage for warehouse
        Map<Inventory, Integer> warehouseShortageMap = inventoryDomainService
                .filterInsufficientInventories(deductedWarehouseInventoryAndShortageMap);
        boolean warehouseSufficient = inventoryDomainService.calcSufficiency(warehouseShortageMap);

        if (!warehouseSufficient) {
            throw new DomainException(ErrorCode.INSUFFICIENT_INVENTORY.getCode(),
                    ErrorCode.INSUFFICIENT_INVENTORY.getMessage());
        }
        // update warehouse inventories including records
        inventoryRepository.updateInventories(
                deductedWarehouseInventoryAndShortageMap.keySet().stream().toList());
        return true;
    }
}
