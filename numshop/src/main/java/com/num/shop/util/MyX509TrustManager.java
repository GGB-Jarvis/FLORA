package com.num.shop.util;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * 编写程序，模拟https连接，获得token，对于https请求，我们需要一个证书信任管理器，这个管理器类需要自己定义，但需要实现X509TrustManager接口
 * 描述: 证书信任管理器（用于https请求）
 */
public class MyX509TrustManager implements X509TrustManager {

    // 检查客户端证书
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }
    // 检查服务器端证书
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }
    // 返回受信任的X509证书数组
    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}
