package com.restaurant.store.common.dto;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonRes<T> {
    private int statusCode;
    private String responseMessage;
    private T data;
}
