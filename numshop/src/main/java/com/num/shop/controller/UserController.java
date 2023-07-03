package com.num.shop.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import com.num.shop.entity.Ranking;
import com.num.shop.util.IdCardVerification;
import com.num.shop.util.MD5Utils;
import com.num.shop.util.SendSms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.num.shop.entity.User;
import com.num.shop.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
    @RequestMapping("list")
    public List<User> listAll(User user) {
    	if(user.getPageIndex()!=null)
            user.setStart((user.getPageIndex()-1)*user.getPageSize());
        return userService.listAll(user);
    }

	@RequestMapping("total")
    public Integer total(User user) {

        return userService.total(user);
    }

    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    @RequestMapping("getById")
    public User getById(Integer id) {
        return userService.getById(id);
    }

    @RequestMapping("getByPhone")
    public User getByPhone(String phone) {
        return userService.getByPhone(phone);
    }


    @RequestMapping("sendCode")
    public Object sendCode(String phone){
        User user = userService.getByPhone(phone);
        Map map = new HashMap();
        if(user!=null){
            map.put("message", "该手机号已注册");
            return map;
        }
        String code = "";
        Random random = new Random();
        code += random.nextInt(10);
        code += random.nextInt(10);
        code += random.nextInt(10);
        code += random.nextInt(10);
        SendSms.sendNote(code,phone);

        System.out.println(
                "code = " + code
        );
        map.put("code", code);
        return map;
    }
    @RequestMapping("recommend")
    public int recommend(User user){
        return 0;
    }

    @RequestMapping("rankList")
    public List<Ranking> rankList(){
        //查找每个人的推荐数量
        List<Ranking> list = userService.findRanking();
        return list;
    }
    String code = "";
    @RequestMapping("sendCode2")
    public Object sendCode2(String phone){
        User user = userService.getByPhone(phone);
        Map map = new HashMap();
        if(user==null){
            map.put("message", "该手机号未注册");
            return map;
        }
        Random random = new Random();
        code += random.nextInt(10);
        code += random.nextInt(10);
        code += random.nextInt(10);
        code += random.nextInt(10);
        SendSms.sendNote(code,phone);

        map.put("code", code);
        return map;
    }
    /**
     * 新增，忽略null字段
     *
     * @param user 新增的记录
     * @return 返回影响行数
     */
    @RequestMapping("add")
    public int insert(User user) {
        if(user.getRealname()==null){
            Random random = new Random();
            user.setRealname("用户_"+random.nextInt(1000000));
        }
        user.setPassword(MD5Utils.getMd5(user.getPassword()));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        user.setRegistertime(simpleDateFormat.format(new Date()));
        return userService.insertIgnoreNull(user);
    }

    @RequestMapping("register")
    public int insert(User user,String code) {
        if(!code.equals(this.code)){
            if(user.getRealname()==null){
                Random random = new Random();
                user.setRealname("用户_"+random.nextInt(1000000));
            }
            user.setPassword(MD5Utils.getMd5(user.getPassword()));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            user.setRegistertime(simpleDateFormat.format(new Date()));
        }
        return userService.insertIgnoreNull(user);
    }


    // 实名认证
    @RequestMapping("verification")
    public Object verification(String name,String idcardno,Integer userid) {
        String result = IdCardVerification.verification(idcardno, name);
        if(result.equals("姓名和身份证号一致")){
            // 认证成功，保存用户信息
            User user = userService.getById(userid);
            user.setName(name);
            user.setAuthentication(2);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            user.setAuthenticationtime(simpleDateFormat.format(new Date()));
            user.setCardno(idcardno);
            userService.updateIgnoreNull(user);
        }
        Map map = new HashMap();
        map.put("message", result);
        return map;
    }

    @RequestMapping("login")
    public User login(User user) {
        String password = MD5Utils.getMd5(user.getPassword());
        user.setPassword(password);
        List<User> users = userService.listAll(user);
        User u = null;
        if(users.size()>0){
            u = users.get(0);
            u.setPassword(null);
        }
        return u;
    }

    /**
     * 修改，忽略null字段
     *
     * @param user 修改的记录
     * @return 返回影响行数
     */
    @RequestMapping("update")
    public int update(User user) {
        if(user.getPassword()!=null && !user.equals("")){
            String password = MD5Utils.getMd5(user.getPassword());
            user.setPassword(password);
        }
        return userService.updateIgnoreNull(user);
    }

    @RequestMapping("updatePass")
    public int updatePass(User user) {
        String password = MD5Utils.getMd5(user.getPassword());
        user.setPassword(password);
        return userService.updatePass(user);
    }
    /**
     * 删除记录
     *
     * @param user 待删除的记录
     * @return 返回影响行数
     */
    @RequestMapping("del")
    public int delete(User user) {
        return userService.delete(user);
    }
    
}