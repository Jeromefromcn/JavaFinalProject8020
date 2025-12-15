package hk.edu.hkmu.jiang.javafinal.Infrastructure.persistence.assembler;

import hk.edu.hkmu.jiang.javafinal.Infrastructure.persistence.po.InventoryPO;
import hk.edu.hkmu.jiang.javafinal.domain.inventory.aggregate.Inventory;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InventoryPersistenceAssembler {
    InventoryPO assemblePo(Inventory object);

    Inventory assembleAggregate(InventoryPO object);

    List<InventoryPO> assemblePoList(List<Inventory> objectList);

    List<Inventory> assembleAggregateList(List<InventoryPO> objectList);
}