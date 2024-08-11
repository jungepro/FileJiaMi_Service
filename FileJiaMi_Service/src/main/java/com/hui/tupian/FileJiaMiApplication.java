package com.hui.tupian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FileJiaMiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileJiaMiApplication.class, args);
        System.out.println("文件加密系统-------------启动成功");
    }

}
