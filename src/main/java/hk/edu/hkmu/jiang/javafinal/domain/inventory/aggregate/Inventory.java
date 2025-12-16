package hk.edu.hkmu.jiang.javafinal.domain.inventory.aggregate;

import hk.edu.hkmu.jiang.javafinal.domain.shaired.aggregate.Basic;
import hk.edu.hkmu.jiang.javafinal.domain.shaired.enums.InventoryType;
import hk.edu.hkmu.jiang.javafinal.domain.shaired.enums.OperationType;
import hk.edu.hkmu.jiang.javafinal.domain.shaired.exception.DomainException;
import hk.edu.hkmu.jiang.javafinal.domain.shaired.exception.ErrorCode;
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

    public void add(Integer quantity) {
        this.changeRecords = List.of(InventoryChangeRecord.builder()
                .inventoryId(this.id)
                .operationType(OperationType.INBOUND)
                .previousQuantity(this.quantity)
                .newQuantity(this.quantity + quantity)
                .build());
        this.quantity += quantity;
    }

    public void deduct(Integer quantity) {
        if (this.quantity < quantity) {
            throw new DomainException(ErrorCode.INSUFFICIENT_INVENTORY.getCode(),
                    ErrorCode.INSUFFICIENT_INVENTORY.getMessage());
        }
        this.changeRecords = List.of(InventoryChangeRecord.builder()
                .inventoryId(this.id)
                .operationType(OperationType.OUTBOUND)
                .previousQuantity(this.quantity)
                .newQuantity(this.quantity - quantity)
                .build());
        this.quantity -= quantity;
    }
}
