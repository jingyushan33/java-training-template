package com.lunz.training.inceptor;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.lunz.training.constance.Constance;
import com.lunz.training.utils.UserUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * feign 请求拦截器
 *
 * @author puritykid
 * @date 2021-07-12 9:06
 */
public class FeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {

        // 全局自定义信息容器，如果是经过网关的rpc通信，传递上游解析token信息给下游服务
        if (UserUtils.GLOBAL_CUSTOM_USER_INFO.get() == null) {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();
            requestTemplate.header(Constance.AUTH_USER_DETAIL, request.getHeader(Constance.AUTH_USER_DETAIL));
        } else {
            // 全局自定义信息容器，如果是不经过网关的rpc通信，设置自定义信息
            Map<Object, Object> paramMap = Maps.newHashMap();
            paramMap.put("sub", UserUtils.GLOBAL_CUSTOM_USER_INFO.get());
            requestTemplate.header(Constance.AUTH_USER_DETAIL, JSON.toJSONString(paramMap));
            // 用完清除
            UserUtils.GLOBAL_CUSTOM_USER_INFO.remove();
        }

    }
}
