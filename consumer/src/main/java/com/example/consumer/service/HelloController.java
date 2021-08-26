package com.example.consumer.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yousj
 * @since 2021/5/12
 */
@RestController
public class HelloController {

    @GetMapping(value = "/hello")
    public String hello() {
        return "Hello Sentinel";
    }

    @GetMapping(value = "/error")
    public String error() {
        return "服务器开小差啦......";
    }
}
