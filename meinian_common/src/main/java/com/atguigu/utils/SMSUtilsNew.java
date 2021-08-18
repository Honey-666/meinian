package com.atguigu.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

public class SMSUtilsNew {
    public static final String accessKeyId = "LTAI4GK9fwMg9Qjj1CoyD3hJ";
    public static final String accessKeySecret = "KGzAqrBpKBarcdN4HGCCunY0cRVI6M";
    public static final String SMS_CODE = "SMS_207954150";
    public static final String singName = "MAXMC";

    public static void sendShortMessage(String phoneNumbers,String code) throws Exception {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();

        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");

        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers",phoneNumbers);
        request.putQueryParameter("SignName",singName);
        request.putQueryParameter("TemplateCode",SMS_CODE);
        request.putQueryParameter("TemplateParam","{\"code\":\"" + code + "\"}");

        CommonResponse response = client.getCommonResponse(request);
        System.out.println(response.getData());
    }
}
