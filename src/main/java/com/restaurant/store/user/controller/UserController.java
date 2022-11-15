package com.restaurant.store.user.controller;

import com.restaurant.store.user.domain.User;
import com.restaurant.store.user.dto.UserJoin;
import com.restaurant.store.user.dto.UserProfile;
import com.restaurant.store.user.service.UserService;
import com.restaurant.store.common.ResponseMessage;
import com.restaurant.store.common.StatusCode;
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
        CommonRes<User> commonRes = new CommonRes<User>();


        userService.createAccount(userJoin);

        commonRes.setStatusCode(StatusCode.OK);
        commonRes.setResponseMessage(ResponseMessage.JOIN_SUCCESS);

        return ResponseEntity
                .ok()
                .body(commonRes);
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
        CommonRes<String> commonRes = new CommonRes<>();

        String result = userService.deleteUser(id);
        commonRes.setStatusCode(ResponseMessage.DELETE_SUCCESS.equals(result) ? StatusCode.OK : StatusCode.NOT_FOUND);
        commonRes.setResponseMessage(result);
        return ResponseEntity
                .ok()
                .body(commonRes);
    }

//    @PostMapping("/login")
//    public ResponseEntity<CommonRes<>>

}
