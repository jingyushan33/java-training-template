package com.lunz.training.inner;

import com.lunz.training.ITrainingApplicationFeignClient;
import com.lunz.training.bean.Demo;
import com.lunz.training.convert.DemoConvertMappers;
import com.lunz.training.service.DemoService;
import com.lunz.training.vo.DemoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 演示内部接口不对外暴露，只提供微服务间调用
 *
 * @author haha
 */
@Api(value = "内部接口", tags = {"内部接口"})
@Validated
@RestController
public class RpcTrainingApplicationController implements ITrainingApplicationFeignClient {


    @Autowired
    private DemoService demoService;

    @Override
    @ApiOperation("获取用户详情")
    public DemoVO getUserInfoById(String id) {
        Demo demo = demoService.getById(id);
        return DemoConvertMappers.MAPPER.convertVO(demo);
    }

    @Override
    @ApiOperation("查看所有")
    public List<DemoVO> getAll() {
        List<Demo> demoList = demoService.list();
        return DemoConvertMappers.MAPPER.convertList(demoList);
    }

}