package com.example.registercenter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yousj
 * @since 2021/1/14
 */
@RestController
@RequestMapping("/register")
public class RegisterController {

    private static ConcurrentHashMap<String,String> urlMappingMap = new ConcurrentHashMap<>();

    @RequestMapping("/set")
    public void set(String k, String v) {
        urlMappingMap.put(k,v);
    }

    @RequestMapping("/get")
    public String get(String k) {
        return urlMappingMap.get(k);
    }
}
