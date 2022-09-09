package com.lunz.training.utils;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * 用户详情
 * @author puritykid
 */
@Data
public class UserDetails implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -3902673607259720897L;
    /**
     * userId 用户Id
     */
    @JSONField(name = "sub")
    private String userId;
    /**
     * 用户名
     */
    @JSONField(name = "name")
    private String userName;
    /**
     * 用户登陆账号
     */
    @JSONField(name = "username")
    private String loginName;

    /**
     * 客户端id
     */
    private String clientId;
    /**
     * 公司Id（用户所属顶级-兼容老）
     */
    private String organizationId;
    /**
     * 公司名称（用户所属顶级-兼容老）
     */
    private String organizationName;
    /**
     * 部门Id（用户所属部门-兼容老）
     */
    private String departmentId;
    /**
     * 部门名称（用户所属部门-兼容老）
     */
    private String departmentName;
    /**
     * 部门code
     */
    private String departmentCode;
    private String authToken;
    private String email;
    /**
     * 用户所属组织机构（新）
     */
    private List<String> organizations;
    /**
     * 用户具有的角色
     */
    private List<String> roles;
}
