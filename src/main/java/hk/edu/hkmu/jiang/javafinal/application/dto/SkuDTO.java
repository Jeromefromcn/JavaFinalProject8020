package hk.edu.hkmu.jiang.javafinal.application.dto;

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
public class SkuDTO extends Basic {
    private String code;
    private String name;
    private String description;
    private ReplenishmentRuleDTO replenishmentRule;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
