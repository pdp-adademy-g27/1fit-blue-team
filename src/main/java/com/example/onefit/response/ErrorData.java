package com.example.onefit.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorData {
    private int errorCode;
    private String msg;
}
