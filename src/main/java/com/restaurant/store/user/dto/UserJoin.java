package com.restaurant.store.user.dto;

import com.restaurant.store.SecurityConfig;
import com.restaurant.store.user.domain.User;
import com.restaurant.store.user.domain.UserRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.PrePersist;
import javax.persistence.Transient;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserJoin {

    @ApiModelProperty(value = "사용자의 아이디", example = "admin", required = true)
    private String userId;

    @ApiModelProperty(value = "사용자의 비밀번호", example = "1234", required = true)
    private String password;

    @ApiModelProperty(value = "사용자의 권한", example = "admin", required = true)
    private UserRole userRole;

    public User toEntity() {
        return User.builder()
                .userId(userId)
                .password(password)
                .userRole((userRole))
                .build();
    }
}