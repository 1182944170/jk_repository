package com.rpframework.module.common.pay.wxpay.util;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.rpframework.module.common.pay.wxpay.config.WXpayConfig;

/**
 * 微信支付接口的xml处理工具
 * @author eagle
 * @since 2015年7月8日
 * @version 1.1.0
 */
public class WXpayXmlUtils {

    /**
     * 将Map转为xml字符串
     * @param map
     * @return
     */
    public static String map2xml(Map<String, String> map) {
        StringBuilder xml = new StringBuilder();
        xml.append("<xml>\n");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            xml.append("<").append(entry.getKey()).append("><![CDATA[").append(entry.getValue()).append("]]></")
                    .append(entry.getKey()).append(">\n");
        }
        xml.append("</xml>");

        String ret = xml.toString();
        return ret;
    }

    /**
     * 将微信返回的xml字符串转为Map<String,String>
     * @param map
     * @return
     */
    public static Map<String, String> xml2map(String xml) {
        Map<String, String> ret = new HashMap<String, String>();

        // 这里用Dom的方式解析回包的最主要目的是防止API新增回包字段
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document document = null;
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(new ByteArrayInputStream(xml.getBytes(WXpayConfig.DEFAULT_RESP_CHAR_SET)));
            document.normalize();
        } catch (Exception e) {
            return ret;
        }

        // 获取到document里面的全部结点
        NodeList allNodes = document.getFirstChild().getChildNodes();
        Node node;
        int i = 0;
        while (i < allNodes.getLength()) {
            node = allNodes.item(i);
            if (node instanceof Element) {
                ret.put(node.getNodeName(), node.getTextContent());
            }
            i++;
        }

        return ret;
    }
    
    public static void main(String[] args) {
        String testXML = "<xml><return_code><![CDATA[SUCCESS]]></return_code>" +
                "<return_msg><![CDATA[OK]]></return_msg>" +
                "<appid><![CDATA[wx21922ce19d2a4dc6]]></appid>" +
                "<mch_id><![CDATA[1249261801]]></mch_id>" +
                "<device_info><![CDATA[aaa]]></device_info>" +
                "<nonce_str><![CDATA[HLIoBodUnYiGexPO]]></nonce_str>" +
                "<sign><![CDATA[6343017C6B23FFA96DCF57E4012C3223]]></sign>" +
                "<result_code><![CDATA[FAIL]]></result_code>" +
                "<err_code><![CDATA[OUT_TRADE_NO_USED]]></err_code>" +
                "<err_code_des><![CDATA[商户订单号重复]]></err_code_des>" +
                "</xml>" ;
        
        Map<String,String> retMap = xml2map(testXML);
        System.out.println(retMap);
        String sign = retMap.get("sign");
        String newSign  = WXpayCore.getSign(retMap, WXpayConfig.api_key);
        System.out.println(sign.equals(newSign));
    }
}
