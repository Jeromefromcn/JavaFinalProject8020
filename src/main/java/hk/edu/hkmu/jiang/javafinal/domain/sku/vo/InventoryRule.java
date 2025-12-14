package hk.edu.hkmu.jiang.javafinal.domain.sku.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryRule {
    private Integer maximumOnShelf;
    private Integer minimumOnShelf;
    private Integer maximumInWarehouse;
    private Integer minimumInWarehouse;
}
