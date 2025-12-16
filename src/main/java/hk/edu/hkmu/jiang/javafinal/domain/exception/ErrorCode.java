package hk.edu.hkmu.jiang.javafinal.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INSUFFICIENT_INVENTORY("INSUFFICIENT_INVENTORY", "Insufficient Inventory"),
    ;

    private final String code;
    private final String message;
}
