package hk.edu.hkmu.jiang.javafinal.application.service.impl;

import hk.edu.hkmu.jiang.javafinal.application.service.InventoryApplicationService;
import hk.edu.hkmu.jiang.javafinal.domain.inventory.repository.InventoryRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InventoryApplicationServiceImpl implements InventoryApplicationService {

    @NonNull
    private InventoryRepository inventoryRepository;


}
