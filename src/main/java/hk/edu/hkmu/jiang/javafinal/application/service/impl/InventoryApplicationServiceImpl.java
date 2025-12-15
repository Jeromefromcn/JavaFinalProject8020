package hk.edu.hkmu.jiang.javafinal.application.service.impl;

import hk.edu.hkmu.jiang.javafinal.application.service.InventoryApplicationService;
import hk.edu.hkmu.jiang.javafinal.domain.inventory.aggregate.Inventory;
import hk.edu.hkmu.jiang.javafinal.domain.inventory.aggregate.InventoryChangeRecord;
import hk.edu.hkmu.jiang.javafinal.domain.inventory.repository.InventoryRepository;
import hk.edu.hkmu.jiang.javafinal.domain.inventory.service.InventoryDomainService;
import hk.edu.hkmu.jiang.javafinal.domain.shaired.enums.InventoryType;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InventoryApplicationServiceImpl implements InventoryApplicationService {

    @NonNull
    private InventoryRepository inventoryRepository;
    @NonNull
    private InventoryDomainService inventoryDomainService;


    @Override
    public void initInventory(Long skuId) {
        doInitInventory(inventoryDomainService.initInventory(skuId, InventoryType.SHELF));
        doInitInventory(inventoryDomainService.initInventory(skuId, InventoryType.WAREHOUSE));
    }

    private void doInitInventory(Optional<Inventory> inventory) {
        if (inventory.isEmpty()) {
            return;
        }
        Inventory initialInventory = inventory.get();
        Inventory savedInventory = inventoryRepository.create(initialInventory);
        List<InventoryChangeRecord> changeRecords = initialInventory.getChangeRecords();
        changeRecords.forEach(inventoryChangeRecord -> {
            inventoryChangeRecord.setInventoryId(savedInventory.getId());
            inventoryRepository.createRecord(inventoryChangeRecord);
        });
    }
}
