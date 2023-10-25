package com.restaurant.store.user.service;

import com.restaurant.store.common.StatusCode;
import com.restaurant.store.common.dto.CommonRes;
import com.restaurant.store.jwt.JwtToken;
import com.restaurant.store.jwt.JwtTokenProvider;
import com.restaurant.store.user.domain.User;
import com.restaurant.store.user.dto.UserJoin;
import com.restaurant.store.user.dto.login.LoginRequest;
import com.restaurant.store.user.dto.login.LoginResponse;
import com.restaurant.store.user.repository.UserRepository;
import com.restaurant.store.common.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    JwtTokenProvider jwtTokenProvider;



    public CommonRes<User> createUser(UserJoin userJoin) throws Exception {

            //아이디 유무 확인
            userRepository.findByUserId(userJoin.getUserId())
                    .orElseThrow(() -> new Exception(ResponseMessage.USER_OVERLAP));

            CommonRes<User> commonRes = new CommonRes<User>();

            User saveUser = userJoin.toEntity();
            saveUser.setPassword(passwordEncoder.encode(saveUser.getPassword()));
            userRepository.save(saveUser);

            commonRes.setStatusCode(StatusCode.OK);
            commonRes.setResponseMessage(ResponseMessage.JOIN_SUCCESS);

            return commonRes;
    }


    public CommonRes<User> findUser(Long id) throws Exception {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new Exception(ResponseMessage.USER_FIND_FAIL));

        CommonRes<User> commonRes = new CommonRes<>();


        commonRes.setStatusCode(StatusCode.OK);
        commonRes.setResponseMessage(ResponseMessage.USER_FIND_SUCCESS);
        commonRes.setData(user);

        return commonRes;
    }

    public CommonRes<String> deleteUser(Long id) throws Exception{

        //아이디 유무 확인
        userRepository.findById(id)
                .orElseThrow(() -> new Exception(ResponseMessage.DELETE_FAIL));

        CommonRes<String> commonRes = new CommonRes<>();

        //아이디 삭제
        userRepository.deleteById(id);

        commonRes.setStatusCode(StatusCode.OK);
        commonRes.setResponseMessage(ResponseMessage.DELETE_SUCCESS);
        return commonRes;
    }

    public CommonRes<LoginResponse> login(LoginRequest loginRequest) throws Exception {

        //아이디 유무 확인
        User user = userRepository.findByUserId(loginRequest.getUserId())
                .orElseThrow(() -> new Exception(ResponseMessage.USER_FIND_FAIL));

        CommonRes<LoginResponse> commonRes = new CommonRes<>();

        if(!passwordEncoder.encode(loginRequest.getPassword()).equals(user.getPassword())) {
            commonRes.setStatusCode(StatusCode.BAD_REQUEST);
            commonRes.setResponseMessage(ResponseMessage.PASSWORD_INCORRECT);
            return commonRes;
        }

        LoginResponse token = LoginResponse.builder().token("Test").build();

        commonRes.setStatusCode(StatusCode.OK);
        commonRes.setResponseMessage(ResponseMessage.DELETE_SUCCESS);
        commonRes.setData(token);

        return commonRes;
    }

    public JwtToken tokenLogin(LoginRequest loginRequest) throws Exception {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUserId(), loginRequest.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        JwtToken token = jwtTokenProvider.generateToken(authentication);

        return token;

    }
}
