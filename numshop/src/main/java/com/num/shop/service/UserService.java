package com.num.shop.service;

import com.num.shop.entity.Ranking;
import com.num.shop.entity.User;
import com.num.shop.mapper.UserMapper;
import com.num.shop.util.HttpRequest;
import com.num.shop.util.MD5Utils;
import com.tencentcloudapi.hcm.v20181106.models.EvaluationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.nio.cs.ext.MacRoman;

import java.util.List;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
    public List<User> listAll(User user) {
    	return userMapper.listAll(user);
    }

	public Integer total(User user) {
        return userMapper.total(user);
    }
    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    public User getById(Integer id) {
    	return userMapper.getById(id);
    }

    public List<Ranking> findRanking(){
        return userMapper.findRanking();
    }

    public User getByPhone(String phone) {
        return userMapper.getByPhone(phone);
    }
    /**
     * 新增，插入所有字段
     *
     * @param user 新增的记录
     * @return 返回影响行数
     */
    public int insert(User user) {
    	return userMapper.insert(user);
    }
	
    /**
     * 新增，忽略null字段
     *
     * @param user 新增的记录
     * @return 返回影响行数
     */
    public int insertIgnoreNull(User user) {
        if(user.getPassword()==null && user.getRealname()==null){
            return 0;
        }
        user.setBlockpassword(MD5Utils.getMd5(System.currentTimeMillis() + ""));
        String result = HttpRequest.createUser(user.getBlockpassword());
        user.setAddress(result);
        String privateKey = HttpRequest.getPrivateKey(user.getBlockpassword(), user.getAddress());
        user.setPrivateKey(privateKey);
        String tCode = "";
        Random random = new Random();
        User j = new User();
        while(true){
            tCode = random.nextInt(1000000)+"";
            if(tCode.length()<6){
                int c = 6 - tCode.length();
                for (int i = 0; i < c; i++) {
                    tCode = "0" + tCode;
                }
            }
            List<User> users = userMapper.listAll(j);
            if(users.size()==0){
                break;
            }
        }
    	return userMapper.insertIgnoreNull(user);
    }
	
    /**
     * 修改，修改所有字段
     *
     * @param user 修改的记录
     * @return 返回影响行数
     */
    public int update(User user) {
    	return userMapper.update(user);
    }
	
    /**
     * 修改，忽略null字段
     *
     * @param user 修改的记录
     * @return 返回影响行数
     */
    public int updateIgnoreNull(User user) {
    	return userMapper.updateIgnoreNull(user);
    }

    public User getByOpenid(String openid){
        return userMapper.getByOpenId(openid);
    }


    public int updatePass(User user) {
        return userMapper.updatePass(user);
    }
    /**
     * 删除记录
     *
     * @param user 待删除的记录
     * @return 返回影响行数
     */
    public int delete(User user) {
    	return userMapper.delete(user);
    }
	
}