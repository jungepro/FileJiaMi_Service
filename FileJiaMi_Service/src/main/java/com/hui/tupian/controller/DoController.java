package com.hui.tupian.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import com.hui.tupian.util.BASE64;
import com.hui.tupian.util.CdAesUtil;
import com.hui.tupian.util.DESUtil;
import com.hui.tupian.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@Slf4j
@RestController
@RequestMapping("/do")
public class DoController {

    public static String rootPath = "D:/filejiami/";

    @GetMapping("jiami")
    public Result<?> jiami(String url, Integer type) throws Exception {
        File fileUrl = new File(rootPath + url);
        if (!fileUrl.exists()) {
            return Result.error("file not exist");
        }
        if (type == 0) {
            //random
            type = RandomUtil.randomInt(1, 4);
        }
        String jiamiFile = doJiaMi(fileUrl, type);

        String suanfa = "";
        switch (type) {
            case 1:
                //aes
                suanfa = "AES算法";
                break;
            case 2:
                //des
                suanfa = "DES算法";

                break;
            case 3:
                //base64
                suanfa = "BASE64算法";
                break;
        }
        JSONObject obj = new JSONObject();
        obj.put("suanfa", suanfa);
        obj.put("jiamiFile", jiamiFile);
        return Result.OK(obj);
    }

    @GetMapping("jiemi")
    public Result<?> jiemi(String url, Integer type) throws Exception {
        try {
            File fileUrl = new File(rootPath + url);
            if (!fileUrl.exists()) {
                return Result.error("file not exist");
            }
            return Result.OK(doJieMi(fileUrl, type));
        } catch (Exception e) {
            return Result.error("解密失败！");
        }
    }

    private String doJieMi(File fileUrl, Integer type) throws Exception {
        String jiemiFile = IdUtil.randomUUID() + ".txt";
        String yuanTxt = FileUtil.readString(fileUrl, "utf-8");
        String jieTxt = "";
        switch (type) {
            case 1:
                //aes
                jieTxt = CdAesUtil.Decrypt(yuanTxt, CdAesUtil.cKey);
                break;
            case 2:
                //des
                jieTxt = DESUtil.decrypt("12345678", yuanTxt);
                break;
            case 3:
                //base64
                jieTxt = BASE64.decryptBASE64(yuanTxt);
                break;
        }
        FileUtil.writeString(jieTxt, new File(rootPath + jiemiFile), "utf-8");
        return jiemiFile;
    }


    private String doJiaMi(File fileUrl, Integer type) throws Exception {
        String jiamiFile = IdUtil.randomUUID() + ".txt";
        String yuanTxt = FileUtil.readString(fileUrl, "utf-8");
        String jiaTxt = "";
        switch (type) {
            case 1:
                //aes
                jiaTxt = CdAesUtil.Encrypt(yuanTxt, CdAesUtil.cKey);
                break;
            case 2:
                //des
                jiaTxt = DESUtil.encrypt("12345678", yuanTxt);
                break;
            case 3:
                //base64
                jiaTxt = BASE64.encryptBASE64(yuanTxt);
                break;
        }
        FileUtil.writeString(jiaTxt, new File(rootPath + jiamiFile), "utf-8");
        return jiamiFile;
    }


}
