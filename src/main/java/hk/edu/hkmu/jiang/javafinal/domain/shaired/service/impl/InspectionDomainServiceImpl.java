package hk.edu.hkmu.jiang.javafinal.domain.shaired.service.impl;

import hk.edu.hkmu.jiang.javafinal.domain.inventory.aggregate.Inventory;
import hk.edu.hkmu.jiang.javafinal.domain.shaired.enums.InventoryType;
import hk.edu.hkmu.jiang.javafinal.domain.shaired.service.InspectionDomainService;
import hk.edu.hkmu.jiang.javafinal.domain.sku.aggregate.Sku;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class InspectionDomainServiceImpl implements InspectionDomainService {
    @Override
    public int calcReplenishment(Sku sku, Inventory inventory) {
        InventoryType type = inventory.getType();
        int minQuantity = InventoryType.SHELF.equals(type) ? sku.getReplenishmentRule().getMinimumOnShelf()
                : sku.getReplenishmentRule().getMinimumInWarehouse();
        int maxQuantity = InventoryType.SHELF.equals(type) ? sku.getReplenishmentRule().getMaximumOnShelf()
                : sku.getReplenishmentRule().getMaximumInWarehouse();
        if (inventory.getQuantity() < minQuantity) {
            return maxQuantity - inventory.getQuantity();
        }
        return 0;
    }
}
