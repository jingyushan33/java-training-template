package com.lunz.training.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lunz.training.bean.TbUser;
import com.lunz.training.dos.FindUserDO;
import com.lunz.training.dos.FindUserOutputDO;
import com.lunz.training.dos.UserDO;
import com.lunz.training.mapper.UserMapper;
import com.lunz.training.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, TbUser> implements IUserService {
    @Autowired
    private UserMapper userMapper;

/*    @Override
    public void addUser(UserDO userDO) {
        userMapper.addUser(userDO);
    }*/

    @Override
    public void addUser(UserDO userDO) {
        TbUser tbUser = new TbUser();
        tbUser.setUsername(userDO.getUsername());
        tbUser.setPassword(userDO.getPassword());
        tbUser.setGender(userDO.getGender());
        tbUser.setNickname(userDO.getNickname());

        userMapper.insert(tbUser);
    }

    @Override
    public FindUserOutputDO findUser(FindUserDO findUserDO) {

        if (StringUtils.isBlank(findUserDO.getUsername()) && StringUtils.isBlank(findUserDO.getNickname())) {
            return null;
        }

        // queryWrapper 从数据库读取
        LambdaQueryWrapper<TbUser> queryWrapper = new LambdaQueryWrapper<>();
        // 只选择需要的字段
        queryWrapper.select(TbUser::getUsername,TbUser::getNickname,TbUser::getGender,TbUser::getCreatedAt);
        queryWrapper.eq(TbUser::getDeleted, 0);

        if (StringUtils.isNotEmpty(findUserDO.getUsername())) {
            queryWrapper.eq(TbUser::getUsername, findUserDO.getUsername());
        }

        if (StringUtils.isNotBlank(findUserDO.getNickname())) {
            queryWrapper.eq(TbUser::getNickname, findUserDO.getNickname());
        }

        TbUser tbUser = userMapper.selectOne(queryWrapper);

        FindUserOutputDO findUserOutputDO = new FindUserOutputDO();
        findUserOutputDO.setUsername(tbUser.getUsername());
        findUserOutputDO.setNickname(tbUser.getNickname());
        findUserOutputDO.setGender(tbUser.getGender());
        findUserOutputDO.setCreatedAt(tbUser.getCreatedAt());

        return findUserOutputDO;
    }

}
