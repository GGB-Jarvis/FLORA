//
//package com.num.shop.controller;
//
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.SortedMap;
//import java.util.TreeMap;
//
//@RestController
//public class PayController {
//    @RequestMapping(value = "/wxpay", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public Map<String, Object> weixinPrePay(@RequestBody Pay pay, HttpServletRequest request) throws Exception  {
//        Map<String,Object> maps = new HashMap<String,Object>();
//        // 前端传值总是会多一个： 后台截取掉 这个看情况 可能你不存在这种情况的
//        pay.setOpenid(pay.getOpenid().substring(0, pay.getOpenid().length()-1));
//        System.out.println(pay.getOpenid());
//        // 前端传来的金额  后台需要将它变成 元
//        Double prices = pay.getPrice()*100;
//        Integer total_fee = prices.intValue();
//        System.out.println(prices+"----"+pay.getPrice()+"----"+total_fee);
//        // 生成唯一订单号
//        Integer out_trade_no = (int) (System.currentTimeMillis() / 1000+970516);
//        SortedMap<String, Object> parameterMap = new TreeMap<String, Object>();
//        parameterMap.put("appid", Parm.APPID);  //微信公众号的appid
//        parameterMap.put("mch_id", Parm.MCH_ID ,PayCommonUtil.MCH_ID);  //商户号
//        parameterMap.put("device_info", "WEB");
//        parameterMap.put("nonce_str", randomString);  // 随机字符串
//        parameterMap.put("body", "cheshi"); // 商品描述
//        parameterMap.put("out_trade_no", out_trade_no);  // 商户订单号（唯一） 我是用当前时间戳+随意数字生成的
//        parameterMap.put("fee_type", "CNY");  //货币类型 CNY：人民币
//        parameterMap.put("total_fee", total_fee);   // 总金额  分为单位
//        parameterMap.put("notify_url", wxnotify);   // 支付成功后的回调地址 填你
//        parameterMap.put("trade_type", "JSAPI");//JSAPI--JSAPI支付（或小程序支付）、NATIVE--Native支付、APP--app支付，MWEB--H5支付，不同trade_type决定了调起支付的方式
//        //trade_type为JSAPI是 openid为必填项
//        parameterMap.put("openid", ASEUtil.AESdecrypt(pay.getOpenid()));
//        // 加密格式  MD5 微信底层默认加密是HMAC-SHA256  具体你可以去看微信的支付底层代码(https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=11_1)
//        parameterMap.put("sign_type", "MD5");
//        // 生成支付签名 参数值的参数按照参数名ASCII码从小到大排序（字典序）
//        String sign = PayCommonUtil.createSign("UTF-8", parameterMap);
//        parameterMap.put("sign", sign);
//        System.out.println(parameterMap);
//        // 微信的统一下单接口 需要将集合中的参数 拼接成<xml></xml> 格式
//        String requestXML = PayCommonUtil.getRequestXml(parameterMap);
//        System.out.println(requestXML);
//        // 调用微信的统一下单接口
//        String result = PayCommonUtil.httpsRequest(
//                "https://api.mch.weixin.qq.com/pay/unifiedorder", "POST",
//                requestXML);
//        // 返回的数据是 xml 格式的数据
//        System.out.println(result);
//        Map<String, Object> map = null;
//        try {
//            // 微信统一下单接口返回的数据 也是xml 格式的  所以需要把它转成map 集合，因为我们只需要当中的一个统一下单编号 prepay_id
//            map = PayCommonUtil.doXMLParse(result);
//            // 时间戳 需要转换成秒
//            map.put("timestamp", System.currentTimeMillis() / 1000);
//            // 二次签名 微信支付签名需要签名两次，第一次是用来获取统一下单的订单号
//            if ("SUCCESS".equals(map.get("result_code"))) {
//                SortedMap<String, Object> map2 = new TreeMap<String, Object>();
//                // 第二次支付签名的 参数 需要将 第一次签名中的 订单号带入签名中
//                map2.put("appId", map.get("appid"));
//                map2.put("timeStamp", map.get("timestamp"));
//                //这边的随机字符串必须是第一次生成sign时，微信返回的随机字符串，不然支付时会报签名错误
//                map2.put("nonceStr", map.get("nonce_str"));
//                // 订单详情扩展字符串 统一下单接口返回的prepay_id参数值，提交格式如：prepay_id=***
//                map2.put("package", "prepay_id=" + map.get("prepay_id"));
//                // 签名方式 要和第一次签名方式一直
//                map2.put("signType", "MD5");
//                // 支付签名
//                String sign2 = PayCommonUtil.createSign("UTF-8", map2);
//                // 将你前端需要的数据 放在集合中
//                Map<String,Object> payInfo = new HashMap<String,Object>();
//                payInfo.put("appId", map.get("appid"));
//                payInfo.put("timeStamp", map.get("timestamp"));
//                payInfo.put("nonceStr", map.get("nonce_str"));
//                payInfo.put("prepay_id",map.get("prepay_id"));
//                payInfo.put("signType", "MD5");
//                payInfo.put("paySign", sign2);
//                // 返回给前端的集合数据
//                maps.put("status", 200);
//                maps.put("msg", "统一下单成功!");
//                maps.put("data", payInfo);
//            }else {
//                maps.put("status", 500);
//                maps.put("msg", "服务器忙，请稍后再试");
//                maps.put("data", null);
//            }
//        } catch (JDOMException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return maps;
//    }
//
//}
//
