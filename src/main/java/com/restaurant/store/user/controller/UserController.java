package com.restaurant.store.user.controller;

import com.restaurant.store.common.ResponseMessage;
import com.restaurant.store.user.domain.User;
import com.restaurant.store.user.dto.UserJoin;
import com.restaurant.store.user.dto.login.LoginRequest;
import com.restaurant.store.user.dto.login.LoginResponse;
import com.restaurant.store.user.service.UserService;
import com.restaurant.store.common.dto.CommonRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/")
    public ResponseEntity<CommonRes<User>> createUser(@RequestBody UserJoin userJoin) throws Exception {
        CommonRes<User> result = userService.createUser(userJoin);

        return ResponseEntity
                .ok()
                .body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonRes<User>> findUser(@PathVariable Long id) throws Exception {
        CommonRes<User> user = userService.findUser(id);

        return ResponseEntity
                .ok()
                .body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonRes<String>> deleteUser(@PathVariable Long id) throws Exception {
        CommonRes<String> result = userService.deleteUser(id);

        return ResponseEntity
                .ok()
                .body(result);
    }

    @PostMapping("/login")
    public ResponseEntity<CommonRes<LoginResponse>> login(@RequestBody LoginRequest loginRequest) throws Exception {
        CommonRes<LoginResponse> result = userService.login(loginRequest);

        return ResponseEntity
                .ok()
                .body(result);
    }
}
