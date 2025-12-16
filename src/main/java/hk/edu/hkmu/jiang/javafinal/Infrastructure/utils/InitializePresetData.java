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
import java.util.stream.IntStream;

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
                            .quantity(100)
                            .build()
            );
        });
    }

    private void initSku() {
        IntStream.range(0, 5).forEach(
                i -> skuMapper.insert(SkuPO.builder()
                        .code(i + "A")
                        .name(i + "B")
                        .description(i + "C")
                        .maximumInWarehouse(i + 10)
                        .minimumInWarehouse(i + 100)
                        .maximumOnShelf(i)
                        .minimumOnShelf(i + 10)
                        .build()));
    }
}