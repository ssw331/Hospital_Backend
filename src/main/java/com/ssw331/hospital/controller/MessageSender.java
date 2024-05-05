package com.ssw331.hospital.controller;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@Service
public class MessageSender {

    // 使用 RestTemplate 进行 HTTP 请求
    private final RestTemplate restTemplate = new RestTemplate();

    // 发送普通短信
    public boolean sendSms(String phoneNumber, String context) {
        // 构建查询参数
        String uid = "JieChu";
        String key = "A6680534D182D0450B51BDF759C6477C";
        String url = "http://utf8.api.smschinese.cn/?Uid={uid}&Key={key}&smsMob={phoneNumber}&smsText={context}";

        Map<String, String> params = new HashMap<>();
        params.put("uid", uid);
        params.put("key", key);
        params.put("phoneNumber", phoneNumber);
        params.put("context", context);

        // 发起 GET 请求并处理响应
        String response = restTemplate.getForObject(url, String.class, params);

        // 添加逻辑来解析响应并确定请求是否成功
        return response != null && response.contains("success");
    }

    // 发送验证码短信
    public boolean sendSmsForVerificationCode(String phoneNumber, String verifyCode) {
        String account = "C58169395";
        String password = "776f675d18a48ed284eaf032364e06f0";
        String content = "您的验证码是：" + verifyCode + "。请不要把验证码泄露给其他人。";
        String url = "http://106.ihuyi.com/webservice/sms.php?method=Submit&account={account}&password={password}&mobile={phoneNumber}&content={content}";

        Map<String, String> params = new HashMap<>();
        params.put("account", account);
        params.put("password", password);
        params.put("phoneNumber", phoneNumber);
        params.put("content", content);

        // 发起 GET 请求并处理响应
        String response = restTemplate.getForObject(url, String.class, params);

        // 添加逻辑来解析响应并确定请求是否成功
        return response != null && response.contains("success");
    }
}

