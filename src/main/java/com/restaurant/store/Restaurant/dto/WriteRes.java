package com.restaurant.store.Restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WriteRes {
    private Long id;
    private String detailInfo;
    private String hashTag;
    private String Title;
}
