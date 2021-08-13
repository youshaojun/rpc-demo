package com.example.consumer.service;

import com.example.api.CommonApi;
import com.example.rpc.annotation.MyReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yousj
 * @since 2021/1/14
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestRpc {

    @MyReference("apiImpl")
    private CommonApi commonApi;

    @RequestMapping("/get")
    public String get(String param) {
    	log.info("=============>>>consumer被调用, param: {}<<<==============", param);
        return commonApi.execute(param);
    }

}
