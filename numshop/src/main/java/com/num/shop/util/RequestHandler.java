package com.num.shop.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

public class RequestHandler {

    /** ???url??? */
    private String gateUrl;

    /** ??? */
    private String key;

    /** ???????? */
    private SortedMap parameters;

    /** debug??? */
    private String debugInfo;

    protected HttpServletRequest request;

    protected HttpServletResponse response;

    /**
     * ??????
     * @param request
     * @param response
     */
    public RequestHandler(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;

        this.gateUrl = "https://gw.tenpay.com/gateway/pay.htm";
        this.key = "";
        this.parameters = new TreeMap();
        this.debugInfo = "";
    }

    /**
     *?????????
     */
    public void init() {
        //nothing to do
    }

    /**
     *????????,????????
     */
    public String getGateUrl() {
        return gateUrl;
    }

    /**
     *?????????,????????
     */
    public void setGateUrl(String gateUrl) {
        this.gateUrl = gateUrl;
    }

    /**
     *??????
     */
    public String getKey() {
        return key;
    }

    /**
     *???????
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * ????????
     * @param parameter ???????
     * @return String
     */
    public String getParameter(String parameter) {
        String s = (String)this.parameters.get(parameter);
        return (null == s) ? "" : s;
    }

    /**
     * ???��????
     * @param parameter ???????
     * @param parameterValue ?????
     */
    public void setParameter(String parameter, String parameterValue) {
        String v = "";
        if(null != parameterValue) {
            v = parameterValue.trim();
        }
        this.parameters.put(parameter, v);
    }

    /**
     * ???????��????
     * @return SortedMap
     */
    public SortedMap getAllParameters() {
        return this.parameters;
    }

    /**
     *???debug???
     */
    public String getDebugInfo() {
        return debugInfo;
    }

    /**
     * ?????????????URL
     * @return String
     * @throws UnsupportedEncodingException
     */
    public String getRequestURL() throws UnsupportedEncodingException {

        this.createSign();

        StringBuffer sb = new StringBuffer();
        Set es = this.parameters.entrySet();
        Iterator it = es.iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            String v = (String)entry.getValue();

            if(!"spbill_create_ip".equals(k)) {
                sb.append(k + "=" + URLEncoder.encode(v, WxPayConfig.CHARTSET) + "&");
            } else {
                sb.append(k + "=" + v.replace("\\.", "%2E") + "&");
            }
        }

        //?????????&
        String reqPars = sb.substring(0, sb.lastIndexOf("&"));

        return this.getGateUrl() + "?" + reqPars;

    }

    public void doSend() throws UnsupportedEncodingException, IOException {
        this.response.sendRedirect(this.getRequestURL());
    }

    /**
     * ????md5??,??????:?????????a-z????,????????????��????
     */
    protected void createSign() {
        StringBuffer sb = new StringBuffer();
        Set es = this.parameters.entrySet();
        Iterator it = es.iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            String v = (String)entry.getValue();
            if(null != v && !"".equals(v)
                    && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + WxPayConfig.KEY);

        String sign = MD5Util.MD5Encode(sb.toString(), WxPayConfig.CHARTSET).toUpperCase();

        this.setParameter("sign", sign);

        //debug???
        this.setDebugInfo(sb.toString() + " => sign:" + sign);

    }

    /**
     *????debug???
     */
    protected void setDebugInfo(String debugInfo) {
        this.debugInfo = debugInfo;
    }

    protected HttpServletRequest getHttpServletRequest() {
        return this.request;
    }

    protected HttpServletResponse getHttpServletResponse() {
        return this.response;
    }

    /**
     * �Զ��庯�����ٷ�û��
     * @param return_code
     * @param return_msg
     * @return
     */
    public static String setXML(String return_code, String return_msg) {
        return "<xml><return_code><![CDATA[" + return_code + "]]></return_code><return_msg><![CDATA[" + return_msg
                + "]]></return_msg></xml>";
    }

    /**
     * �Զ��庯�����ڹٷ��ĵ���û��
     * @return
     * @throws UnsupportedEncodingException
     */
    public String getRequestXml() throws UnsupportedEncodingException {
        this.createSign();

        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set es = this.parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k)) {
                sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
            } else {
                sb.append("<" + k + ">" + v + "</" + k + ">");
            }
        }

        sb.append("</xml>");
        return sb.toString();
    }
}
