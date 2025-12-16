package hk.edu.hkmu.jiang.javafinal.domain.sku.repository;

import hk.edu.hkmu.jiang.javafinal.domain.sku.aggregate.Sku;

import java.util.List;

public interface SkuRepository {

    Sku queryByCode(String code);

    Sku create(Sku sku);

    Sku update(Sku sku);

    List<Sku> queryAll();
}
