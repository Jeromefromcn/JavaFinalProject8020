package hk.edu.hkmu.jiang.javafinal.application.service.impl;

import hk.edu.hkmu.jiang.javafinal.application.assembler.SkuAppAssembler;
import hk.edu.hkmu.jiang.javafinal.application.dto.SkuDTO;
import hk.edu.hkmu.jiang.javafinal.application.proxy.MessageSendingProxy;
import hk.edu.hkmu.jiang.javafinal.application.service.SkuApplicationService;
import hk.edu.hkmu.jiang.javafinal.common.util.GsonUtil;
import hk.edu.hkmu.jiang.javafinal.domain.sku.aggregate.Sku;
import hk.edu.hkmu.jiang.javafinal.domain.sku.repository.SkuRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SkuApplicationServiceImpl implements SkuApplicationService {

    @NonNull
    private SkuRepository skuRepository;
    @NonNull
    private SkuAppAssembler skuAppAssembler;
    @NonNull
    private MessageSendingProxy messageSendingProxy;
    @Value("${app.properties.topic.sku-created}")
    private String topicSkuCreated;

    @Override
    public SkuDTO queryBySkuCode(String code) {
        return skuAppAssembler.assembleDto(skuRepository.queryByCode(code));
    }

    @Override
    public SkuDTO save(SkuDTO skuDTO) {
        Sku sku = skuAppAssembler.assembleAggregate(skuDTO);
        boolean creation = sku.isCreation();
        SkuDTO savedSkuDTO;
        if (creation) {
            Sku savedSku = skuRepository.create(sku);
            savedSkuDTO = skuAppAssembler.assembleDto(savedSku);
            log.info("Sku code: {} has been created", savedSku.getCode());
            messageSendingProxy.sendMessage(topicSkuCreated, GsonUtil.getGson().toJson(savedSkuDTO));
        } else {
            Sku updatedSku = skuRepository.update(sku);
            savedSkuDTO = skuAppAssembler.assembleDto(updatedSku);
            log.info("Sku code: {} has been updated", updatedSku.getCode());
        }
        return savedSkuDTO;
    }
}
