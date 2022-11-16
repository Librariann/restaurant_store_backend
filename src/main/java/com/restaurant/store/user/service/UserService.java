package com.restaurant.store.user.service;

import com.restaurant.store.common.StatusCode;
import com.restaurant.store.common.dto.CommonRes;
import com.restaurant.store.user.domain.User;
import com.restaurant.store.user.dto.UserJoin;
import com.restaurant.store.user.repository.UserRepository;
import com.restaurant.store.common.ResponseMessage;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.restaurant.store.common.Constant.SECRET_KEY;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public CommonRes<User> createAccount(UserJoin userJoin) {
            Optional<User> user = userRepository.findByUserId(userJoin.getUserId());
            CommonRes<User> commonRes = new CommonRes<User>();

            //이미 생성된 아이디가 존재 할 때
            if (user.isPresent()) {
                commonRes.setStatusCode(StatusCode.BAD_REQUEST);
                commonRes.setResponseMessage(ResponseMessage.USER_OVERLAP);
                return commonRes;
            }

            User saveUser = userJoin.toEntity();
            saveUser.setPassword(passwordEncoder.encode(saveUser.getPassword()));
            userRepository.save(saveUser);

            commonRes.setStatusCode(StatusCode.OK);
            commonRes.setResponseMessage(ResponseMessage.JOIN_SUCCESS);

            return commonRes;
    }


    public CommonRes<Optional<User>> findUser(Long id) {
        Optional<User> user = userRepository.findById(id);

        CommonRes<Optional<User>> commonRes = new CommonRes<>();

        if(user.isEmpty()) {
            commonRes.setStatusCode(StatusCode.NOT_FOUND);
            commonRes.setResponseMessage(ResponseMessage.USER_FIND_FAIL);
            return commonRes;
        }

        commonRes.setStatusCode(StatusCode.OK);
        commonRes.setResponseMessage(ResponseMessage.USER_FIND_SUCCESS);
        commonRes.setData(user);

        return commonRes;
    }

    public String deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            return ResponseMessage.DELETE_FAIL;
        }
        userRepository.deleteById(id);
        return ResponseMessage.DELETE_SUCCESS;
    }

    public List<User> findAllUser() {
        return userRepository.findAll();
    }
}
