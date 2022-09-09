package com.lunz.training.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * 演示实体
 * @author haha
 */
@TableName
@Data
public class Demo extends BaseModel {


    @TableId(type=IdType.AUTO)
    private Integer id;

    private String name;

    private String loginName;


}
