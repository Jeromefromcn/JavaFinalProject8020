package hk.edu.hkmu.jiang.javafinal.application.service;

import java.util.Map;

public interface InventoryApplicationService {

    void initInventory(Long skuId);

    Boolean deductInventory(Map<Long, Integer> skuMap);

    Boolean replenishWarehouse(Map<Long, Integer> skuIdQuantityMap);

    Boolean replenishShelf(Map<Long, Integer> skuIdQuantityMap);
}
