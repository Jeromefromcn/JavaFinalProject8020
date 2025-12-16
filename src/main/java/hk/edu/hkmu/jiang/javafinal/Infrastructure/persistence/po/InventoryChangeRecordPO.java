package hk.edu.hkmu.jiang.javafinal.Infrastructure.persistence.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("inventory_change_record")
public class InventoryChangeRecordPO extends Basic {
    private Long inventoryId;
    private OperationType operationType;
    private Integer previousQuantity;
    private Integer newQuantity;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
    private String notes;
}
