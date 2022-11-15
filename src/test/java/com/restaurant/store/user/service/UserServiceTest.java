package com.restaurant.store.user.service;

import com.restaurant.store.common.ResponseMessage;
import com.restaurant.store.common.StatusCode;
import com.restaurant.store.common.dto.CommonRes;
import com.restaurant.store.user.domain.User;
import com.restaurant.store.user.domain.UserRole;
import com.restaurant.store.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserService userService;
    @Mock
    UserRepository userRepository;

    long findId = 1L;

    @Test
    @DisplayName("유저찾기 실패")
    public void userFindFail() throws Exception {

        given(userRepository.findById(findId)).willReturn(Optional.empty());
        CommonRes<Optional<User>> commonRes = new CommonRes<>();
        commonRes.setStatusCode(StatusCode.NOT_FOUND);
        commonRes.setResponseMessage(ResponseMessage.USER_FIND_FAIL);
        commonRes.setData(Optional.empty());

        CommonRes<Optional<User>> result = userService.findUser(1L);

        verify(userRepository, times(1)).findById(findId);
        assertThat(result, is(commonRes));
    }

    @Test
    @DisplayName("유저찾기 성공")
    public void userFindSuccess() throws Exception {

        User user = User.builder()
                .id(1L)
                .userId("testId")
                .password("1111")
                .userRole(UserRole.ADMIN).build();
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