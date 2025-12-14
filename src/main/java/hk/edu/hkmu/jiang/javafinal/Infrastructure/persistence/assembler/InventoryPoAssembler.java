package hk.edu.hkmu.jiang.javafinal.Infrastructure.persistence.assembler;

import hk.edu.hkmu.jiang.javafinal.Infrastructure.persistence.po.InventoryPO;
import hk.edu.hkmu.jiang.javafinal.domain.inventory.aggregate.Inventory;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InventoryPoAssembler {
    InventoryPO toPo(Inventory object);

    Inventory toAggregate(InventoryPO object);

    List<InventoryPO> toPoList(List<Inventory> objectList);

    List<Inventory> toAggregateList(List<InventoryPO> objectList);
}