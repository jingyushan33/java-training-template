package com.lunz.training.handler;

import com.alibaba.fastjson.JSON;
import com.lunz.kernel.exceptions.core.BaseException;
import feign.FeignException;
import feign.Request;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;


/**
 * feign 异常解码信息重写
 *
 * @author puritykid
 * @date 2021-08-09 11:22
 */
@Slf4j
@Configuration
public class FeignClientErrorDecoder implements ErrorDecoder {


    @Override
    public Exception decode(String methodKey, Response response) {

        String body = null;
        try {
            body = Util.toString(response.body().asReader(StandardCharsets.UTF_8));
        } catch (IOException e) {
            log.error("feign.IOException", e);
        }
        // 获取请求方式
        Request request = response.request();
        StringBuilder sb = new StringBuilder();

        // 重写请求响应信息
        BaseException baseException = JSON.parseObject(body, BaseException.class);
        if (Objects.nonNull(baseException) && Objects.nonNull(baseException.getErrorMessage())) {
            sb.append("Feign RPC")
                    .append("[").append(request.httpMethod().name()).append("]")
                    .append("请求").append("[").append(request.url()).append("]").append("调用")
                    .append("[").append(methodKey).append("]")
                    .append("异常：").append(String.join("||", baseException.getErrorMessage()));
        } else {
            // 重写请求响应信息
            FeignException feignException = FeignException.errorStatus(methodKey, response);
            sb.append("Feign RPC 请求异常：").append(String.join("||", feignException.getMessage()));
        }
        Response newResponse = response.toBuilder().body(sb.toString(), StandardCharsets.UTF_8).build();
        return FeignException.errorStatus(methodKey, newResponse);
    }
}
