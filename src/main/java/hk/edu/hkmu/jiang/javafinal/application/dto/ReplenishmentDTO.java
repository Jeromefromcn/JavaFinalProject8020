package hk.edu.hkmu.jiang.javafinal.application.dto;

import hk.edu.hkmu.jiang.javafinal.domain.shaired.enums.InventoryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplenishmentDTO {
    private Long skuId;
    private String skuCode;
    private String skuName;
    private Integer currentQuantity;
    private Integer needQuantity;
    private InventoryType type;
}
