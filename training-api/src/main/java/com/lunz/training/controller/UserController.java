package com.lunz.training.controller;

import com.lunz.kernel.exceptions.NotFoundDataException;
import com.lunz.training.BaseController;
import com.lunz.training.annotation.AllowAnonymous;
import com.lunz.training.convert.UserConvertMappers;
import com.lunz.training.dos.UserDO;
import com.lunz.training.dto.UserDTO;
import com.lunz.training.result.OperationResult;
import com.lunz.training.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Api(value="用户 API",tags = {"用户 API"})
@Validated
@RestController
@RequestMapping("/api/v1/user")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;
    
    @AllowAnonymous
    @ApiOperation(value = "新增用户", tags = {"作业"})
    @ApiResponse(code = 200, message = "success")
    @PostMapping("/addUser")
    @ResponseBody
    public OperationResult addUser(@Valid @RequestBody UserDTO userDTO) {
        UserDO userDO = UserConvertMappers.MAPPER.convert2UserDO(userDTO);
        userService.addUser(userDO);
        return OperationResult.success();
    }


    @AllowAnonymous
    @ApiOperation(value = "根据姓名查找用户", tags = {"作业"})
    @ApiResponse(code = 200, message = "success")
    @PostMapping("/findUserByName/{name}")
    @ResponseBody
    public UserDO findUser(@Valid String name) {
        UserDTO userDTO = userService.findUser(name);
        if (userDTO == null) {
            throw new NotFoundDataException();
        }

        return UserConvertMappers.MAPPER.convert2UserDO(userDTO);
    }
}

