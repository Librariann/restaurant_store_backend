package com.restaurant.store.user.service;

import com.restaurant.store.common.ResponseMessage;
import com.restaurant.store.common.StatusCode;
import com.restaurant.store.common.dto.CommonRes;
import com.restaurant.store.user.domain.User;
import com.restaurant.store.user.dto.UserJoin;
import com.restaurant.store.user.domain.UserRole;
import com.restaurant.store.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.internal.matchers.Null;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    long findId = 1L;

    User user = User.builder()
            .id(1L)
            .userId("testId")
            .password("1111")
            .userRole(UserRole.ADMIN).build();

    UserJoin userJoin = UserJoin.builder()
            .userId("testId")
            .password("1111")
            .userRole(UserRole.ADMIN)
            .build();

    @Nested
    @DisplayName("계정생성")
    class CreateUser {
        @Test
        @DisplayName("중복된 유저명으로 인한 계정생성 실패")
        public void createUserFail() throws Exception {

            given(userRepository.findByUserId(any())).willReturn(Optional.of(user));

            CommonRes<Optional<User>> commonRes = new CommonRes<>();
            commonRes.setStatusCode(StatusCode.BAD_REQUEST);
            commonRes.setResponseMessage(ResponseMessage.USER_OVERLAP);
            commonRes.setData(null);

            CommonRes<User> result = userService.createAccount(userJoin);

            verify(userRepository, times(1)).findByUserId(any());
            assertThat(result, is(commonRes));
        }

        @Test
        @DisplayName("계정 생성 성공")
        public void createUserSuccess() throws Exception {

            given(userRepository.findByUserId(any())).willReturn(Optional.empty());
            given(passwordEncoder.encode(anyString())).willReturn("encodePassword");

            CommonRes<Optional<User>> commonRes = new CommonRes<>();
            commonRes.setStatusCode(StatusCode.OK);
            commonRes.setResponseMessage(ResponseMessage.JOIN_SUCCESS);
            commonRes.setData(null);

            CommonRes<User> result = userService.createAccount(userJoin);

            verify(userRepository, times(1)).findByUserId(any());
            verify(userRepository, times(1)).save(any());

            assertThat(result, is(commonRes));
        }
    }

    @Test
    @DisplayName("유저찾기 실패")
    public void findUserFail() throws Exception {

        given(userRepository.findById(findId)).willReturn(Optional.empty());

        CommonRes<Optional<User>> commonRes = new CommonRes<>();
        commonRes.setStatusCode(StatusCode.NOT_FOUND);
        commonRes.setResponseMessage(ResponseMessage.USER_FIND_FAIL);
        commonRes.setData(null);

        CommonRes<Optional<User>> result = userService.findUser(1L);

        verify(userRepository, times(1)).findById(findId);
        assertThat(result, is(commonRes));
    }

    @Test
    @DisplayName("유저찾기 성공")
    public void findUserSuccess() throws Exception {

        given(userRepository.findById(any())).willReturn(Optional.of(user));

        CommonRes<Optional<User>> commonRes = new CommonRes<>();
        commonRes.setStatusCode(StatusCode.OK);
        commonRes.setResponseMessage(ResponseMessage.USER_FIND_SUCCESS);
        commonRes.setData(Optional.of(user));

        CommonRes<Optional<User>> result = userService.findUser(findId);

        verify(userRepository, times(1)).findById(findId);
        assertThat(result, is(commonRes));
    }
}