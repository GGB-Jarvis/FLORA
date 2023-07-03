package com.num.shop.controller;

import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

import com.num.shop.entity.Details;
import com.num.shop.entity.Product;
import com.num.shop.entity.User;
import com.num.shop.service.DetailsService;
import com.num.shop.service.ProductService;
import com.num.shop.service.UserService;
import com.num.shop.util.*;
import com.num.shop.wx.WXPayReport;
import com.num.shop.wx.WXPayUtil;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.num.shop.entity.Orders;
import com.num.shop.service.OrdersService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
    @RequestMapping("list")
    public List<Orders> listAll(Orders orders) {
        if(orders.getPageIndex()!=null)
            orders.setStart((orders.getPageIndex()-1)*orders.getPageSize());
        return ordersService.listAll(orders);
    }

    @RequestMapping("total")
    public Integer total(Orders orders) {

        return ordersService.total(orders);
    }

    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    @RequestMapping("getById")
    public Orders getById(Integer id) {
        return ordersService.getById(id);
    }

    /**
     * 新增，忽略null字段
     *
     * @param orders 新增的记录
     * @return 返回影响行数
     */
    @RequestMapping("add")
    public int add(Orders orders) {
        return ordersService.insertIgnoreNull(orders);
    }

    @Autowired
    private ProductService productService;

    @Autowired
    private DetailsService detailsService;

    @RequestMapping("insert1")
    public void insert1(@RequestParam List<Map<String,String>> productList, Integer userid) {
//        System.out.println(productList);
//        Orders orders = new Orders();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        orders.setCreatetime(simpleDateFormat.format(new Date()));
//        orders.setStatus("待支付");
//        orders.setUserid(userid);
//        ordersService.insert(orders);
//        if(productList.size()>0){
//            for (int i = 0; i < productList.size(); i++) {
//                String pid = productList.get(i).get("pid");
//                String count = productList.get(i).get("count");
//                Product product = productService.getById(Integer.valueOf(pid));
//                if(product.getCount()<Integer.valueOf(count)){
//                    return 0;
//                }
//                System.out.println(productList.get(i).toString());
//                for (Integer j = 0; j < Integer.valueOf(count); j++) {
//                    Details details = new Details();
//                    details.setCount(1);
//                    details.setOrderid(orders.getId());
//                    details.setProductid(Integer.valueOf(pid));
//                    details.setProductno("NO"+new Date().getTime()+""+(int)Math.random()*100);
//                    System.out.println(details.toString());
//                    detailsService.insert(details);
//                }
//            }
//        }
//        return orders.getId();
    }
    @RequestMapping("insert")
    public int insert(Integer id,Integer count,Integer userid) {
        Product product = productService.getById(id);
        if(product.getCount()<count){
            return 0;
        }
        Orders orders = new Orders();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        orders.setCreatetime(simpleDateFormat.format(new Date()));
        orders.setStatus("待支付");
        orders.setUserid(userid);
        ordersService.insert(orders);

        for (Integer i = 0; i < count; i++) {
            Details details = new Details();
            details.setCount(1);
            details.setOrderid(orders.getId());
//            details.setPrice(new BigDecimal(product.getReserved()));
            details.setProductid(id);
            details.setProductno("NO"+new Date().getTime()+""+(int)Math.random()*100);
            detailsService.insert(details);
        }
        return orders.getId();
    }

    /**
     * 修改，忽略null字段
     *
     * @param orders 修改的记录
     * @return 返回影响行数
     */
    @RequestMapping("update")
    public int update(Orders orders) {
        if("已完成".equals(orders.getStatus())){
            orders.setPaytime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            orders.setPaytype("无需支付");
        }
        return ordersService.updateIgnoreNull(orders);
    }

    /**
     * 删除记录
     *
     * @param orders 待删除的记录
     * @return 返回影响行数
     */
    @RequestMapping("del")
    public int delete(Orders orders) {
        return ordersService.delete(orders);
    }

    @Autowired
    private UserService userService;
    //统一下单
    @RequestMapping("pay")
    public Map<Object, Object> pay(Integer id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Orders orders = ordersService.getById(id);
        User user = userService.getById(orders.getUserid());
        String openid = user.getOpenid();
        String noncestr = Sha1Util.getNonceStr();
        String timestamp = Sha1Util.getTimeStamp();
        String out_trade_no = timestamp + orders.getId();
        orders.setReserved(out_trade_no);
        Details d = new Details();
        d.setOrderid(id);
        List<Details> details = detailsService.listAll(d);
        BigDecimal priceBD = new BigDecimal("0");
        for (Details detail : details) {
            priceBD = priceBD.add(detail.getPrice().multiply(new BigDecimal(detail.getCount() + "")));
        }
        String order_price = priceBD.multiply(new BigDecimal("100")).setScale(0,BigDecimal.ROUND_HALF_UP).toString();
        String body = "FLORA数字商城";
        RequestHandler reqHandler = new RequestHandler(request, response);
        // 初始化 RequestHandler 类可以在微信的文档中找到.还有相关工具类
        reqHandler.init();
        // 执行统一下单接口 获得预支付id，一下是必填参数
        // 微信分配的公众账号ID（企业号corpid即为此appId）
        reqHandler.setParameter("appid", WxPayConfig.APP_ID);
        // 微信支付分配的商户号
        reqHandler.setParameter("mch_id", WxPayConfig.MCH_ID);
        // 终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB"
        reqHandler.setParameter("device_info", "WEB");
        // 随机字符串，不长于32位。推荐随机数生成算法
        reqHandler.setParameter("nonce_str", noncestr);
        // 商品描述
        reqHandler.setParameter("body", body);
        // 商家订单号
        reqHandler.setParameter("out_trade_no", out_trade_no);
        // 商品金额,以分为单位
        reqHandler.setParameter("total_fee", order_price);
        // APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
        reqHandler.setParameter("spbill_create_ip", "101.42.152.20");
        // 下面的notify_url是用户支付成功后为微信调用的action 异步回调.
        reqHandler.setParameter("notify_url", WxPayConfig.NOTIFY_URL);
        // 交易类型,取值如下：JSAPI，NATIVE，APP，详细说明见参数规定
        reqHandler.setParameter("trade_type", "JSAPI");
        // ------------需要进行用户授权获取用户openid-------------
        reqHandler.setParameter("openid", openid); // 这个必填.
        // 生成签名，并且转为xml
        String requestXml = reqHandler.getRequestXml();
        System.out.println("requestXml:" + requestXml);

        // 得到的预支付id
        String prepay_id = unifiedorder(requestXml);
        SortedMap<String, String> params = new TreeMap<String, String>();
        params.put("appId", WxPayConfig.APP_ID);
        params.put("timeStamp", timestamp);
        params.put("nonceStr", noncestr);
        params.put("package", "prepay_id=" + prepay_id);
        params.put("signType", "MD5");

        // 生成支付签名,这个签名 给 微信支付的调用使用
        SortedMap<Object,Object> signMap = new TreeMap<Object,Object>();
        signMap.put("appId", WxPayConfig.APP_ID);
        signMap.put("timeStamp", timestamp);
        signMap.put("nonceStr", noncestr);
        signMap.put("package", "prepay_id=" + prepay_id);
        signMap.put("signType", "MD5");
        // 微信支付签名
        String paySign = MD5Util.createSign(signMap, WxPayConfig.KEY);
        signMap.put("paySign", paySign);
        ordersService.updateIgnoreNull(orders);
        return signMap;
    }
    public  String unifiedorder(String requestXml) throws Exception{
        // 统一下单接口提交 xml格式
        URL orderUrl = new URL("https://api.mch.weixin.qq.com/pay/unifiedorder");
        HttpURLConnection conn = (HttpURLConnection) orderUrl.openConnection();
        conn.setConnectTimeout(30000); // 设置连接主机超时（单位：毫秒)
        conn.setReadTimeout(30000); // 设置从主机读取数据超时（单位：毫秒)
        conn.setDoOutput(true); // post请求参数要放在http正文内，顾设置成true，默认是false
        conn.setDoInput(true); // 设置是否从httpUrlConnection读入，默认情况下是true
        conn.setUseCaches(false); // Post 请求不能使用缓存
        // 设定传送的内容类型是可序列化的java对象(如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestMethod("POST");// 设定请求的方法为"POST"，默认是GET
        conn.setRequestProperty("Content-Length", requestXml.length() + "");
        OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), WxPayConfig.CHARTSET);
        out.write(requestXml.toString());
        out.flush();
        out.close();
        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            return null;
        }
        // 获取响应内容体
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), WxPayConfig.CHARTSET));
        String line = "";
        StringBuffer strBuf = new StringBuffer();
        while ((line = in.readLine()) != null) {
            strBuf.append(line).append("\n");
        }
        in.close();
        System.out.println("result=========返回的xml=============" + strBuf.toString());
        Map<String, String> orderMap = XMLUtil.doXMLParse(strBuf.toString());
        System.out.println("orderMap===========================" + orderMap);

        // 获取
        String prepay_id = orderMap.get("prepay_id");
        return prepay_id;
    }
    @RequestMapping("notify")
    public String notify2(HttpServletRequest request, HttpServletResponse response){
        String out_trade_no = null;
        try {
            InputStream inStream = request.getInputStream();
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            outSteam.close();
            inStream.close();
            String resultStr = new String(outSteam.toByteArray(), WxPayConfig.CHARTSET);
            Map<String, String> resultMap = XMLUtil.doXMLParse(resultStr);

            System.out.println("微信回调结果:" + resultMap.toString());

            String result_code = resultMap.get("result_code");
            String is_subscribe = resultMap.get("is_subscribe");
            out_trade_no = resultMap.get("out_trade_no");
            String transaction_id = resultMap.get("transaction_id");
            String sign = resultMap.get("sign");
            String time_end = resultMap.get("time_end");
            String bank_type = resultMap.get("bank_type");
            String return_code = resultMap.get("return_code");
            // 签名验证
            //GenericValue userLogin = delegator.findOne("UserLogin", UtilMisc.toMap("userLoginId", "admin"), false);
            if (return_code.equals("SUCCESS")) {
                // 此处就是你的逻辑代码
                Orders orders = new Orders();
                orders.setReserved(out_trade_no);
                List<Orders> ordersList = ordersService.listAll(orders);
                if(ordersList.size()>0){
                    orders = ordersList.get(0);
                    orders.setPaytype("微信支付");
                    orders.setStatus("已完成");
                    orders.setPaytime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                    ordersService.updateIgnoreNull(orders);
                    //查询购买的商品
                    Details details = new Details();
                    details.setOrderid(orders.getId());
                    List<Details> detailsList = detailsService.listAll(details);
                    for (Details d : detailsList) {
                        Product product = productService.getById(d.getProductid());
                        product.setCount(product.getCount() - d.getCount());
                        productService.updateIgnoreNull(product);
                    }
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RequestHandler.setXML("SUCCESS","OK");
    }
    @RequestMapping("findResult")
    public int findResult(Integer id) throws Exception {
        Orders orders = ordersService.getById(id);
        String noncestr = Sha1Util.getNonceStr();
        String mch_id = WxPayConfig.MCH_ID;
        String out_trade_no = orders.getReserved();

        SortedMap<String,String> signMap = new TreeMap<String,String>();
        signMap.put("appid", WxPayConfig.APP_ID);
        signMap.put("nonce_str", noncestr);
        signMap.put("mch_id", mch_id);
        signMap.put("out_trade_no", out_trade_no);
        // 微信支付签名
        String paySign =  WXPayUtil.generateSignature(signMap, WxPayConfig.KEY);
        signMap.put("sign_type", "MD5");
        signMap.put("sign", paySign);
        String xml = WXPayUtil.mapToXml(signMap);
        String unifiedorder_url = "https://api.mch.weixin.qq.com/pay/orderquery";

        String xmlStr = WeiXinUtil.httpRequest2(unifiedorder_url, "get", xml);
        System.out.println("查询回调结果是:"+xmlStr);
        if (xmlStr.indexOf("SUCCESS") != -1) {
            orders.setPaytype("微信支付");
            orders.setStatus("已完成");
            orders.setPaytime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            ordersService.updateIgnoreNull(orders);
        }
        return 1;
    }
}