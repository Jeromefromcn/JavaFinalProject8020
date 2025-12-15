package hk.edu.hkmu.jiang.javafinal.application.assembler;

import hk.edu.hkmu.jiang.javafinal.application.dto.SkuDTO;
import hk.edu.hkmu.jiang.javafinal.domain.sku.aggregate.Sku;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SkuAppAssembler {
    SkuDTO assembleDto(Sku object);

    Sku assembleAggregate(SkuDTO object);

    List<SkuDTO> assembleDtoList(List<Sku> objectList);

    List<Sku> assembleAggregateList(List<SkuDTO> objectList);
}