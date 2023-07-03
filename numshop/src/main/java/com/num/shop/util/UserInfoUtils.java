package com.num.shop.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.num.shop.entity.User;
import org.apache.commons.codec.binary.Base64;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class UserInfoUtils {
    public static User getUser(Map m){
        String oppen_id = (String) m.get("openid");
        String accessToken = (String) m.get("access_token");
        User u = new User();
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token="+accessToken+"&openid="+oppen_id+"&lang=zh_CN";
        try {
            //设置链接
            URL urlGet = new URL(url);
            //启动链接
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
            //设置链接参数与要求
            http.setRequestMethod("GET"); // 必须是get方式请求
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            //            链接
            http.connect();
            //获取返回值json字节流
            InputStream is = http.getInputStream();
            int size = is.available();
            byte[] jsonBytes = new byte[size];
            is.read(jsonBytes);
            //转化成字符串
            String message = new String(jsonBytes, "UTF-8");
            //转化成json对象然后返回accessToken属性的值
            JSONObject demoJson = JSON.parseObject(message);
            u.setOpenid(oppen_id);
            u.setImageurl(demoJson.getString("headimgurl"));
            String nickname = demoJson.getString("nickname");
            u.setRealname(nickname);
            u.setRegistertime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }
}
