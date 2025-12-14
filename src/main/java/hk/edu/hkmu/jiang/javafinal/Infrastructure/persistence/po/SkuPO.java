package hk.edu.hkmu.jiang.javafinal.Infrastructure.persistence.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import hk.edu.hkmu.jiang.javafinal.domain.shaired.aggregate.Basic;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sku")
public class SkuPO extends Basic {
    private String code;
    private String name;
    private String description;
    private Integer maximumOnShelf;
    private Integer minimumOnShelf;
    private Integer maximumInWarehouse;
    private Integer minimumInWarehouse;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
