package com.lunz.training.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lunz.training.bean.TbUser;
import com.lunz.training.dos.UserDO;
import com.lunz.training.mapper.UserMapper;
import com.lunz.training.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, TbUser> implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser(UserDO userDO) {
        userMapper.addUser(userDO);
        /*TbUser tbUser = new TbUser();
        tbUser.setUsername(userDO.getUsername());
        tbUser.setPassword(userDO.getPassword());
        tbUser.setGender(userDO.getGender());
        tbUser.setNickname(userDO.getNickname());

        userMapper.insert(tbUser);*/
    }
}
