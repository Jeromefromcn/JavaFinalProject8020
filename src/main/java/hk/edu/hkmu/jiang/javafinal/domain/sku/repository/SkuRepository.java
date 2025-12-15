package hk.edu.hkmu.jiang.javafinal.domain.sku.repository;

import hk.edu.hkmu.jiang.javafinal.domain.sku.aggregate.Sku;

public interface SkuRepository {

    Sku queryByCode(String code);

    Sku create(Sku sku);

    Sku update(Sku sku);
}
