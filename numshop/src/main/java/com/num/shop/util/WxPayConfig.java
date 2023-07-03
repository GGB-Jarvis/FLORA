package com.num.shop.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class WxPayConfig {
    // appid
    public static String APP_ID = WXConstants.APPID;
    // JSAPI接口中获取openid，审核后在公众平台开启开发模式后可查看
    public static String APP_SECRET = WXConstants.APPSECRET;
    // 受理商ID，身份标识
    public static String MCH_ID = "1625506813";
    // 商户支付密钥Key，装完整数后，配置得到。
    public static String KEY = "v4Cuxxab42dBzqg54ApCAdtDDj0BDEXX";
    // 异步回调地址
    public static String NOTIFY_URL = "http://artvery.txwsyun.cn:8080/numshop/orders/notify";
    // 字符编码
    public static String CHARTSET = "UTF-8";
    // 加密方式
    public static String SIGN_TYPE = "MD5";
    /*// redirect_uri，微信授权重定向地址
    public static String REDIRECT_URI;

    static {
        try {
            REDIRECT_URI = URLEncoder.encode("https://ronglincaishi.com/pay", CHARTSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }*/
}
