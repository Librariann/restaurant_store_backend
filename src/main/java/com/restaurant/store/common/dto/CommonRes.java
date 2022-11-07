package com.restaurant.store.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonRes<T> {
    private int statusCode;
    private String responseMessage;
    private T data;
}
