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
public class LoginRequest {

    @ApiModelProperty(value = "사용자의 아이디", example = "admin", required = true)
    private String userId;

    @ApiModelProperty(value = "사용자의 비밀번호", example = "1234", required = true)
    private String password;
}
