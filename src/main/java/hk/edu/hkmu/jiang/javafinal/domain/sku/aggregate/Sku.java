package hk.edu.hkmu.jiang.javafinal.domain.sku.aggregate;

import hk.edu.hkmu.jiang.javafinal.domain.shaired.aggregate.Basic;
import hk.edu.hkmu.jiang.javafinal.domain.sku.vo.InventoryRule;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Sku extends Basic {
    private String code;
    private String name;
    private String description;
    private InventoryRule inventoryRule;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
