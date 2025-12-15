package hk.edu.hkmu.jiang.javafinal.Infrastructure.persistence.assembler;

import hk.edu.hkmu.jiang.javafinal.Infrastructure.persistence.po.SkuPO;
import hk.edu.hkmu.jiang.javafinal.domain.sku.aggregate.Sku;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SkuPersistenceAssembler {
    @Mapping(source = "replenishmentRule.maximumOnShelf", target = "maximumOnShelf")
    @Mapping(source = "replenishmentRule.minimumOnShelf", target = "minimumOnShelf")
    @Mapping(source = "replenishmentRule.maximumInWarehouse", target = "maximumInWarehouse")
    @Mapping(source = "replenishmentRule.minimumInWarehouse", target = "minimumInWarehouse")
    SkuPO assemblePo(Sku object);

    @Mapping(source = "maximumOnShelf", target = "replenishmentRule.maximumOnShelf")
    @Mapping(source = "minimumOnShelf", target = "replenishmentRule.minimumOnShelf")
    @Mapping(source = "maximumInWarehouse", target = "replenishmentRule.maximumInWarehouse")
    @Mapping(source = "minimumInWarehouse", target = "replenishmentRule.minimumInWarehouse")
    Sku assembleAggregate(SkuPO object);

    List<SkuPO> assemblePoList(List<Sku> objectList);

    List<Sku> assembleAggregateList(List<SkuPO> objectList);
}