package com.restaurant.store.user.controller;

import com.restaurant.store.user.domain.User;
import com.restaurant.store.user.dto.UserJoin;
import com.restaurant.store.user.service.UserService;
import com.restaurant.store.common.dto.CommonRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/")
    public ResponseEntity<CommonRes<User>> createAccount(@RequestBody UserJoin userJoin) {

        CommonRes<User> result = userService.createAccount(userJoin);

        return ResponseEntity
                .ok()
                .body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonRes<Optional<User>>> findUser(@PathVariable Long id) {
        CommonRes<Optional<User>> user = userService.findUser(id);

        return ResponseEntity
                .ok()
                .body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonRes<String>> DeleteUser(@PathVariable Long id) {

        CommonRes<String> result = userService.deleteUser(id);
        return ResponseEntity
                .ok()
                .body(result);
    }

//    @PostMapping("/login")
//    public ResponseEntity<CommonRes<>>

}
