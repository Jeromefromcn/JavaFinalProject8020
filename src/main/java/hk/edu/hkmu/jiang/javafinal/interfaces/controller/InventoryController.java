package hk.edu.hkmu.jiang.javafinal.interfaces.controller;

import hk.edu.hkmu.jiang.javafinal.application.service.InventoryApplicationService;
import hk.edu.hkmu.jiang.javafinal.common.annotation.Loggable;
import hk.edu.hkmu.jiang.javafinal.interfaces.exception.BaseResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InventoryController {

    @NonNull
    private InventoryApplicationService inventoryApplicationService;

    @Loggable
    @PostMapping("/purchase")
    public BaseResponse<Boolean> purchase(@RequestBody Map<Long, Integer> skuIdDeductionMap) {
        Boolean deductResult = inventoryApplicationService.deductInventory(skuIdDeductionMap);
        return BaseResponse.<Boolean>builder()
                .message(deductResult)
                .code(HttpStatus.OK.getReasonPhrase())
                .build();
    }
}
