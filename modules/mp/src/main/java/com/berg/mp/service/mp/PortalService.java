package com.berg.mp.service.mp;

public interface PortalService {

    void authentication(String appId,String signature,String timestamp,String nonce,String echostr);

    void handler(String appId,String signature,String timestamp,String nonce,String openid,String encType,String msgSignature,String requestBody);
}
