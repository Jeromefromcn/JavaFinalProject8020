package hk.edu.hkmu.jiang.javafinal.domain.inventory.aggregate;

import hk.edu.hkmu.jiang.javafinal.domain.shaired.aggregate.Basic;
import hk.edu.hkmu.jiang.javafinal.domain.shaired.enums.InventoryType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Inventory extends Basic {
    private Long skuId;
    private InventoryType type;
    private Integer quantity;
    private List<InventoryChangeRecord> changeRecords;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void initQuantity() {
        this.quantity = 0;
    }

    public boolean isSufficient(Integer deduction) {
        return this.quantity >= deduction;
    }
}
