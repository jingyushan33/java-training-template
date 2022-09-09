package com.lunz.training.constance;

/**
 * 公共常量类
 *
 * @author haha
 */
public class Constance {

    private Constance() {
    }

    /**
     * 10位日期
     */
    public static final String DATE_PATTERN_10 = "yyyy-MM-dd";

    /**
     * 19位日期
     */
    public static final String DATE_PATTERN_19 = "yyyy-MM-dd HH:mm:ss";

    /**
     * 带时区日期
     */
    public static final String PATTERN_DATE_ZONE = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";


    /**
     * 批次
     */
    public static final Integer BATCH_SIZE = 100;

    public static final String NULL_STRING = "null";

    /**
     * 错误码前缀
     */
    public static final String ERROR_FIX = "TEST";


    /**
     * token中角色对应的key（认证中心设置，每个环境都一样）
     */
    public static final String CLAIM_ROLE_KEY = "http://schemas.microsoft.com/ws/2008/06/identity/claims/role";


    /**
     * 授权用户token信息
     */
    public static final String AUTH_USER_DETAIL = "auth_user_detail";

    /**
     * 默认用户账号
     */
    public static final String DEFAULT_USER_ID = "4f500000-4c4f-0200-cbda-08d6250847ad";

    /**
     * 默认名称
     */
    public static final String DEFAULT_NAME = "系统";

    /**
     * 默认登录名
     */
    public static final String DEFAULT_LOGIN_NAME = "系统";
    /**
     * 默认客户Id
     */
    public static final String DEFAULT_CLIENT_ID = "default_client_id";
}
