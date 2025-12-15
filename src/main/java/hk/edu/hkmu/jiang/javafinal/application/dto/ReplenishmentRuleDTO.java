package hk.edu.hkmu.jiang.javafinal.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplenishmentRuleDTO {
    private Integer maximumOnShelf;
    private Integer minimumOnShelf;
    private Integer maximumInWarehouse;
    private Integer minimumInWarehouse;
}
