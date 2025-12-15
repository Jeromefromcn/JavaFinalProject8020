package hk.edu.hkmu.jiang.javafinal.Infrastructure.repository;

import hk.edu.hkmu.jiang.javafinal.Infrastructure.persistence.assembler.InventoryChangeRecordPersistenceAssembler;
import hk.edu.hkmu.jiang.javafinal.Infrastructure.persistence.assembler.InventoryPersistenceAssembler;
import hk.edu.hkmu.jiang.javafinal.Infrastructure.persistence.mapper.InventoryChangeRecordMapper;
import hk.edu.hkmu.jiang.javafinal.Infrastructure.persistence.mapper.InventoryMapper;
import hk.edu.hkmu.jiang.javafinal.Infrastructure.persistence.po.InventoryChangeRecordPO;
import hk.edu.hkmu.jiang.javafinal.Infrastructure.persistence.po.InventoryPO;
import hk.edu.hkmu.jiang.javafinal.domain.inventory.aggregate.Inventory;
import hk.edu.hkmu.jiang.javafinal.domain.inventory.aggregate.InventoryChangeRecord;
import hk.edu.hkmu.jiang.javafinal.domain.inventory.repository.InventoryRepository;
import hk.edu.hkmu.jiang.javafinal.domain.shaired.enums.InventoryType;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InventoryRepositoryImpl implements InventoryRepository {

    @NonNull
    private InventoryMapper inventoryMapper;
    @NonNull
    private InventoryChangeRecordMapper inventoryChangeRecordMapper;
    @NonNull
    private InventoryPersistenceAssembler inventoryPersistenceAssembler;
    @NonNull
    private InventoryChangeRecordPersistenceAssembler inventoryChangeRecordPersistenceAssembler;

    @Override
    public Inventory queryBySkuIdAndType(Long skuId, InventoryType type) {
        return null;
    }

    @Override
    public Inventory create(Inventory inventory) {
        InventoryPO inventoryPO = inventoryPersistenceAssembler.assemblePo(inventory);
        inventoryMapper.insert(inventoryPO);
        return inventoryPersistenceAssembler.assembleAggregate(inventoryPO);
    }

    @Override
    public void createRecord(InventoryChangeRecord inventoryChangeRecord) {
        InventoryChangeRecordPO inventoryChangeRecordPO =
                inventoryChangeRecordPersistenceAssembler.assemblePo(inventoryChangeRecord);
        inventoryChangeRecordMapper.insert(inventoryChangeRecordPO);
    }
}
