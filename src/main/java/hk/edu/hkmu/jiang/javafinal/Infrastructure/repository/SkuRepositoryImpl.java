package hk.edu.hkmu.jiang.javafinal.Infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import hk.edu.hkmu.jiang.javafinal.Infrastructure.persistence.assembler.SkuPersistenceAssembler;
import hk.edu.hkmu.jiang.javafinal.Infrastructure.persistence.mapper.SkuMapper;
import hk.edu.hkmu.jiang.javafinal.Infrastructure.persistence.po.SkuPO;
import hk.edu.hkmu.jiang.javafinal.domain.sku.aggregate.Sku;
import hk.edu.hkmu.jiang.javafinal.domain.sku.repository.SkuRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SkuRepositoryImpl implements SkuRepository {

    @NonNull
    private SkuMapper skuMapper;
    @NonNull
    private SkuPersistenceAssembler skuPersistenceAssembler;

    @Override
    public Sku queryByCode(String code) {
        LambdaQueryWrapper<SkuPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SkuPO::getCode, code);
        SkuPO skuPO = skuMapper.selectOne(wrapper);
        return skuPersistenceAssembler.assembleAggregate(skuPO);
    }

    @Override
    public Sku create(Sku sku) {
        SkuPO skuPO = skuPersistenceAssembler.assemblePo(sku);
        skuMapper.insert(skuPO);
        return skuPersistenceAssembler.assembleAggregate(skuPO);
    }

    @Override
    public Sku update(Sku sku) {
        SkuPO skuPO = skuPersistenceAssembler.assemblePo(sku);
        skuMapper.updateById(skuPO);
        return skuPersistenceAssembler.assembleAggregate(skuPO);
    }
}
