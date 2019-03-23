package cn.otote.springbootdemo.controller;

import cn.otote.springbootdemo.util.HttpUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/***
 * Created on 2019-03-23 21:19
 * Created by otote
 *
 ***/
@ResponseBody
@Controller
public class LoginController {

    static String appId = "wx0f75b52b51ef007b";
    static String appSecret = "13a861de1938eb4cc5c87a6bc759d2c9";

    static String url = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";

    @PostMapping("/login")
    public String login(@RequestBody JSONObject jsonObject) throws IOException {
        String code = (String) jsonObject.get("code");

        String finalUrl =getUrl().replace("JSCODE",code);
        String result = HttpUtils.get(finalUrl);
        System.out.println(result);
        return result;
    }

    public static String getUrl(){
        url = url.replace("APPID",appId);
        url = url.replace("SECRET",appSecret);
        return url;
    }


}
