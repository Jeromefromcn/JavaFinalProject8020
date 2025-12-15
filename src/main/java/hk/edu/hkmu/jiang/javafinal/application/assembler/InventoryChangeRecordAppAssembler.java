package hk.edu.hkmu.jiang.javafinal.application.assembler;

import hk.edu.hkmu.jiang.javafinal.application.dto.InventoryChangeRecordDTO;
import hk.edu.hkmu.jiang.javafinal.domain.inventory.aggregate.InventoryChangeRecord;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InventoryChangeRecordAppAssembler {
    InventoryChangeRecordDTO toDto(InventoryChangeRecord object);

    InventoryChangeRecord toAggregate(InventoryChangeRecordDTO object);

    List<InventoryChangeRecordDTO> toDtoList(List<InventoryChangeRecord> objectList);

    List<InventoryChangeRecord> toAggregateList(List<InventoryChangeRecordDTO> objectList);
}