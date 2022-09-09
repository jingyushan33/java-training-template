package com.lunz.training.utils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.lunz.training.constance.Constance;
import com.lunz.kernel.exceptions.InvalidTokenException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 用户信息工具类
 *
 * @author haha
 */

@Slf4j
public class UserUtils {

    private UserUtils() {

    }

    /**
     * 自定义全局用户信息存储器
     */
    public static final ThreadLocal<String> GLOBAL_CUSTOM_USER_INFO = new ThreadLocal<>();

    /**
     * 获取当前登陆用户ID
     */
    public static String getCurrentUserId() {
        return getUserDetails().getUserId();
    }

    /**
     * 获取当前登陆用户名称
     */
    public static String getCurrentUserName() {
        return getUserDetails().getUserName();
    }

    /**
     * 获取当前登陆用户账号
     */
    public static String getCurrentLoginName() {
        return getUserDetails().getLoginName();
    }

    /**
     * 获取当前登陆用户clientId
     */
    public static String getCurrentClientId() {
        return getUserDetails().getClientId();
    }

    /**
     * 获取当前登陆用户ID
     */
    private static UserDetails getUserDetails() {
        // 验证token的有效性
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(servletRequestAttributes).getRequest();
        String claims = request.getHeader(Constance.AUTH_USER_DETAIL);
        if (StringUtils.isEmpty(claims)) {
            throw new InvalidTokenException();
        }
        try {
            // 解码
            String claimBody = URLDecoder.decode(claims, StandardCharsets.UTF_8.name());

            // 填充用户信息
            UserDetails userDetails = JSON.parseObject(claimBody, UserDetails.class);
            List<String> roles = Lists.newArrayList();
            // 填充角色信息
            Map<String, Object> claimMap = JSON.parseObject(claimBody, Map.class);
            claimMap.forEach((k, v) -> {
                if (k.contains(Constance.CLAIM_ROLE_KEY)) {
                    if (v instanceof String) {
                        roles.add((String) v);
                    } else {
                        roles.addAll(((List<String>) v));
                    }
                }
            });
            userDetails.setRoles(roles);
            // 第三方调用没有用户信息,设置系统默认值
            if (StringUtils.isBlank(userDetails.getUserId())) {
                // 认证通过，对于不带用户信息的token,做默认设置
                userDetails.setUserId(Constance.DEFAULT_USER_ID);
                userDetails.setUserName(Constance.DEFAULT_NAME);
                userDetails.setUserName(Constance.DEFAULT_LOGIN_NAME);
                userDetails.setClientId(Constance.DEFAULT_CLIENT_ID);
            }
            return userDetails;
        } catch (Exception e) {
            throw new InvalidTokenException();
        }
    }

}
