package hk.edu.hkmu.jiang.javafinal.domain.inventory.service;

import hk.edu.hkmu.jiang.javafinal.domain.inventory.aggregate.Inventory;
import hk.edu.hkmu.jiang.javafinal.domain.shaired.enums.InventoryType;

import java.util.Optional;

public interface InventoryDomainService {
    Optional<Inventory> initInventory(Long skuId, InventoryType inventoryType);
}
