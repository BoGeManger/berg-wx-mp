package com.berg.mp.constant;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class MpConstants {

    @Value("${shiro.urls:default}")
    String shiroUrls;

    @Value("${shiro.expireTime:180}")
    Integer expireTime;
}
