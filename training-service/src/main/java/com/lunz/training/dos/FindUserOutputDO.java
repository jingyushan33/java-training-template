package com.lunz.training.dos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FindUserOutputDO {

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
}