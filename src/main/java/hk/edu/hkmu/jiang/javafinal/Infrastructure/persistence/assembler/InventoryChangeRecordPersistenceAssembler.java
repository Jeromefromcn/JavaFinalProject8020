package hk.edu.hkmu.jiang.javafinal.Infrastructure.persistence.assembler;

import hk.edu.hkmu.jiang.javafinal.Infrastructure.persistence.po.InventoryChangeRecordPO;
import hk.edu.hkmu.jiang.javafinal.domain.inventory.aggregate.InventoryChangeRecord;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InventoryChangeRecordPersistenceAssembler {
    InventoryChangeRecordPO toPo(InventoryChangeRecord object);

    InventoryChangeRecord toAggregate(InventoryChangeRecordPO object);

    List<InventoryChangeRecordPO> toPoList(List<InventoryChangeRecord> objectList);

    List<InventoryChangeRecord> toAggregateList(List<InventoryChangeRecordPO> objectList);
}