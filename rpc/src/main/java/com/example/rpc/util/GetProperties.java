package com.example.rpc.util;

import cn.hutool.core.io.file.FileReader;

import java.io.File;
import java.util.List;

/**
 * @author yousj
 * @since 2021/1/15
 */
public class GetProperties {

    public static String getProperties(String key) {
        FileReader fileReader = FileReader.create(new File(GetProperties.class.getClassLoader().getResource("application.yml").getPath()));
        List<String> list = fileReader.readLines();
        for (String s : list) {
            if (s.contains(key + ":")) {
                return s.split(key + ":")[1].trim();
            }
        }
        return null;
    }


}
