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
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
                                .previousQuantity(0)
                                .newQuantity(0)
                                .build()
                ))
                .build();
        initialInventory.initQuantity();
        return Optional.of(initialInventory);
    }

    @Override
    public Map<Inventory, Integer> deductShelfInventory(Map<Long, Integer> skuIdDeductionMap) {
        List<Long> skuIds = skuIdDeductionMap.keySet().stream().toList();
        List<Inventory> shelfInventories = inventoryRepository
                .queryBySkuIdsAndType(skuIds, InventoryType.SHELF);
        return getInventoryShortageMap(skuIdDeductionMap, shelfInventories);
    }

    private int calcShortage(Inventory inventory, Integer deduction) {
        InventoryChangeRecord record = inventory.getChangeRecords().get(0);
        Integer deducted = record.getPreviousQuantity() - record.getNewQuantity();
        if (deducted < deduction) {
            return deduction - deducted;
        } else {
            return 0;
        }
    }

    private Inventory deductInventory(Inventory inventory, Integer deduction) {
        boolean sufficient = inventory.isSufficient(deduction);
        Integer previousQuantity = inventory.getQuantity();
        if (sufficient) {
            inventory.setQuantity(inventory.getQuantity() - deduction);
        } else {
            inventory.setQuantity(0);
        }
        inventory.setChangeRecords(List.of(InventoryChangeRecord.builder()
                .previousQuantity(previousQuantity)
                .newQuantity(inventory.getQuantity())
                .inventoryId(inventory.getId())
                .operationType(OperationType.OUTBOUND)
                .build()));
        return inventory;
    }

    @Override
    public Map<Inventory, Integer> deductWarehouseInventory(Map<Long, Integer> skuIdDeductionMap) {
        List<Long> skuIds = skuIdDeductionMap.keySet().stream().toList();
        List<Inventory> warehouseInventories = inventoryRepository
                .queryBySkuIdsAndType(skuIds, InventoryType.WAREHOUSE);
        return getInventoryShortageMap(skuIdDeductionMap, warehouseInventories);
    }

    @NotNull
    private Map<Inventory, Integer> getInventoryShortageMap(Map<Long, Integer> skuIdDeductionMap,
                                                            List<Inventory> inventories) {
        return inventories.stream()
                .collect(Collectors.toMap(inventory -> {
                            Integer deduction = skuIdDeductionMap.get(inventory.getSkuId());
                            return deductInventory(inventory, deduction);
                        },
                        inventory -> {
                            Integer deduction = skuIdDeductionMap.get(inventory.getSkuId());
                            return calcShortage(inventory, deduction);
                        }));
    }


    @Override
    public Map<Inventory, Integer> filterInsufficientInventories(
            Map<Inventory, Integer> inventoryAndShortageMap) {
        return inventoryAndShortageMap.entrySet().stream()
                .filter(entry ->
                        Objects.nonNull(entry.getValue()) && entry.getValue() > 0)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public boolean calcSufficiency(Map<Inventory, Integer> inventoryShortageMap) {
        return CollectionUtils.isEmpty(inventoryShortageMap);
    }
}
