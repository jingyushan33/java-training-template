package com.lunz.training.dto;

import com.lunz.training.group.AddGroup;
import com.lunz.training.group.UpdateGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

@Data
public class UserDTO {
    /**
     * 用户id
     */
    @ApiModelProperty(name = "id",value = "用户id")
    private Integer id;

    /**
     * 用户名
     */
    @ApiModelProperty(name = "name", value = "用户名称", required = true)
    @NotBlank(message = "用户名不能为空！")
    @Pattern(regexp = "^.{1,20}$", message = "用户名长度必须在1-20范围内!")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\\\\W_]+$)(?![a-z0-9]+$)(?![a-z\\\\W_]+$)(?![0-9\\\\W_]+$)[a-zA-Z0-9\\\\W_]{8,20}$", message = "密码长度8-20位且至少包含大写字母、小写字母、数字或特殊符号中的任意三种")
    private String password;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    @Range(min=0, max = 1, message = "性别不正确")
    private Integer gender;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    @Length(max = 20, message = "昵称最大20位")
    private String nickname;
}
