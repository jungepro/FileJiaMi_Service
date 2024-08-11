package com.hui.tupian.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.util.RandomUtil;
import com.hui.tupian.util.CdAesUtil;

import java.io.File;
import java.util.List;

public class FileJiaMiUtil {


    public static void main(String[] args) throws Exception {
        String file = "C:\\Users\\24731\\Desktop\\1.txt";
        String file2 = "C:\\Users\\24731\\Desktop\\2.txt";
//        String s = FileUtil.readString(new File(file), "utf-8");
//        String key = RandomUtil.randomString(16);
//        System.out.println(key);
//        String jiami = CdAesUtil.Encrypt(s, key);
//        String jiemi = CdAesUtil.Decrypt(jiami, key);
//        System.out.println(s);
//        System.out.println(jiami);
//        System.out.println(jiemi);
//        FileUtil.writeString(jiami, new File(file2), "utf-8");


        String key2 = "jtmckgrr3kil522q";
        String s2 = FileUtil.readString(new File(file2), "utf-8");
        String jiemi2 = CdAesUtil.Decrypt(s2, key2);
        System.out.println(jiemi2);
    }
}
