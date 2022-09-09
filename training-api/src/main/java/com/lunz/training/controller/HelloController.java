package com.lunz.training.controller;

import com.lunz.training.BaseController;
import com.lunz.training.annotation.AllowAnonymous;
import com.lunz.training.convert.QuoteConvertMappers;
import com.lunz.training.dos.QuoteListQueryDO;
import com.lunz.training.dto.QuoteListDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


@Api(value="Hello API",tags = {"Hello API"})
@Validated
@RestController
@RequestMapping("/api/v1/hello")
public class HelloController extends BaseController {
    @ApiOperation("你好")
    @GetMapping("/{name}")
    public String hello(@PathVariable("name") @NotBlank(message = "姓名不能为空") String name){
        return "hello"+name;
    }

    /**
     * 获取客户报价列表-按客户、业务类型
     * @param quoteListDTO 客户报价参数
     * @return 客户报价列表
     */
    @AllowAnonymous
    @ApiOperation(value = "客户报价列表", tags = {"报价相关接口"})
    @ApiResponse(code = 200, message = "success")
    @PostMapping("/quoteListByCust")
    @ResponseBody
    public String quoteListByCust(@Valid @RequestBody QuoteListDTO quoteListDTO) {
        QuoteListQueryDO quoteListQueryDO = QuoteConvertMappers.MAPPER.convert2QuoteListQueryDO(quoteListDTO);
        return "OK";
    }
}

