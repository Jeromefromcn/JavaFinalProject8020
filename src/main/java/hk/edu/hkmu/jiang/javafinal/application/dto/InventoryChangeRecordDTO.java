package hk.edu.hkmu.jiang.javafinal.application.dto;

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
public class InventoryChangeRecordDTO extends Basic {
    private Long inventoryId;
    private OperationType operationType;
    private Integer previousQuantity;
    private Integer newQuantity;
    private LocalDateTime createdAt;
    private String notes;
}
