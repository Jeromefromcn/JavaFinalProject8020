package hk.edu.hkmu.jiang.javafinal.domain.inventory.repository;

import hk.edu.hkmu.jiang.javafinal.domain.inventory.aggregate.Inventory;
import hk.edu.hkmu.jiang.javafinal.domain.inventory.aggregate.InventoryChangeRecord;
import hk.edu.hkmu.jiang.javafinal.domain.shaired.enums.InventoryType;

public interface InventoryRepository {

    Inventory queryBySkuIdAndType(Long skuId, InventoryType type);

    Inventory create(Inventory inventory);

    void createRecord(InventoryChangeRecord inventoryChangeRecord);
}
