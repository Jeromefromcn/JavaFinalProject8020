package hk.edu.hkmu.jiang.javafinal.interfaces.controller;

import hk.edu.hkmu.jiang.javafinal.application.service.InventoryApplicationService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InventoryController {

    @NonNull
    private InventoryApplicationService inventoryApplicationService;

}
