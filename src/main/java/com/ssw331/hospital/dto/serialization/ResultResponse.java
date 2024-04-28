package com.ssw331.hospital.dto.serialization;

/**
 * @author 24053
 */
public class ResultResponse {
    public static Result<Object> success(Object data) {
        return new Result<>().setResult(true, data);
    }

    public static Result<Object> failure() {
        return new Result<>().setResult(false);
    }
}
