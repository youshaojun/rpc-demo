package com.example.consumer.service;

import com.example.api.CommonApi;
import com.example.rpc.annotation.MyReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yousj
 * @since 2021/1/14
 */
@RestController
@RequestMapping("/test")
public class TestRpc {

    @MyReference("apiImpl")
    private CommonApi commonApi;

    @RequestMapping("/get")
    public String get(String param) {
        return commonApi.execute(param);
    }

}
