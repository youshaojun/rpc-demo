package com.example.producer.impl;

import com.example.api.CommonApi;
import com.example.rpc.annotation.MyService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yousj
 * @since 2021/1/14
 */
@MyService("apiImpl")
@RestController
@RequestMapping("/apiImpl")
public class ApiImpl implements CommonApi {

    @Override
    @RequestMapping("/execute")
    public String execute(String param) {
        return "hello, " + param;
    }

}
