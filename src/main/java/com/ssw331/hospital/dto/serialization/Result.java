package com.ssw331.hospital.dto.serialization;

import lombok.Data;

import java.io.Serializable;
import java.util.*;

/**
 * @author 24053
 */
@Data
public class Result<T> implements Serializable {
    private UUID id;
    private boolean success;
    private T data;

    public Result<T> setResult(boolean success) {
        this.id = UUID.randomUUID();
        this.success = success;

        return this;
    }

    public Result<T> setResult(boolean success, T data) {
        this.id = UUID.randomUUID();
        this.success = success;
        this.data = data;

        return this;
    }
}
