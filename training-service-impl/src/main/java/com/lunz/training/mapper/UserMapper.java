package com.lunz.training.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lunz.training.bean.TbUser;
import com.lunz.training.dos.UserDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author liangbing
 * @description 用户Mapper
 * @date 2022/8/29
 */
@Repository
public interface UserMapper extends BaseMapper<TbUser> {

    /**
     * 新增用户
     * @param user
     * @return
     */
    void addUser(@Param("user") UserDO user);

    /**
     * 修改用户
     * @param user
     */
    int updateById(@Param("user") UserDO user);

    /**
     * 删除用户
     * @param id
     * @return
     */
    int delete(Integer id);
}

