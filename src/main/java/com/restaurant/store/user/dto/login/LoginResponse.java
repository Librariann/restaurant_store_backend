package com.restaurant.store.user.dto.login;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    @ApiModelProperty(value = "로그인 json web token", required = true)
    private String token;
}
