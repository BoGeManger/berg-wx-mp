package com.berg.mp.service.mp;

public interface PortalService {

    void get(String appId,String signature,String timestamp,String nonce,String echostr);

    void post(String appId,String signature,String timestamp,String nonce,String openid,String encType,String msgSignature,String requestBody);
}
