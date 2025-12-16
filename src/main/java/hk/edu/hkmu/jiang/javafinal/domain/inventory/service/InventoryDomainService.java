package hk.edu.hkmu.jiang.javafinal.domain.inventory.service;

import hk.edu.hkmu.jiang.javafinal.domain.inventory.aggregate.Inventory;
import hk.edu.hkmu.jiang.javafinal.domain.shaired.enums.InventoryType;

import java.util.Map;
import java.util.Optional;

public interface InventoryDomainService {
    Optional<Inventory> initInventory(Long skuId, InventoryType inventoryType);

    Map<Inventory, Integer> deductShelfInventory(Map<Long, Integer> skuIdDeductionMap);

    Map<Inventory, Integer> deductWarehouseInventory(Map<Long, Integer> skuIdDeductionMap);

    Map<Inventory, Integer> filterInsufficientInventories(Map<Inventory, Integer> inventoryAndShortageMap);

    boolean calcSufficiency(Map<Inventory, Integer> inventoryShortageMap);
}
