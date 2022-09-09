package com.lunz.training.dos;

import lombok.Data;

/**
 * @author liangbing
 * @description 用户 领域模型类
 * @date 2022/8/29
 */
@Data
public class UserDO {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别 0-男 1-女
     */
    private Integer gender;

    /**
     * 昵称
     */
    private String nickname;

}

