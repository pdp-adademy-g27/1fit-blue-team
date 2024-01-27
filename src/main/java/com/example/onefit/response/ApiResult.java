package com.example.onefit.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResult<E> {
    private final boolean success;
    private String message;
    private E data;
    private List<ErrorData> errors;

    private ApiResult(E data) {
        this.data = data;
        success = true;
    }

    private ApiResult() {
        success = true;
    }

    private ApiResult(List<ErrorData> errors) {
        this.errors = errors;
        success = false;
    }

    private ApiResult(boolean isSuccess, String msg) {
        this.message = msg;
        this.success = isSuccess;
    }

    public static <T> ApiResult<T> successResponse(T data) {
        return new ApiResult<>(data);
    }

    public static <T> ApiResult<T> successResponse() {
        return new ApiResult<>();
    }

    public static <T> ApiResult<T> successResponse(boolean isSuccess, String message) {
        return new ApiResult<>(isSuccess,message);
    }

    public static ApiResult<ErrorData> failResponse(String msg  , Integer errorCode) {
        return new ApiResult<>(List.of(new ErrorData(errorCode, msg)));
    }

}
