package hk.edu.hkmu.jiang.javafinal.interfaces.controller;

import hk.edu.hkmu.jiang.javafinal.application.dto.SkuDTO;
import hk.edu.hkmu.jiang.javafinal.application.service.SkuApplicationService;
import hk.edu.hkmu.jiang.javafinal.interfaces.exception.BaseResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/sku")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SkuController {

    @NonNull
    private SkuApplicationService skuApplicationService;

    @GetMapping("/query_by_code")
    public BaseResponse<SkuDTO> queryByCode(String code) {
        SkuDTO skuDTO = skuApplicationService.queryBySkuCode(code);
        return BaseResponse.<SkuDTO>builder()
                .message(skuDTO)
                .code(HttpStatus.OK.getReasonPhrase())
                .build();
    }

    @PostMapping("/save")
    public BaseResponse<SkuDTO> save(@RequestBody SkuDTO sku) {
        SkuDTO skuDTO = skuApplicationService.save(sku);
        return BaseResponse.<SkuDTO>builder()
                .message(skuDTO)
                .code(HttpStatus.OK.getReasonPhrase())
                .build();
    }

}
