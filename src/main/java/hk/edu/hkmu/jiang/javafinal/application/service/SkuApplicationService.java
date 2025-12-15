package hk.edu.hkmu.jiang.javafinal.application.service;

import hk.edu.hkmu.jiang.javafinal.application.dto.SkuDTO;

public interface SkuApplicationService {

    SkuDTO queryBySkuCode(String code);

    SkuDTO save(SkuDTO sku);
}
