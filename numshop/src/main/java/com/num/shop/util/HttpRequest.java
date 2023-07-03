package com.num.shop.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
//    public static String toUrl(String path, String method, String data) {
//        System.out.println(path);
//        System.out.println(data);
//        StringBuilder sb = new StringBuilder();
//        try {
//            URL url = new URL(path);
//            //打开和url之间的连接
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//
//            /**设置URLConnection的参数和普通的请求属性****start***/
//            //设置是否向httpUrlConnection输出，设置是否从httpUrlConnection读入，此外发送post请求必须设置这两个
//            conn.setDoOutput(true);
//            conn.setDoInput(true);
//            //GET和POST必须全大写
//            conn.setRequestMethod(method);
//            // 设置不用缓存
//            conn.setUseCaches(false);
//            conn.setRequestProperty("accept", "*/*");
//            conn.setRequestProperty("connection", "Keep-Alive");
//            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
//            // 设置文件字符集:
//            conn.setRequestProperty("Charset", "UTF-8");
//            byte[] tmp = new byte[0];
//            if ("POST".equals(method) && data != null && data.length() > 0) {
//                //转换为字节数组
//                tmp = data.getBytes();
//                // 设置文件长度
//                conn.setRequestProperty("Content-Length", String.valueOf(tmp.length));
//            }
//            // 设置文件类型:
//            conn.setRequestProperty("contentType", "application/json");
//            conn.setRequestProperty("content-Type", "application/json");
//            /**设置URLConnection的参数和普通的请求属性****end***/
//
//            /**GET方法请求*****start*/
//            /**
//             * 如果只是发送GET方式请求，使用connet方法建立和远程资源之间的实际连接即可；
//             * 如果发送POST方式的请求，需要获取URLConnection实例对应的输出流来发送请求参数。
//             * */
//            conn.connect();
//            /**GET方法请求*****end*/
//
//            /***POST方法请求****start*/
//            if ("POST".equals(method) && data != null && data.length() > 0) {
//                //获取URLConnection对象对应的输出流
//                OutputStream out = conn.getOutputStream();
//                //发送请求参数即数据
//                out.write(tmp);
//                out.flush();
//                out.close();
//            }
//            /***POST方法请求****end*/
//            // 请求返回的状态
//            if (conn.getResponseCode() == 200) {
//                System.out.println("连接成功");
//                //获取URLConnection对象对应的输入流
//                InputStream is = conn.getInputStream();
//                //构造一个字符流缓存
//                BufferedReader br = new BufferedReader(new InputStreamReader(is));
//                String str = "";
//                while ((str = br.readLine()) != null) {
//                    //解决中文乱码问题
//                    str = new String(str.getBytes(), "UTF-8");
//                    sb.append(str);
//                    System.out.println(str);
//                }
//                //关闭流
//                is.close();
//            }
//            //断开连接，最好写上，disconnect是在底层tcp socket链接空闲时才切断。如果正在被其他线程使用就不切断。
//            //固定多线程的话，如果不disconnect，链接会增多，直到收发不出信息。写上disconnect后正常一些。
//            conn.disconnect();
//            System.out.println("完整结束");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return sb.toString();
//    }
    /**
     * 安全证书管理
     */
    static class MyX509TrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
        }
        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
        }
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    /**
     * IP 域名校验
     */
    static class NullHostNameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String s, SSLSession sslSession) {
            return false;
        }
    }

    public static String httpsRequest(String url,String method,String data) {
        System.out.println("地址:" + url);
        System.out.println("参数:" + data);
        String resp = null;
        BufferedReader reader = null;
        InputStream in = null;
        HttpsURLConnection https = null;
        InputStreamReader inReader = null;
        StringBuffer buffer = new StringBuffer();
        try {
            //设置可以通过IP地址访问HTTPS请求
            HttpsURLConnection.setDefaultHostnameVerifier(new NullHostNameVerifier());
            //创建SSLContext对象，并使我们指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null,tm,new java.security.SecureRandom());
            //从上述SSLContxt对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            URL requestUrl = new URL(url);
            https = (HttpsURLConnection) requestUrl.openConnection();
            https.setInstanceFollowRedirects(false);
            https.setSSLSocketFactory(ssf);
            https.setDoInput(true);
            https.setDoOutput(true);
            https.setUseCaches(false);
            https.setRequestProperty("Content-Type","application/json");
            //设置请求方式
            https.setRequestMethod(method);
            https.connect();
            if(null != data){
                OutputStream out = https.getOutputStream();
                out.write(data.getBytes(StandardCharsets.UTF_8));
                //out.write(data.getBytes("utf-8"));
                out.close();
            }
            //将返回的输入流转换成字符串
            in = https.getInputStream();
            inReader = new InputStreamReader(in,StandardCharsets.UTF_8);
            reader = new BufferedReader(inReader);
            String str = null;
            while((str = reader.readLine()) != null){
                buffer.append(str);
            }
            resp = buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }  catch (KeyManagementException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            if(null != in){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                in = null;
            }
            if(null != reader){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null != inReader){
                try {
                    inReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null != https){
                https.disconnect();
            }
        }
        return resp;
    }
    public static String createUser(String password){
        Map map = new HashMap();
        map.put("password", password);
        String post = httpsRequest(Blockchain.createUserUrl,"POST", JSON.toJSONString(map));
        JSONObject obj = JSON.parseObject(post);
        String result = null;
        if("200".equals(obj.getString("code"))){
            result = obj.getJSONObject("result").getString("address");
        }
        return result;
    }
    public static String getPrivateKey(String password,String address){
        Map map = new HashMap();
        map.put("password", password);
        map.put("address", address);
        String post = httpsRequest(Blockchain.exportUser, "POST", JSON.toJSONString(map));
        //String post = httpsRequest(Blockchain.exportUser, "POST", "password="+password+"&address="+address);
        System.out.println("post:"+post);
        JSONObject obj = JSON.parseObject(post);
        String result = null;
        if("200".equals(obj.getString("code"))){
            result = obj.getJSONObject("result").getString("privateKey");
        }
        return result;
    }

    public static String[] createCollect(String name,String address,String password,int type){
        Map map = new HashMap();
        map.put("password", password);
        map.put("address", address);
        map.put("type", type);
        Map sub = new HashMap();
        sub.put("name", name);
        sub.put("symbol", "ALN");
        sub.put("tokenUrlPrefix", "http://artvery.txwsyun.cn:8080/");
        sub.put("contractUrl", "http://artvery.txwsyun.cn:8080/numshop/other/message/QmZJqDPxkwGLEwocovFMpmrAKkN7nmr95LqfhcumbM9urw");
        map.put("cMetadata", sub);
        String post = httpsRequest(Blockchain.createCollect, "POST", JSON.toJSONString(map));
        System.out.println("post:"+post);
        JSONObject obj = JSON.parseObject(post);
        String[] result = new String[2];
        if("200".equals(obj.getString("code"))){
            result[0] = obj.getJSONObject("result").getString("collectAddress");
            result[1] = obj.getJSONObject("result").getString("hash");
        }
        return result;
    }
    public static String createNft(String address,String password,String collectAddress,String file,String supply,
                                   String title,String description,String author,String authorDesc){
        Map map = new HashMap();
        map.put("password", password);
        map.put("address", address);
        map.put("collectAddress", collectAddress);
        map.put("file", file);
        map.put("supply", supply);
        Map sub = new HashMap();
        sub.put("title", title);
        sub.put("description", description);
        sub.put("author", author);
        sub.put("authorDesc", authorDesc);
        map.put("data", sub);
        String post = httpsRequest(Blockchain.createNft, "POST", JSON.toJSONString(map));
        System.out.println("post:"+post);
        JSONObject obj = JSON.parseObject(post);
        String result = null;
        if("200".equals(obj.getString("code"))){
            result = obj.getJSONObject("result").getString("tokenId");
        }
        return result;
    }
    public static void main(String[] args) {
        /*String[] result = createCollect("数字藏品",Blockchain.address,Blockchain.password, 10);
        System.out.println(Arrays.toString(result));*/
        String result = getPrivateKey(Blockchain.password, Blockchain.address);
        System.out.println(result);
    }
}
