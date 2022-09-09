package com.lunz.training.dto;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

import com.lunz.training.group.AddGroup;
import com.lunz.training.group.UpdateGroup;


/**
 * @ClassName: DemoDTO
 * @Description: 测试入参
 * @Author: puritykid
 * @Date: 2021/6/28 10:36
 * @Version: v1.0
 */
@Data
@ApiModel
public class DemoDTO {

    /**
     * 注：有些字段是新增不需要，但是修改必填，通过分组实现，分组之后按照不同的组实现不同的验证逻辑
     */
    @ApiModelProperty(name = "id", value = "用户id")
    @TableId(type = IdType.AUTO)
    @Null(groups = {AddGroup.class}, message = "id必须是空！")
    @NotNull(groups = {UpdateGroup.class}, message = "id不能为空！")
    private Integer id;


    @ApiModelProperty(name = "name", value = "用户名称", required = true)
    @NotBlank(message = "用户名不能为空！")
    private String name;

    @ApiModelProperty(name = "loginName", value = "用户登录名", required = true)
    @NotBlank(message = "登录名不能为空！")
    @Pattern(regexp = "^.{1,50}$", message = "登录账号长度必须在1-50范围内!")
    private String loginName;

}
