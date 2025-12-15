package hk.edu.hkmu.jiang.javafinal.interfaces.exception;


import lombok.Builder;
import lombok.Getter;


@Getter
public class BaseResponse<T> {
    private final String code;
    private final T message;
    private final long timestamp;

    @Builder
    public BaseResponse(String code, T message) {
        this.code = code;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }
}