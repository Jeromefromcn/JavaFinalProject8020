package hk.edu.hkmu.jiang.javafinal.Infrastructure.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import hk.edu.hkmu.jiang.javafinal.Infrastructure.persistence.mapper.InventoryMapper;
import hk.edu.hkmu.jiang.javafinal.Infrastructure.persistence.mapper.SkuMapper;
import hk.edu.hkmu.jiang.javafinal.Infrastructure.persistence.po.InventoryPO;
import hk.edu.hkmu.jiang.javafinal.Infrastructure.persistence.po.SkuPO;
import hk.edu.hkmu.jiang.javafinal.domain.shaired.enums.InventoryType;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InitializePresetData {

    @NonNull
    private SkuMapper skuMapper;
    @NonNull
    private InventoryMapper inventoryMapper;


    @EventListener(ApplicationReadyEvent.class)
    public void initializeData() {
        initSku();
        initInventory();
    }

    private void initInventory() {
        List<SkuPO> skus = skuMapper.selectList(new QueryWrapper<>());
        skus.forEach(sku -> {
            Long skuId = sku.getId();
            inventoryMapper.insert(
                    InventoryPO.builder()
                            .type(InventoryType.SHELF)
                            .skuId(skuId)
                            .quantity(10)
                            .build()
            );
            inventoryMapper.insert(
                    InventoryPO.builder()
                            .type(InventoryType.WAREHOUSE)
                            .skuId(skuId)
                            .quantity(1000)
                            .build()
            );
        });
    }

    private void initSku() {
        skuMapper.insert(SkuPO.builder()
                .code("code1")
                .name("name1")
                .description("description1")
                .maximumInWarehouse(10000)
                .minimumInWarehouse(1000)
                .maximumOnShelf(100)
                .minimumOnShelf(10)
                .build());
        skuMapper.insert(SkuPO.builder()
                .code("code2")
                .name("name2")
                .description("description2")
                .maximumInWarehouse(20000)
                .minimumInWarehouse(2000)
                .maximumOnShelf(100)
                .minimumOnShelf(10)
                .build());
        skuMapper.insert(SkuPO.builder()
                .code("code3")
                .name("name3")
                .description("description3")
                .maximumInWarehouse(50000)
                .minimumInWarehouse(5000)
                .maximumOnShelf(1000)
                .minimumOnShelf(100)
                .build());
    }
}