package hk.edu.hkmu.jiang.javafinal.domain.inventory.service.impl;

import hk.edu.hkmu.jiang.javafinal.domain.inventory.aggregate.Inventory;
import hk.edu.hkmu.jiang.javafinal.domain.inventory.aggregate.InventoryChangeRecord;
import hk.edu.hkmu.jiang.javafinal.domain.inventory.repository.InventoryRepository;
import hk.edu.hkmu.jiang.javafinal.domain.inventory.service.InventoryDomainService;
import hk.edu.hkmu.jiang.javafinal.domain.shaired.enums.InventoryType;
import hk.edu.hkmu.jiang.javafinal.domain.shaired.enums.OperationType;
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
public class InventoryDomainServiceImpl implements InventoryDomainService {

    @NonNull
    private InventoryRepository inventoryRepository;

    @Override
    public Optional<Inventory> initInventory(Long skuId, InventoryType inventoryType) {
        Inventory existingInventory = inventoryRepository.queryBySkuIdAndType(skuId, inventoryType);
        if (existingInventory != null) {
            return Optional.empty();
        }
        Inventory initialInventory = Inventory.builder()
                .skuId(skuId)
                .type(inventoryType)
                .changeRecords(List.of(
                        InventoryChangeRecord.builder()
                                .operationType(OperationType.INITIALIZATION)
                                .previousQuantity(0L)
                                .newQuantity(0L)
                                .build()
                ))
                .build();
        initialInventory.initQuantity();
        return Optional.of(initialInventory);
    }
}
