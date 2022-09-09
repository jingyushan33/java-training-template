package com.lunz.training.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("tb_user")
@Data
public class TbUser extends BaseModel {

    private Integer id;
    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别 0-男 1-女
     */
    private Integer gender;
}
