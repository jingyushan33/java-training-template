package com.lunz.training.impl;

import com.google.common.net.HttpHeaders;
import com.lunz.training.IRpcDemoService;
import com.lunz.training.annotation.RpcService;
import com.lunz.http.enums.HttpClientEnumFactory;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: RpcDemoService
 * @Description: 远程调用服务
 * @Author: puritykid
 * @Date: 2021/7/1 16:32
 * @Version: v1.0
 */
@RpcService
public class RpcDemoService implements IRpcDemoService {

    // 此处注入自己需要的第三方调用客户端

    @Override
    public String test() {


        Map<String, String> headerMap = new HashMap<>();
        headerMap.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        // 某些接口可能会验证User-Agent,所以需要设置这个请求头，默认不设置即可
        headerMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36");
        String json = HttpClientEnumFactory.INSTANCE.getDefaultClient().get("http://api.help.bj.cn/apis/weather/?id=101060101",null,headerMap);

        return "服务通信成功了..." + json;
    }
}
