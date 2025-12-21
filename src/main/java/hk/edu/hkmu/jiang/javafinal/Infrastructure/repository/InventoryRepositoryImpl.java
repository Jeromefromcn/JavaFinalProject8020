package hk.edu.hkmu.jiang.javafinal.Infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
import org.springframework.util.CollectionUtils;

import java.util.List;

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
        LambdaQueryWrapper<InventoryPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(InventoryPO::getType, type)
                .eq(InventoryPO::getSkuId, skuId);
        InventoryPO inventoryPO = inventoryMapper.selectOne(wrapper);
        return inventoryPersistenceAssembler.assembleAggregate(inventoryPO);
    }

    @Override
    public Inventory create(Inventory inventory) {
        InventoryPO inventoryPO = inventoryPersistenceAssembler.assemblePo(inventory);
        inventoryMapper.insert(inventoryPO);
        if (!CollectionUtils.isEmpty(inventory.getChangeRecords())) {
            List<InventoryChangeRecord> changeRecords = inventory.getChangeRecords();
            changeRecords.forEach(inventoryChangeRecord -> {
                inventoryChangeRecord.setInventoryId(inventoryPO.getId());
                InventoryChangeRecordPO recordPO =
                        inventoryChangeRecordPersistenceAssembler.assemblePo(inventoryChangeRecord);
                inventoryChangeRecordMapper.insert(recordPO);
                inventoryChangeRecord.setId(recordPO.getId());
            });
        }
        return inventoryPersistenceAssembler.assembleAggregate(inventoryPO);
    }

    @Override
    public List<Inventory> queryBySkuIdsAndType(List<Long> ids, InventoryType type) {
        LambdaQueryWrapper<InventoryPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(InventoryPO::getType, type)
                .in(InventoryPO::getSkuId, ids);
        List<InventoryPO> inventoryPOs = inventoryMapper.selectList(wrapper);
        return inventoryPersistenceAssembler.assembleAggregateList(inventoryPOs);
    }

    @Override
    public void updateInventories(List<Inventory> inventories) {
        if (CollectionUtils.isEmpty(inventories)) {
            return;
        }
        inventories.forEach(inventory -> {
            inventoryMapper.updateById(inventoryPersistenceAssembler.assemblePo(inventory));
            List<InventoryChangeRecord> changeRecords = inventory.getChangeRecords();
            if (CollectionUtils.isEmpty(changeRecords)) {
                return;
            }
            changeRecords.forEach(inventoryChangeRecord ->
                    inventoryChangeRecordMapper.insert(inventoryChangeRecordPersistenceAssembler
                            .assemblePo(inventoryChangeRecord)));
        });
    }
}
