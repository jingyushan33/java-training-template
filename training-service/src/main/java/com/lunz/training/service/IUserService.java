package com.lunz.training.service;

import com.lunz.training.dos.FindUserDO;
import com.lunz.training.dos.FindUserOutputDO;
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

    /**
     *  查找用户
     * @param findUserDO
     * @return
     */
    FindUserOutputDO findUser(FindUserDO findUserDO);

    /**
     *
     * @param userDTO
     */
    void updateDemo(UserDO userDTO);
}

