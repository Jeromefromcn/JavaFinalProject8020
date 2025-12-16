package hk.edu.hkmu.jiang.javafinal.domain.shaired.service;

import hk.edu.hkmu.jiang.javafinal.domain.inventory.aggregate.Inventory;
import hk.edu.hkmu.jiang.javafinal.domain.sku.aggregate.Sku;

public interface InspectionDomainService {
    int calcReplenishment(Sku sku, Inventory inventory);
}
