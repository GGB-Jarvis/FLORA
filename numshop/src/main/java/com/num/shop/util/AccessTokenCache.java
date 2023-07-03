package com.num.shop.util;

import java.util.HashMap;
import java.util.Map;

public class AccessTokenCache {
    //缓存accessToken的Map,map中包含accessToken,expiresIn和缓存的时间戳time
    private Map<String, String> map = new HashMap<String,String>();
    private static AccessTokenCache accessTokenCache = null;

    private AccessTokenCache() { }
    // 静态工厂方法
    public static AccessTokenCache getInstance() {
        if (accessTokenCache == null) {
            accessTokenCache = new AccessTokenCache();
        }
        return accessTokenCache;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    /**
     * 获取 accessToken expires_in
     * @param appid 第三方用户唯一凭证
     * @param appsecret 第三方用户唯一凭证密钥
     * @return
     */
    public Map<String,Object> getAcessTokenAndExpiresIn(String appid, String appsecret) {
        Map<String,Object> result = new HashMap<String,Object>();
        AccessTokenCache accessTokenCache = AccessTokenCache.getInstance();
        Map<String, String> map = accessTokenCache.getMap();
        String time = map.get("time");
        String accessToken = map.get("access_token");
        String expiresIn = map.get("expires_in");
        Long nowDate = System.currentTimeMillis();
        if (accessToken != null && time != null && expiresIn!=null) {
            //这里设置过期时间为微信规定的过期时间减去5分钟
            int outTime = (Integer.parseInt(expiresIn)-300) * 1000;
            if (nowDate - Long.parseLong(time) < outTime) {
                System.out.println("-----从缓存读取access_token：" + accessToken);
                //从缓存中拿数据为返回结果赋值
                result.put("access_token", accessToken);
                result.put("expires_in", expiresIn);
            }else{
                AccessToken info = WeiXinUtil.getAccessToken(appid, appsecret);//实际中这里要改为你自己调用微信接口去获取accessToken和expires_in
                //System.out.println("-----通过调用微信接口获取access_token：" + info.getAccessToken());
                //将信息放置缓存中
                map.put("time", nowDate + "");
                map.put("access_token", info.getAccessToken());
                map.put("expires_in", info.getExpiresIn()+"");
                //为返回结果赋值
                result.put("access_token", info.getAccessToken());
                result.put("expires_in", info.getExpiresIn());
            }
        } else {
            AccessToken info = WeiXinUtil.getAccessToken(appid, appsecret);//实际中这里要改为你自己调用微信接口去获取accessToken和expires_in
            //System.out.println("-----通过调用微信接口获取access_token：" + info.getAccessToken());
            //将信息放置缓存中
            map.put("time", nowDate + "");
            map.put("access_token", info.getAccessToken());
            map.put("expires_in", info.getExpiresIn()+"");
            //为返回结果赋值
            result.put("access_token", info.getAccessToken());
            result.put("expires_in", info.getExpiresIn());
        }
        return result;
    }
}