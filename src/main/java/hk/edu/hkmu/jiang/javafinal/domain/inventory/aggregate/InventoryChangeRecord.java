package hk.edu.hkmu.jiang.javafinal.domain.inventory.aggregate;

import hk.edu.hkmu.jiang.javafinal.domain.shaired.aggregate.Basic;
import hk.edu.hkmu.jiang.javafinal.domain.shaired.enums.OperationType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryChangeRecord extends Basic {
    private Long inventoryId;
    private OperationType operationType;
    private Long previousQuantity;
    private Long newQuantity;
    private LocalDateTime createdAt;
    private String notes;
}
