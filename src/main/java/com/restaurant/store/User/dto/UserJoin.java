package com.restaurant.store.User.dto;

import com.restaurant.store.User.domain.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserJoin {

    @ApiModelProperty(value = "사용자의 아이디", example = "admin", required = true)
    private String userId;

    @ApiModelProperty(value = "사용자의 비밀번호", example = "1234", required = true)
    private String password;

    public User toEntity() {
        return User.builder()
                .userId(userId)
                .password(password)
                .build();
    }
}
