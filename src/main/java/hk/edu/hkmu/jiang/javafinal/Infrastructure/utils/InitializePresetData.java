package hk.edu.hkmu.jiang.javafinal.Infrastructure.utils;

import hk.edu.hkmu.jiang.javafinal.Infrastructure.persistence.mapper.SkuMapper;
import hk.edu.hkmu.jiang.javafinal.Infrastructure.persistence.po.SkuPO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InitializePresetData {

    @NonNull
    private SkuMapper skuMapper;


    @EventListener(ApplicationReadyEvent.class)
    public void initializeData() {
        initSku();
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