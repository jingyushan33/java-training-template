package com.lunz.training.service;

import com.lunz.training.dos.UserDO;

/**
 * @author liangbing
 * @description 用户服务接口
 * @date 2022/8/29
 */
public interface IUserService {

    /**
     * 新增用户
     * @param userDO
     */
    void addUser(UserDO userDO);

}

