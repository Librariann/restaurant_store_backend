package com.restaurant.store.Restaurant.controller;

import com.restaurant.store.Restaurant.domain.Restaurant;
import com.restaurant.store.Restaurant.dto.WriteReq;
import com.restaurant.store.Restaurant.dto.WriteRes;
import com.restaurant.store.Restaurant.service.WriteService;
import com.restaurant.store.common.ResponseMessage;
import com.restaurant.store.common.StatusCode;
import com.restaurant.store.common.dto.CommonRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WriteController {

    @Autowired
    WriteService writeService;

    @PostMapping("/write")
    public ResponseEntity<CommonRes> insertWrite(@RequestBody WriteReq writeReq) {
        CommonRes commonRes = new CommonRes();
        List<Restaurant> result = writeService.write(writeReq);

        commonRes.setStatusCode(StatusCode.OK);
        commonRes.setResponseMessage(ResponseMessage.WRITE_SUCCESS);

        return new ResponseEntity<>(commonRes, HttpStatus.OK);
    }
}
