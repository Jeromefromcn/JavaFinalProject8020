package hk.edu.hkmu.jiang.javafinal.Infrastructure.persistence.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import hk.edu.hkmu.jiang.javafinal.domain.shaired.aggregate.Basic;
import hk.edu.hkmu.jiang.javafinal.domain.shaired.enums.InventoryType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("inventory")
public class InventoryPO extends Basic {
    private Long skuId;
    private InventoryType type;
    private Integer quantity;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
