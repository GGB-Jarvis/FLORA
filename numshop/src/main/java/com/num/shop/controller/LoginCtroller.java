package com.num.shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.num.shop.entity.User;
import com.num.shop.service.UserService;
import com.num.shop.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LoginCtroller {
    @Autowired
    private UserService userService;

    @RequestMapping("login")
    public User login(User user){
//        user.setReserved("管理员");
//        user.setPassword(MD5Utils.getMd5(user.getPassword()));
//        List<User> users = userService.listAll(user);
//        if(users.size()>0){
//            return users.get(0);
//        }else{
//            return null;
//        }
        // 获取用户手机号
        User user1 = userService.getByPhone(user.getPhone());
        if(user1!=null){
            if(user1.getPassword().equals(MD5Utils.getMd5(user.getPassword()))){
                // 生成token
                user1.setToken(JWTUtils.createToken(user1.getPhone(), 3000));
                return user1;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }
    @RequestMapping("/goLogin")
    public User goLogin(String code){
        Map map = getReader(code);
        String oppen_id = (String)map.get("openid");
        User user = userService.getByOpenid(oppen_id);
        if(user==null){
            user = UserInfoUtils.getUser(map);
            System.out.println("user中的地址是:"+user);
            userService.insertIgnoreNull(user);
        }
        System.out.println("用户是:");
        System.out.println(user);
        return user;
    }

    @RequestMapping("/goLogin2")
    public User goLogin2(String code,Integer id){
        Map map = getReader(code);
        String oppen_id = (String)map.get("openid");
        User user = userService.getById(id);
        user.setOpenid(oppen_id);
        userService.updateIgnoreNull(user);
        return user;
    }

    public Map getReader(String code){
        String queryUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" +WXConstants.APPID+
                "&secret="+ WXConstants.APPSECRET+"&code="+code+"&grant_type=authorization_code";
        Map<String, String> map = new HashMap<String, String>();
        String openId = null;
        JSONObject jsonObject = WeiXinUtil.httpRequest(queryUrl,"GET",null);
        System.out.println("--------------"+jsonObject);
        openId = jsonObject.getString("openid");
        map.put("openid", openId);
        map.put("access_token",jsonObject.getString("access_token"));
        return map;
    }
}
