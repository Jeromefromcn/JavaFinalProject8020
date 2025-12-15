package hk.edu.hkmu.jiang.javafinal.application.assembler;

import hk.edu.hkmu.jiang.javafinal.application.dto.InventoryDTO;
import hk.edu.hkmu.jiang.javafinal.domain.inventory.aggregate.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InventoryAppAssembler {
    @Mapping(target = "changeRecords", ignore = true)
    InventoryDTO assembleDto(Inventory object);

    @Mapping(target = "changeRecords", ignore = true)
    Inventory assembleAggregate(InventoryDTO object);

    List<InventoryDTO> assembleDtoList(List<Inventory> objectList);

    List<Inventory> assembleAggregateList(List<InventoryDTO> objectList);
}