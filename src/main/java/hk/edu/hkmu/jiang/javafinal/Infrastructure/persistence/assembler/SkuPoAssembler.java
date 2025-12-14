package hk.edu.hkmu.jiang.javafinal.Infrastructure.persistence.assembler;

import hk.edu.hkmu.jiang.javafinal.Infrastructure.persistence.po.SkuPO;
import hk.edu.hkmu.jiang.javafinal.domain.sku.aggregate.Sku;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SkuPoAssembler {
    @Mapping(source = "inventoryRule.maximumOnShelf", target = "maximumOnShelf")
    @Mapping(source = "inventoryRule.minimumOnShelf", target = "minimumOnShelf")
    @Mapping(source = "inventoryRule.maximumInWarehouse", target = "maximumInWarehouse")
    @Mapping(source = "inventoryRule.minimumInWarehouse", target = "minimumInWarehouse")
    SkuPO toPo(Sku object);

    @Mapping(source = "maximumOnShelf", target = "inventoryRule.maximumOnShelf")
    @Mapping(source = "minimumOnShelf", target = "inventoryRule.minimumOnShelf")
    @Mapping(source = "maximumInWarehouse", target = "inventoryRule.maximumInWarehouse")
    @Mapping(source = "minimumInWarehouse", target = "inventoryRule.minimumInWarehouse")
    Sku toAggregate(SkuPO object);

    List<SkuPO> toPoList(List<Sku> objectList);

    List<Sku> toAggregateList(List<SkuPO> objectList);
}