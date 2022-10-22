package com.lunz.training.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查找用户DTO
 */
@Data
public class FindUserDTO {
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickname;
}
