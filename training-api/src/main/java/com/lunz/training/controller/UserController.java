package com.lunz.training.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.lunz.training.BaseController;
import com.lunz.training.annotation.AllowAnonymous;
import com.lunz.training.convert.UserConvertMappers;
import com.lunz.training.dos.FindUserDO;
import com.lunz.training.dos.FindUserOutputDO;
import com.lunz.training.dos.UserDO;
import com.lunz.training.dto.FindUserDTO;
import com.lunz.training.dto.UserDTO;
import com.lunz.training.group.AddGroup;
import com.lunz.training.group.UpdateGroup;
import com.lunz.training.result.OperationResult;
import com.lunz.training.result.OperationTResult;
import com.lunz.training.service.IUserService;
import com.lunz.training.vo.FindUserVO;
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

    /**
     * 新增用户
     * @param userDTO
     * @return
     */
    @AllowAnonymous
    @ApiOperation(value = "新增用户", tags = {"作业"})
    @ApiResponse(code = 200, message = "success")
    @PostMapping("/addUser")
    @ResponseBody
    public OperationResult addUser(@RequestBody @Validated({AddGroup.class}) @Valid UserDTO userDTO) {
        UserDO userDO = UserConvertMappers.MAPPER.convert2UserDO(userDTO);
        userService.addUser(userDO);
        return OperationResult.success();
    }

    /**
     * 查找用户
     * @param findUserDTO 查找用户参数类
     * @return
     */
    @ApiOperation(value = "查找用户", tags = {"作业"})
    @ApiResponse(code = 200, message = "success")
    @GetMapping("/findUser")
    @ResponseBody
    public OperationTResult<FindUserDTO> findUse(@Valid @RequestBody FindUserDTO findUserDTO){
        if (StringUtils.isBlank(findUserDTO.getUsername()) && StringUtils.isBlank(findUserDTO.getNickname())) {
            return OperationTResult.fail("请输入用户名或者昵称！");
        }
        FindUserDO findUserDO = UserConvertMappers.MAPPER.convert2FindUserDO(findUserDTO);

        FindUserOutputDO findUserOutputDO = userService.findUser(findUserDO);
        FindUserVO findUserVO = null;

        if (findUserOutputDO != null) {
            findUserVO = UserConvertMappers.MAPPER.convert2FindUserVO(findUserOutputDO);
        }

        if (findUserVO == null) {
            return OperationTResult.fail("找不到用户！");
        } else {
            return OperationTResult.succeeded(findUserVO);
        }
    }

    /**
     * 更新用户
     * @param userDTO
     * @return
     */
    @AllowAnonymous
    @ApiOperation(value = "修改用户", tags = {"作业"})
    @ApiResponse(code = 200, message = "success")
    @PutMapping("/updateUser")
    @ResponseBody
    public OperationResult updateUser(@RequestBody @Validated({UpdateGroup.class}) @Valid UserDTO userDTO){
        UserDO userDO = UserConvertMappers.MAPPER.convert2UserDO(userDTO);
        userService.updateDemo(userDO);
        return OperationResult.success();
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @AllowAnonymous
    @ApiOperation(value = "删除用户", tags = {"作业"})
    @ApiResponse(code = 200, message = "success")
    @PutMapping("/deleteUser/{id}")
    @ResponseBody
    public OperationResult deleteUser(@PathVariable("id")  Integer id){
        userService.deleteDemo(id);
        return OperationResult.success();
    }
}

